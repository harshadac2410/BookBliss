import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../Components/CSS/AddBookForm.css';

function AddBookForm({ onSuccess }) {
  const [book, setBook] = useState({
    bookName: '',
    price: '',
    quantity: '',
    description: '',
    author: {
      aname: '',
      description: ''
    }
  });
  const [authors, setAuthors] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch authors for the form
    axios.get('http://localhost:8080/authors') // Adjust the URL if needed
      .then(response => setAuthors(response.data))
      .catch(error => console.error('Error fetching authors:', error));
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBook(prevBook => ({
      ...prevBook,
      [name]: value
    }));
  };

  const handleAuthorChange = (e) => {
    const { name, value } = e.target;
    setBook(prevBook => ({
      ...prevBook,
      author: {
        ...prevBook.author,
        [name]: value
      }
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post('http://localhost:8080/books', book)
      .then(response => {
        onSuccess();
      })
      .catch(error => {
        setError('Error adding book. Please try again later.');
        console.error('Error adding book:', error);
      });
  };

  return (
    <div className="add-book-form">
      <h2>Add New Book</h2>
      {error && <p className="error-message">{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Book Name:</label>
          <input type="text" name="bookName" value={book.bookName} onChange={handleChange} required />
        </div>
        <div>
          <label>Price:</label>
          <input type="number" name="price" value={book.price} onChange={handleChange} required />
        </div>
        <div>
          <label>Quantity:</label>
          <input type="number" name="quantity" value={book.quantity} onChange={handleChange} required />
        </div>
        <div>
          <label>Description:</label>
          <textarea name="description" value={book.description} onChange={handleChange} />
        </div>
        <div>
          <label>Author Name:</label>
          <input type="text" name="aname" value={book.author.aname} onChange={handleAuthorChange} required />
        </div>
        <div>
          <label>Author Description:</label>
          <textarea name="description" value={book.author.description} onChange={handleAuthorChange} required />
        </div>
        <button type="submit">Add Book</button>
      </form>
    </div>
  );
}

export default AddBookForm;
