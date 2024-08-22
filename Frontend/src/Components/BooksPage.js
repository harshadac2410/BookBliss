import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../Components/CSS/BooksPage.css'; // Ensure the CSS file is correctly imported
import greatgatsby from '../images/TheGreatGatsby.jpg';
import killm from '../images/To Kill a Mockingbird.jpg'; 
import war from '../images/The Art of War.jpg';
import attitude from '../images/Attitude-is-Everything.jpg';
import harry from '../images/harry.jpg';
import hobbit from '../images/hobbit.jpg';

const bookImageMap = {
    'The Great Gatsby': greatgatsby,
    'To Kill a Mockingbird': killm,
    'The Art of War': war,
    'Attitude is Everything': attitude,
    'Harry Potter and the Sorcerer\'s Stone': harry,
    'The Hobbit': hobbit,
};

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
              <div key={book.id} className="book-card">
                <img
                  src={bookImageMap[book.bookName] || '/images/default-book.avif'} // Fallback image
                  alt={book.bookName}
                  className="book-image"
                />
                <div className="book-details">
                  <h3 className="book-title">{book.bookName}</h3>
                  <p className="book-author">by {book.authorId}</p>
                  <p className="book-price">${book.price}</p>
                  {/* You can add buttons for actions here */}
                  <button className="delete-button">Delete</button>
                  {/* <button className="btn-primary">Update</button> */}
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
