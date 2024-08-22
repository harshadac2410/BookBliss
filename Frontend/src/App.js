import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
// import Header from './Components/Header';
import Home from './Components/Home';
import Login from './Components/Login';
import Book from './Components/Book';
import 'bootstrap/dist/css/bootstrap.min.css'; // Include Bootstrap if needed
import ForgotPassword from '../src/Components/ForgetPass';
import Register from '../src/Components/Register';
import CustomerDashboard from './Components/CustomerDashboard';
import BooksPage from './Components/BooksPage';
import AdminDashboard from './Components/AdminDashboard';
import CartPage from './Components/CartPage';

function App() {
  return (
    <Router>
      {/* <Header /> */}
      <main>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/Book" element={<Book />} />
          <Route path="/Login" element={<Login />} />
          <Route path="/forgot-password" element={<ForgotPassword />} />
          <Route path="/Register" element={<Register />} />
          <Route path="/Customer" element={<CustomerDashboard />} />
          {/* Add routes for other components like Book, Packages, etc. */}
          <Route path="/BooksPage" element={<BooksPage />} />
          <Route path="/admin-dashboard" element={<AdminDashboard />} />
          <Route path="/CartPage" element={<CartPage />} />
        </Routes>
      </main>
      {/* <Footer /> */}
    </Router>
  );
}

export default App;
