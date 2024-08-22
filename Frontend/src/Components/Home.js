import React from 'react';
import '../App.css'; // Import CSS for styling
// import bookImage from '../images/bk.jpg'; // Import the image
import '../Components/CSS/Home.css';
import Footer from '../Components/Footer';
import Header from '../Components/Header';

function Home() {
  return (
    <div>
      <Header></Header>
    <section className="home" id="home">
      {/* <div className="img">
        <img src={bookImage} alt="Book" />
      </div> */}
      <div className="content">
        <h1>Explore Stories</h1>
        <p>Dive into our collection and let every book take you on a new journey.</p>
        <a href="/Book" className="btn">Discover More</a>
      </div>
    </section>
     <Footer></Footer> {/* Add Footer component here */}
     </div>
  );
}

export default Home;
