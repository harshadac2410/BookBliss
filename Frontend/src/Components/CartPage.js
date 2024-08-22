import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../Components/CSS/CartPage.css'; // Ensure you have a CSS file for styling

function CartPage() {
  const [cartItems, setCartItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch cart details from the API
    axios.get('http://localhost:8080/cart') // Replace with your actual API URL
      .then(response => {
        setCartItems(response.data);
        setLoading(false);
      })
      .catch(error => {
        setError('Error fetching cart details. Please try again later.');
        setLoading(false);
        console.error('Error fetching cart details:', error);
      });
  }, []);

  return (
    <div className="cart-page">
      <h2>Cart Details</h2>
      {loading && <p>Loading...</p>}
      {error && <p className="error-message">{error}</p>}
      {!loading && !error && (
        <div className="cart-container">
          {cartItems.length > 0 ? (
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
                {cartItems.map((item) => (
                  <tr key={item.cart_id}>
                    <td>{item.orderBook.bookName}</td>
                    <td>{item.orderBook.author.aname}</td>
                    <td>${item.orderBook.price}</td>
                    <td>{item.quantity}</td>
                    <td>${item.total_price}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <p>No items in the cart.</p>
          )}
        </div>
      )}
    </div>
  );
}

export default CartPage;
