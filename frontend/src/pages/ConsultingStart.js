import React from 'react';
import '../styles/ConsultingStart.css';

const ConsultingStart = () => {
    return (
        <div>
            <header className="header">
                <div className="container">
                    <h1 className="logo">e-Softworks, Inc.</h1>
                    <nav className="nav">
                        <ul>
                            <li><a href="#about">About Us</a></li>
                            <li><a href="#services">Services</a></li>
                            <li><a href="#approach">Our Approach</a></li>
                            <li><a href="#contact">Contact</a></li>
                        </ul>
                    </nav>
                </div>
            </header>

            <section id="hero" className="hero">
                <div className="container">
                    <h2>Innovative Blockchain & IT Consulting</h2>
                    <p>Empowering businesses with tailored technology solutions in Blockchain and Java.</p>
                </div>
            </section>

            <section id="about" className="about">
                <div className="container">
                    <h2>About Us</h2>
                    <p>e-Softworks, Inc. is an IT consulting firm specializing in Blockchain technology and Java-based solutions. We aim to empower businesses by providing expert consultation, strategic integration, and custom solutions that enhance efficiency, transparency, and security.</p>
                </div>
            </section>

            <section id="services" className="services">
                <div className="container">
                    <h2>Our Services</h2>
                    <ul>
                        <li>Blockchain Consulting & Implementation</li>
                        <li>Smart Contract Development</li>
                        <li>Java Application Development & Optimization</li>
                        <li>System Integration Services</li>
                        <li>Technical Training & Support</li>
                    </ul>
                </div>
            </section>

            <section id="approach" className="approach">
                <div className="container">
                    <h2>Our Approach</h2>
                    <p>Our commitment to innovation and client-focused strategies allows us to leverage Blockchain and Java technologies to deliver scalable, future-ready solutions. We focus on practical, secure, and customized technology consulting that meets the evolving needs of the IT landscape.</p>
                </div>
            </section>

            <section id="contact" className="contact">
                <div className="container">
                    <h2>Contact Us</h2>
                    <p>If you're ready to transform your business digitally, reach out to us for a consultation.</p>
                    <p>Email: <a href="mailto:info@e-softworks.consulting">info@e-softworks.consulting</a></p>
                </div>
            </section>

            <footer className="footer">
                <div className="container">
                    <p>&copy; 2024 e-Softworks, Inc. All rights reserved.</p>
                </div>
            </footer>
        </div>
    );
};

export default ConsultingStart;
