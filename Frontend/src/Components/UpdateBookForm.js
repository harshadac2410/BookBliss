import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../Components/CSS/UpdateBookForm.css'; // Ensure you have CSS file for styles

function UpdateBookForm({ bookId, onClose, onUpdate }) {
  const [price, setPrice] = useState('');
  const [quantity, setQuantity] = useState('');
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchBookDetails = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/books/${bookId}`);
        setPrice(response.data.price);
        setQuantity(response.data.quantity);
      } catch (err) {
        setError('Error fetching book details.');
        console.error('Error fetching book details:', err);
      }
    };

    fetchBookDetails();
  }, [bookId]);

  const handleUpdate = async (e) => {
    e.preventDefault();

    try {
      await axios.put(`http://localhost:8080/books/${bookId}`, { price: parseFloat(price),
        quantity: parseInt(quantity)});
      onUpdate(); // Notify parent component to refresh data
      onClose(); // Close the form
    } catch (err) {
      setError('Error updating book.');
      console.error('Error updating book:', err);
    }
  };

  return (
    <div className="update-book-form">
      <h3>Update Book</h3>
      {error && <p className="error-message">{error}</p>}
      <form onSubmit={handleUpdate}>
        <div className="form-group">
          <label htmlFor="price">Price:</label>
          <input
            type="number"
            id="price"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="quantity">Quantity:</label>
          <input
            type="number"
            id="quantity"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">Update Details</button>
        <button type="button" onClick={onClose} className="btn btn-secondary">Cancel</button>
      </form>
    </div>
  );
}

export default UpdateBookForm;
