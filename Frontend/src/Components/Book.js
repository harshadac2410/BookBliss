import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../Components/CSS/Book.css'; // Ensure the CSS file is correctly imported

function BooksPage() {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch books from the API
    axios.get('http://localhost:8080/books') // Replace with your actual API URL
      .then(response => {
        setBooks(response.data);
        setLoading(false);
      })
      .catch(error => {
        setError('Error fetching books. Please try again later.');
        setLoading(false);
        console.error('Error fetching books:', error);
      });
  }, []);

  return (
    <div className="book-list">
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
                  {/* You can add buttons for actions here */}
                </div>
              </div>
            ))
          ) : (
            <p>No books available.</p>
          )}
        </div>
      )}
    </div>
  );
}

export default BooksPage;
