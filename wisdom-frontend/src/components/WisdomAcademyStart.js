import React, { useState, useEffect, useRef } from "react";

const ConsultingStart = () => {
    const [openDropdown, setOpenDropdown] = useState(null);
    const menuRef = useRef(null);
    const [quizAnswers, setQuizAnswers] = useState({ q1: "", q2: "", q3: "" }); // Quiz answers state
    const [quizResult, setQuizResult] = useState(null); // Quiz result state

    // Toggle dropdown visibility
    const toggleDropdown = (menu) => {
        setOpenDropdown(openDropdown === menu ? null : menu);
    };

    // Function to check if MetaMask is installed
    const checkMetaMask = () => {
        if (window.ethereum) {
            alert("MetaMask is installed! Please connect your wallet in the app.");
        } else {
            alert("MetaMask is not installed. Please install MetaMask to log in.");
        }
    };

    // Close dropdown on outside click
    useEffect(() => {
        const handleClickOutside = (event) => {
            if (menuRef.current && !menuRef.current.contains(event.target)) {
                setOpenDropdown(null);
            }
        };
        document.addEventListener("mousedown", handleClickOutside);
        return () => document.removeEventListener("mousedown", handleClickOutside);
    }, []);
    // Handle quiz submission
    const handleQuizSubmit = () => {
        const {q1, q2, q3} = quizAnswers;
        if (q1 && q2 && q3) {
            let result = "";
            if (q1 === "visual" && q2 === "fast" && q3 === "very") {
                result = "You’re a Visual Speed Learner! Our AI will deliver quick, image-rich lessons with real-time feedback.";
            } else if (q1 === "hands-on" && q2 === "moderate" && q3 === "somewhat") {
                result = "You’re a Practical Balancer! Our AI will craft interactive tasks at a steady pace with periodic check-ins.";
            } else if (q1 === "text" && q2 === "slow" && q3 === "not") {
                result = "You’re a Deep Reader! Our AI will provide detailed text-based content for self-paced mastery.";
            } else if (q1 === "visual" && q2 === "moderate" && q3 === "very") {
                result = "You’re a Visual Feedback Seeker! Our AI will blend visuals with steady progress and constant updates.";
            } else {
                result = "You’re a Flexible Explorer! Our AI will adapt a mix of styles to suit your unique learning journey.";
            }
            setQuizResult(result);
        } else {
            alert("Please answer all questions!");
        } 
    };

    return (
            <div>
                {/* Header Section */}
                <header className="headmenu" ref={menuRef} aria-label="Main Navigation">
                    {/* Logo */}
                    <div className="menu-logo">
                        <a href="/">
                            <img src="/pictures/wisdomacademylogo.jpeg" alt="Wisdom Logo" />
                        </a>
                    </div>
            
                    <nav className="nav-container">
                        <ul className="menu-container">
                            <li className="dropdown">
                                <a href="#about">About</a>
                            </li>
                            <li className="dropdown">
                                <button
                                    onClick={() => toggleDropdown("features")}
                                    aria-expanded={openDropdown === "features"}
                                    aria-haspopup="true"
                                    >
                                    Features <span className="dropdown-arrow">▼</span>
                                </button>
                                {openDropdown === "features" && (
                            <ul className="dropdown-menu" role="menu">
                                <li><a href="#features" role="menuitem">📚 Learning Plans</a></li>
                                <li><a href="#features" role="menuitem">🔍 Assessments</a></li>
                                <li><a href="#features" role="menuitem">🏆 Rewards</a></li>
                            </ul>
                                            )}
                            </li>
                            <li className="dropdown">
                                <a href="#approach">Our AI</a>
                            </li>
                            <li className="dropdown">
                                <a href="#contact">Contact</a>
                            </li>
                        </ul>
                    </nav>
            
                    <div className="menu-right">
                        <button className="login-btn" onClick={checkMetaMask}>
                            Login with MetaMask
                        </button>
                    </div>
                </header>
            
                {/* Hero Section */}
                <section id="hero" className="hero">
                    <div className="container">
                        <h2>Unleash Your Potential with AI & Blockchain</h2>
                        <p>Embark on a learning journey tailored just for you—guided by cutting-edge AI, secured by blockchain, and rewarded with tokens you can own.</p>
                    </div>
                </section>
            
            
                {/* About Wisdom Section */}
                <section id="wisdomacademy" className="wisdomacademy">
                    <div className="container">
                        <h2>About Wisdom Academy</h2>
                    </div>
                </section>
            
                <section id="about" className="about">
                    <div className="about-tile">
                        <h3>🚀 AI That Feels Like Magic</h3>
                        <p><b>Say goodbye to one-size-fits-all</b>. Our AI crafts a <b>learning adventure just for you</b>, matching your rhythm—whether you’re racing ahead or savoring every step.</p>
                    </div>
            
                    <div className="about-tile">
                        <h3>🔗 Credentials You Can Trust Forever</h3>
                        <p>Your victories deserve more than a pat on the back. With <b>blockchain’s unbreakable seal</b>, your achievements become <b>timeless proof of your brilliance</b>. Show the world what you’re made of.</p>
                    </div>
            
                    <div className="about-tile">
                        <h3>💰 Rewards That Fuel Your Fire</h3>
                        <p>Why just learn when you can <b>earn as you grow</b>? Every breakthrough unlocks <b>WSD Tokens</b>—yours to spend, trade, or celebrate. Feel the thrill of progress that pays off.</p>
                    </div>
            
                    <div className="about-tile">
                        <h3>🎓 Lead the Learning Revolution</h3>
                        <p>Your wisdom is power. <b>Shape your own courses</b>, inspire others, and earn rewards—all secured on the blockchain. Step into a community where <b>your knowledge shapes the future</b>.</p>
                    </div>
                </section>
            
                {/* Features Section */}
                <section id="features" className="features">
                    <div className="container">
                        <h2>Your Path to Mastery Starts Here</h2>
                        <ul>
                            <li>📚 <b>Journeys Built for You</b> – AI weaves lessons that evolve with every step you take.</li>
                            <li>🔍 <b>Unlock Your Strengths</b> – Smart quizzes pinpoint where you shine and lift you higher.</li>
                            <li>🏆 <b>Celebrate Every Win</b> – Earn <b>WSD Tokens</b> that prove your progress is real.</li>
                            <li>🎓 <b>Grow with Guidance</b> – Connect with mentors, human or AI, who light your way.</li>
                            <li>🛒 <b>Own Your Learning</b> – Trade <b>tokens</b> for exclusive wisdom that’s yours to keep.</li>
                        </ul>
            
                        {/* Interactive Mini-Quiz */}
                        <div className="quiz-section">
                            <h3>Find Your Learning Superpower Now</h3>
                            <div className="quiz-form">
                                <label>
                                    How do you prefer to learn new concepts?
                                    <select
                                        value={quizAnswers.q1}
                                        onChange={(e) => setQuizAnswers({...quizAnswers, q1: e.target.value})}
                                        >
                                        <option value="">Select...</option>
                                        <option value="visual">Through visuals (videos, diagrams)</option>
                                        <option value="text">Through reading and text</option>
                                        <option value="hands-on">Through practice and tasks</option>
                                    </select>
                                </label>
                                <label>
                                    How quickly do you like to progress?
                                    <select
                                        value={quizAnswers.q2}
                                        onChange={(e) => setQuizAnswers({...quizAnswers, q2: e.target.value})}
                                        >
                                        <option value="">Select...</option>
                                        <option value="fast">Quickly, key points only</option>
                                        <option value="moderate">Steady, balanced pace</option>
                                        <option value="slow">Slowly, with deep detail</option>
                                    </select>
                                </label>
                                <label>
                                    How important is feedback to you?
                                    <select
                                        value={quizAnswers.q3}
                                        onChange={(e) => setQuizAnswers({...quizAnswers, q3: e.target.value})}
                                        >
                                        <option value="">Select...</option>
                                        <option value="very">Very important</option>
                                        <option value="somewhat">Somewhat important</option>
                                        <option value="not">Not important</option>
                                    </select>
                                </label>
                                <button onClick={handleQuizSubmit} className="quiz-submit-btn">
                                    Get Your Style
                                </button>
                            </div>
                            {quizResult && <p className="quiz-result">{quizResult}</p>}
                        </div>
                    </div>
                </section>
            
                {/* AI-Powered Learning Approach */}
                <section id="approach" className="approach">
                    <div className="container">
                        <h2>AI That Knows You, Blockchain That Protects You</h2>
                        <p>Our AI bends to your unique rhythm, accelerating your growth—while blockchain locks in every triumph, forever yours.</p>
                    </div>
                </section>
            
                {/* Contact Section */}
                <section id="contact" className="contact">
                    <div className="container">
                        <h2>Step Into Your Future Now</h2>
                        <p>Ready to claim your place in the blockchain revolution? Join Wisdom Academy AI and start earning your legacy today!</p>
                        <p>Email: <a href="mailto:wisdomacademy@e-softworks.consulting">wisdomacademy@e-softworks.consulting</a></p>
                    </div>
                </section>
                
                {/* Impressum Section */}
                <section id="impressum" className="impressum" style={{ padding: "3rem 1rem", background: "#f8f8f8" }}>
                    <div className="container">
                        <h2>Imprint</h2>
                        <p><strong>Website Operator:</strong><br />
                        Wisdom Academy<br />
                        A project by e-Softworks, Inc.<br />
                        Oliver Bodemer<br />
                        Sacramento, CA, USA</p>

                        <p><strong>Contact:</strong><br />
                        Email: <a href="mailto:info@wisdomacademy.ai">wisdomacademy@e-softworks.consulting</a><br />
                        Website: <a href="https://www.wisdomacademy.ai">https://www.wisdomacademy.ai</a></p>

                        <p><strong>Company Information:</strong><br />
                        e-Softworks, Inc.<br />
                        Registered in Sacramento, California, USA<br />
                        Legal Form: Corporation (Inc.)<br />
                        CEO: Oliver Bodemer</p>

                        <p><strong>Responsible for Content (§ 18 Abs. 2 MStV):</strong><br />
                        Oliver Bodemer<br />
                        Sacramento, CA, USA</p>

                        <p><strong>Disclaimer:</strong><br />
                        The content of this website has been created with great care. However, we do not guarantee the accuracy, completeness, or current validity of the content. As a service provider, we are responsible for our own content on these pages under general laws. We are not obligated to monitor transmitted or stored external information or investigate circumstances indicating illegal activity.</p>

                        <p><strong>Copyright:</strong><br />
                        All content and works published on this website are subject to copyright laws. Any reproduction, processing, distribution, or use beyond what copyright law allows requires prior written consent from the author.</p>
                    </div>
                </section>

            
                {/* Footer Section */}
                <footer className="footer">
                    <div className="container">
                        <p>&copy; 2024 Wisdom AI Learning Platform by e-Softworks, Inc.. All rights reserved.</p>
                        <a href="#impressum" style={{ color: "#fff", textDecoration: "underline" }}>Imprint</a>
                    </div>
                </footer>
            </div>
            );
};

export default ConsultingStart;
