import React, { useState } from 'react';
import axios from 'axios';
import '../Components/CSS/BookDetails.css'; // Ensure you have CSS file for styles

function BookDetails() {
  const [bookId, setBookId] = useState('');
  const [book, setBook] = useState(null);
  const [error, setError] = useState(null);

  const handleSubmit = (event) => {
    event.preventDefault();
    axios.get(`http://localhost:8080/books/${bookId}`)
      .then(response => {
        setBook(response.data);
        setError(null);
      })
      .catch(error => {
        setError('Error fetching book details. Please try again.');
        setBook(null);
        console.error('Error fetching book details:', error);
      });
  };

  return (
    <div className="book-details-container">
      <h2>Get Book by ID</h2>
      <form onSubmit={handleSubmit} className="book-details-form">
        <div className="form-group">
          <label htmlFor="bookId">Book ID:</label>
          <input
            type="text"
            id="bookId"
            value={bookId}
            onChange={(e) => setBookId(e.target.value)}
            required
            placeholder="Enter book ID"
          />
        </div>
        <button type="submit" className="submit-button">Submit</button>
      </form>
      {error && <p className="error-message">{error}</p>}
      {book && (
        <div className="book-details">
          <h3>{book.bookName}</h3>
          <p><strong>Author:</strong> {book.author.aname}</p>
          <p><strong>Price:</strong> ${book.price}</p>
          <p><strong>Quantity:</strong> {book.quantity}</p>
          <p><strong>Description:</strong> {book.description}</p>
        </div>
      )}
    </div>
  );
}

export default BookDetails;
