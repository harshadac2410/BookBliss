import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../Components/CSS/Login.css';

const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

function Login() {
  const [emailId, setEmailId] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('CUSTOMER');
  const [error, setError] = useState(null);
  const [emailError, setEmailError] = useState(null);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    // Validate email
    if (!emailRegex.test(emailId)) {
      setEmailError('Invalid email format.');
      return;
    } else {
      setEmailError(null);
    }
    try {
      const response = await axios.post('http://localhost:8080/user/login', null, {
        params: { emailId, password, role }
      });

      if (response.status === 200) {
        const { redirectUrl } = response.data;
        if (redirectUrl) {
          navigate(redirectUrl);
        } else {
          setError('Unexpected response from server.');
        }
      } else {
        setError('Unexpected response from server.');
      }
    } catch (err) {
      setError('Login failed. Please check your credentials.');
      console.error('Login error:', err);
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <div className="form-group">
          <label htmlFor="emailId">Email:</label>
          <input
            type="emailId"
            id="emailId"
            className={`form-control ${emailError ? 'is-invalid' : ''}`}
            value={emailId}
            onChange={(e) => setEmailId(e.target.value)}
            required
          />
            {emailError && <div className="invalid-feedback">{emailError}</div>}
        </div>
        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            className="form-control"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="role">Role:</label>
          <select
            id="role"
            className="form-control"
            value={role}
            onChange={(e) => setRole(e.target.value)}
          >
            <option value="CUSTOMER">Customer</option>
            <option value="ADMIN">Admin</option>
          </select>
        </div>
        <button type="submit" className="btn btn-primary">Login</button>
        {error && <p className="text-danger mt-2">{error}</p>}
      </form>
      <div className="mt-3">
        <a href="/forgot-password" className="btn btn-link">Forgot Password?</a><br></br>
        <div className="register-container">
          <span className="text-span">New to The BookBliss?</span>
          <a href="/Register" className="btn btn-link">Register</a>
        </div>
      </div>
    </div>
  );
}

export default Login;