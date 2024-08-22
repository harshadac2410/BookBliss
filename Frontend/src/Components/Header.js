import React from 'react';
import { Link } from 'react-router-dom';
import '../App.css'; // Import CSS for styling

function Header() {
  return (
    <header>
      <a href="home.js" className="logo"><span>B</span>ook<span>B</span>liss</a>
      <nav className="navbar">
        <Link to="/">Home</Link>
        <Link to="/book">Book</Link>
        <Link to="/contact">Contact</Link>
        <Link to="/Login">Login</Link>
        {/* <Link to="/Signup">SignUp</Link> */}
      </nav>
    </header>
  );
}

export default Header;
