  import React, { useState, useEffect } from 'react';
  import axios from 'axios';
  import '../Components/CSS/Dashboard.css'; // Ensure you have CSS file for styles
  
  function CustomerDashboard() {
    const [showBooks, setShowBooks] = useState(false);
    const [showCart, setShowCart] = useState(false);
    const [books, setBooks] = useState([]);
    const [cart, setCart] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [cartError, setCartError] = useState(null);
  
    useEffect(() => {
      if (showBooks) {
        axios.get('http://localhost:8080/books') // Replace with your API URL
          .then(response => {
            setBooks(response.data);
            setLoading(false);
          })
          .catch(error => {
            setError('Error fetching books. Please try again later.');
            setLoading(false);
            console.error('Error fetching books:', error);
          });
      }
    }, [showBooks]);
  
    useEffect(() => {
      if (showCart) {
        axios.get('http://localhost:8080/cart') // Replace with your API URL for cart
          .then(response => {
            setCart(response.data);
            setLoading(false);
          })
          .catch(error => {
            setCartError('Error fetching cart details. Please try again later.');
            setLoading(false);
            console.error('Error fetching cart details:', error);
          });
      }
    }, [showCart]);
  
    const handleAddToCart = (book) => {
      // Create a new cart item
      const newCartItem = {
          orderBook: book,
          quantity: 1,
          total_price: book.price
      };
  
      // Check if the item already exists in the cart
      const existingItem = cart.find(item => item.orderBook.book_id === book.book_id);
  
      if (existingItem) {
          // Update the existing cart item
          const updatedCartItem = {
              ...existingItem,
              quantity: existingItem.quantity + 1,
              total_price: (existingItem.quantity + 1) * existingItem.orderBook.price
          };
  
          axios.put(`http://localhost:8080/cart/${existingItem.cart_id}`, updatedCartItem)
              .then(response => {
                  // Update the cart state with the modified item
                  setCart(cart.map(item =>
                      item.orderBook.book_id === book.book_id ? updatedCartItem : item
                  ));
              })
              .catch(error => {
                  console.error('Error updating cart:', error);
              });
      } else {
          // Add the new cart item
          axios.post('http://localhost:8080/cart', newCartItem)
              .then(response => {
                  // Update the cart state with the newly added item
                  setCart([...cart, response.data]);
                  // Redirect to the cart view
                  setShowBooks(false);
                  setShowCart(true);
              })
              .catch(error => {
                  console.error('Error adding item to cart:', error);
              });
      }
  };
  
    
  
    const handleLogout = () => {
      localStorage.removeItem('token'); // Example: removing a token from local storage
      window.location.href = '/'; // Redirect to the home page
    };
  
    const handleLinkClick = (section) => {
      if (section === 'books') {
        setShowBooks(true);
        setShowCart(false);
      } else if (section === 'cart') {
        setShowBooks(false);
        setShowCart(true);
      }
    };
  
    return (
      <div className="dashboard-container">
        <div className="sidebar">
          <h2>Customer Dashboard</h2>
          <ul>
            <li><button onClick={() => handleLinkClick('books')}>{showBooks ? 'Hide Books' : 'View Books'}</button></li>
            <li><button onClick={() => handleLinkClick('cart')}>{showCart ? 'Hide Cart' : 'View Cart'} ({cart.length})</button></li>
            <li><button onClick={handleLogout} className="logout-button">Logout</button></li>
          </ul>
        </div>
        <div className="content">
          {showBooks && (
            <div className="books-list">
              <h2>Books Collection</h2>
              {loading && <p>Loading...</p>}
              {error && <p className="error-message">{error}</p>}
              {!loading && !error && (
                <div className="book-container">
                  {books.length > 0 ? (
                    books.map((book) => (
                      <div key={book.book_id} className="book-card">
                        <img
                          src='https://img.freepik.com/premium-photo/landscape-open-book-with-cover-mockup-mockup_641503-157256.jpg' // Default image URL
                          alt={book.bookName}
                          className="book-image"
                        />
                        <div className="book-details">
                          <h3 className="book-title">{book.bookName}</h3>
                          <p className="book-author">by {book.author.aname}</p>
                          <p className="book-price">${book.price}</p>
                          <button onClick={() => handleAddToCart(book)}>Add to Cart</button>
                        </div>
                      </div>
                    ))
                  ) : (
                    <p>No books available.</p>
                  )}
                </div>
              )}
            </div>
          )}
          {showCart && (
            <div className="cart-list">
              <h2>Cart Details</h2>
              {loading && <p>Loading...</p>}
              {cartError && <p className="error-message">{cartError}</p>}
              {!loading && !cartError && (
                <table className="cart-table">
                  <thead>
                    <tr>
                      <th>Book Name</th>
                      <th>Author</th>
                      <th>Price</th>
                      <th>Quantity</th>
                      <th>Total Price</th>
                    </tr>
                  </thead>
                  <tbody>
                    {cart.length > 0 ? (
                      cart.map((item) => (
                        <tr key={item.cart_id}>
                          <td>{item.orderBook.bookName}</td>
                          <td>{item.orderBook.author.aname}</td>
                          <td>${item.orderBook.price}</td>
                          <td>{item.quantity}</td>
                          <td>${item.total_price}</td>
                        </tr>
                      ))
                    ) : (
                      <tr>
                        <td colSpan="5">No items in the cart.</td>
                      </tr>
                    )}
                  </tbody>
                </table>
              )}
            </div>
          )}
        </div>
      </div>
    );
  }
  
  export default CustomerDashboard;
  