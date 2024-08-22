// src/Components/PaymentDetails.js

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './CSS/PaymentDetails.css'; // Ensure you have the CSS file for styles

function PaymentDetails() {
  const [payments, setPayments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios.get('http://localhost:8080/payments') // Replace with your API URL
      .then(response => {
        setPayments(response.data);
        setLoading(false);
      })
      .catch(error => {
        setError('Error fetching payment details. Please try again later.');
        setLoading(false);
        console.error('Error fetching payments:', error);
      });
  }, []);

  return (
    <div className="payment-details-container">
      <h2>Payment Details</h2>
      {loading && <p>Loading...</p>}
      {error && <p className="error-message">{error}</p>}
      {!loading && !error && (
        <table className="payment-table">
          <thead>
            <tr>
              <th>Payment ID</th>
              <th>Order ID</th>
              <th>User ID</th>
              <th>Payment Status</th>
              <th>Payment Mode</th>
            </tr>
          </thead>
          <tbody>
            {payments.map(payment => (
              <tr key={payment.payment_id}>
                <td>{payment.payment_id}</td>
                <td>{payment.orderId}</td>
                <td>{payment.userId}</td>
                <td>{payment.paymentStatus}</td>
                <td>{payment.mode}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default PaymentDetails;
