import React, { useState } from 'react';
import axios from 'axios';
import '../Components/CSS/SearchBooksByAuthor.css'; // Import the CSS file

function SearchBooksByAuthor() {
  const [authorName, setAuthorName] = useState('');
  const [books, setBooks] = useState([]);
  const [error, setError] = useState(null);

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Submitting author name:', authorName); // Log the author name
    if (!authorName.trim()) {
      setError('Author name cannot be empty.');
      return;
    }

    axios.get(`http://localhost:8080/books/author/${encodeURIComponent(authorName)}`)
      .then(response => {
        console.log('Response:', response); // Log the response
        if (response.status === 204) {
          setBooks([]);
          setError('No books found for this author.');
        } else if (response.status === 200) {
          setBooks(response.data);
          setError(null);
        } else {
          setBooks([]);
          setError('Unexpected response from the server.');
        }
      })
      .catch(error => {
        if (error.response) {
          // Server responded with a status other than 200 range
          console.error('Error Response:', error.response);
          setError(`Error: ${error.response.status} ${error.response.statusText}`);
        } else if (error.request) {
          // Request was made but no response received
          console.error('Error Request:', error.request);
          setError('No response received from the server.');
        } else {
          // Something happened in setting up the request
          console.error('Error Message:', error.message);
          setError('An error occurred while setting up the request.');
        }
        setBooks([]);
      });
  };
  
  
  

  return (
    <div className="search-books-container">
      <h2 style={{color : "blue"}}>Search Book by Author</h2>
      <form onSubmit={handleSubmit} className="search-books-form">
        <label htmlFor="authorName">
          Author Name:
          <input
            type="text"
            id="authorName"
            value={authorName}
            onChange={(e) => setAuthorName(e.target.value)}
            required
          />
        </label>
        <button type="submit">Search</button>
      </form>
      {error && <p className="error-message">{error}</p>}
      {books.length > 0 && (
        <div className="books-list">
          {books.map((book) => (
            <div key={book.book_id} className="book-card">
              <div className="book-details">
                <h3 className="book-title">{book.bookName}</h3>
                <p className="book-author">by {book.author.aname}</p>
                <p className="book-price">${book.price}</p>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default SearchBooksByAuthor;
