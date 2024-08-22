import React, { useState, useEffect } from 'react';
import axios from 'axios';
import UpdateBookForm from './UpdateBookForm'; 
import BookDetails from './BookDetails'; 
import OrderDetails from './OrderDetails'; 
import PaymentDetails from './PaymentDetails'; 
import AddBookForm from './AddBookForm';
import SearchBooksByAuthor from './SearchBooksByAuthor'; // Import the new SearchBooksByAuthor component
import '../Components/CSS/AdminDashboard.css'; 

function AdminDashboard() {
  const [showBooks, setShowBooks] = useState(false);
  const [showBookDetails, setShowBookDetails] = useState(false);
  const [showOrderDetails, setShowOrderDetails] = useState(false);
  const [showPaymentDetails, setShowPaymentDetails] = useState(false);
  const [showAddBookForm, setShowAddBookForm] = useState(false);
  const [showSearchBooksForm, setShowSearchBooksForm] = useState(false); // State for search form visibility
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedBook, setSelectedBook] = useState(null);

  useEffect(() => {
    if (showBooks) {
      axios.get('http://localhost:8080/books') 
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

  const handleUpdateBook = (book) => {
    setSelectedBook(book);
  };

  const handleCloseUpdateForm = () => {
    setSelectedBook(null);
  };

  const handleUpdateSuccess = () => {
    axios.get('http://localhost:8080/books')
      .then(response => setBooks(response.data))
      .catch(error => console.error('Error refreshing book list:', error));
    handleCloseUpdateForm();
  };

  const handleLogout = () => {
    localStorage.removeItem('token'); 
    window.location.href = '/'; 
  };

  return (
    <div className="admin-dashboard-container">
      <div className="sidebar">
        <h2>Admin Dashboard</h2>
        <ul>
          <li><a href="#" onClick={() => { setShowBooks(true); setShowBookDetails(false); setShowOrderDetails(false); setShowPaymentDetails(false); setShowAddBookForm(false); setShowSearchBooksForm(false); }}>View Books</a></li>
          <li><a href="#" onClick={() => { setShowBooks(false); setShowBookDetails(true); setShowOrderDetails(false); setShowPaymentDetails(false); setShowAddBookForm(false); setShowSearchBooksForm(false); }}>Get Book by ID</a></li>
          <li><a href="#" onClick={() => { setShowBooks(false); setShowBookDetails(false); setShowOrderDetails(true); setShowPaymentDetails(false); setShowAddBookForm(false); setShowSearchBooksForm(false); }}>Get Order Details</a></li>
          <li><a href="#" onClick={() => { setShowBooks(false); setShowBookDetails(false); setShowOrderDetails(false); setShowPaymentDetails(true); setShowAddBookForm(false); setShowSearchBooksForm(false); }}>Payment Details</a></li>
          <li><a href="#" onClick={() => { setShowBooks(false); setShowBookDetails(false); setShowOrderDetails(false); setShowPaymentDetails(false); setShowAddBookForm(true); setShowSearchBooksForm(false); }}>Add New Book</a></li>
          <li><a href="#" onClick={() => { setShowBooks(false); setShowBookDetails(false); setShowOrderDetails(false); setShowPaymentDetails(false); setShowAddBookForm(false); setShowSearchBooksForm(true); }}>Search Books by Author</a></li> {/* New hyperlink */}
          <li><button onClick={handleLogout} className="logout-button">Logout</button></li>
        </ul>
      </div>
      <div className="content">
        {showBooks && (
          <div className="books-list">
            <h2>Books Management</h2>
            {loading && <p>Loading...</p>}
            {error && <p className="error-message">{error}</p>}
            {!loading && !error && (
              <div className="book-container">
                {books.length > 0 ? (
                  books.map((book) => (
                    <div key={book.book_id} className="book-card">
                      <img
                        src='https://img.freepik.com/premium-photo/landscape-open-book-with-cover-mockup-mockup_641503-157256.jpg' 
                        alt={book.bookName}
                        className="book-image"
                      />
                      <div className="book-details">
                        <h3 className="book-title">{book.bookName}</h3>
                        <p className="book-author">by {book.author.aname}</p>
                        <p className="book-price">Price:${book.price}</p>
                        <p className="book-quantity">Quantity:{book.quantity}</p>
                        <button onClick={() => handleUpdateBook(book)}>Update</button>
                        <button className="btn btn-danger btn-sm ml-2">Delete</button>
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
        {showBookDetails && (
          <BookDetails />
        )}
        {showOrderDetails && (
          <OrderDetails />
        )}
        {showPaymentDetails && (
          <PaymentDetails />
        )}
        {showAddBookForm && (
          <AddBookForm onSuccess={() => { 
            setShowAddBookForm(false); 
            alert('Book added successfully!'); 
          }} />
        )}
        {showSearchBooksForm && (
          <SearchBooksByAuthor />
        )}
        {selectedBook && (
          <UpdateBookForm
            bookId={selectedBook.book_id}
            onClose={handleCloseUpdateForm}
            onUpdate={handleUpdateSuccess}
          />
        )}
      </div>
    </div>
  );
}

export default AdminDashboard;
