// src/Components/OrderDetails.js

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../Components/CSS/OrderDetails.css'; // Optional: CSS file for styling

function OrderDetails() {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await axios.get('http://localhost:8080/order'); // Adjust URL if needed
        setOrders(response.data);
        setLoading(false);
      } catch (err) {
        setError('Error fetching orders.');
        setLoading(false);
        console.error('Error fetching orders:', err);
      }
    };

    fetchOrders();
  }, []);

  return (
    <div className="order-details">
      <h2>Order Details</h2>
      {loading && <p>Loading...</p>}
      {error && <p className="error-message">{error}</p>}
      {!loading && !error && (
        <table className="order-table">
          <thead>
            <tr>
              <th>Order ID</th>
              <th>Status</th>
              <th>Cart ID</th>
              <th>Price per Item</th>
            </tr>
          </thead>
          <tbody>
            {orders.length > 0 ? (
              orders.map(order => (
                <tr key={order.order_id}>
                  <td>{order.order_id}</td>
                  <td>{order.status}</td>
                  <td>{order.cart ? order.cart.cart_id : 'N/A'}</td>
                  <td>${order.price.toFixed(2)}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="4">No orders found.</td>
              </tr>
            )}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default OrderDetails;
