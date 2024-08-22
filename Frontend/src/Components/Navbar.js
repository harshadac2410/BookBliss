// import React from 'react';
// import { Navbar, Nav } from 'react-bootstrap';
// import { Link } from 'react-router-dom';
// // import 'bootstrap/dist/css/bootstrap.min.css';
// import '../Components/Navbar.css';

// function NavbarComponent() {
//   return (
//     <Navbar className="custom-navbar" expand="lg">
//       <Navbar.Brand as={Link} to="/">Boot Store</Navbar.Brand>
//       <Navbar.Toggle aria-controls="basic-navbar-nav" />
//       <Navbar.Collapse id="basic-navbar-nav">
//         <Nav className="mr-auto">
//           <Nav.Link as={Link} to="/">Home</Nav.Link>
//           <Nav.Link as={Link} to="/books">Books</Nav.Link>
//           <Nav.Link as={Link} to="/about-us">About Us</Nav.Link>
//           <Nav.Link as={Link} to="/contact-us">Contact Us</Nav.Link>
//         </Nav>
//         <Nav className="ml-auto">
//           <Nav.Link as={Link} to="/login">Login</Nav.Link>
//           <Nav.Link as={Link} to="/signup">Signup</Nav.Link>
//         </Nav>
//       </Navbar.Collapse>
//     </Navbar>
//   );
// }

// export default NavbarComponent;


// // import React from 'react';
// // import "../Components/Navbar.css"; // Import the CSS file for styling

// // const Navbar = () => {
// //     return (
// //         <nav className="navbar">
// //             <div className="navbar-brand">
// //                 <a href="#">BookBliss</a>
// //             </div>
// //             <ul className="navbar-nav">
// //                 <li><a href="#">Home</a></li>
// //                 <li><a href="#">About</a></li>
// //                 <li><a href="#">Book</a></li>
// //                 <li><a href="#">Contact</a></li>
// //                 <li><a href="#">Login</a></li>
// //                 <li><a href="#">SignUp</a></li>
// //             </ul>
// //         </nav>
// //     );
// // };

// // export default Navbar;

import React from 'react';
import { Link } from 'react-router-dom';
import '../Components/Navbar.css';

function NavbarComponent() {
  return (
    <nav className="navbar">
      <div className="navbar-brand">
        <Link to="/">Boot Store</Link>
      </div>
      <ul className="navbar-nav">
        <li className="nav-item">
          <Link to="/">Home</Link>
        </li>
        <li className="nav-item">
          <Link to="/books">Books</Link>
        </li>
        <li className="nav-item">
          <Link to="/about-us">About Us</Link>
        </li>
        <li className="nav-item">
          <Link to="/contact-us">Contact Us</Link>
        </li>
        <li className="nav-item">
          <Link to="/login">Login</Link>
        </li>
        <li className="nav-item">
          <Link to="/signup">Signup</Link>
        </li>
      </ul>
    </nav>
  );
}

export default NavbarComponent;
