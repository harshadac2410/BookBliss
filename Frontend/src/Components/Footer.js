// import React from 'react';
// // import '../App.css'; // Import CSS for styling
// import '../Components/CSS/Footer.css';

// function Footer() {
//   return (
//     <section className="footer">
//       <div className="box-container">
//         <div className="box">
//           <h3>About Us</h3>
//           <p>At Boot Store, we’re passionate about bringing you the best in literature.
// From timeless classics to the latest bestsellers, we have something for every reader.</p>
//         </div>
//         <div className="box">
//           <h3>Branch Location</h3>
//           <p>Aurangabad, Maharashtra, India</p>
//         </div>
//         <div className="box">
//           <h3>Quick Links</h3>
//           <a href="home.html">Home</a>
//           <a href="book.html">Book</a>
//           <a href="contact.html">Contact</a>
//           <a href="Login.js">Login</a>
//           <a href="Signup.js">Signup</a>
//         </div>
//       </div>
//       <h1 className="credit">Created by <span>Harshada Chaudhari</span> | All rights reserved!</h1>
//     </section>
//   );
// }

// export default Footer;

import React from 'react';
// import './CSS/Footer.css';
import '../App.css';

function Footer() {
  return (
    <section className="footer">
      <div className="box-container">
        <div className="box">
          <h4>About Us</h4>
          <p>Dive into our collection and let every book take you on a new journey.</p>
        </div>
        <div className="box">
          <h4>Branch Location</h4>
          <p>Aurangabad, Maharashtra, India</p>
        </div>
        {/* <div className="box">
          <h4>Quick Links</h4>
          <a href="/home">Home</a>
          <a href="/book">Book</a>
          <a href="/contact">Contact</a>
          <a href="/login">Login</a>
        </div> */}
      </div>
      <hr></hr>
      <h4 className="credit">
        Created by <span>Harshada Chaudhari & Utkarsha Kadlag</span> | All rights reserved!
      </h4>
    </section>
  );
}

export default Footer;
