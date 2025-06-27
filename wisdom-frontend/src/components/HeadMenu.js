import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./HeadMenu.css"; // Import the CSS file

const HeadMenu = () => {
    // State to track open dropdowns
    const [openDropdown, setOpenDropdown] = useState(null);
    const [showManageDropdown, setShowManageDropdown] = useState(false);
    const [showProfileDropdown, setShowProfileDropdown] = useState(false);

    // Toggle dropdown visibility
    const toggleDropdown = (menu) => {
        setOpenDropdown(openDropdown === menu ? null : menu);
    };

    return (
            <nav className="headmenu">
                {/* Left Section: Logo */}
                <div className="menu-logo">
                    <a href="/">
                        <img src="/pictures/wisdomacademylogo.jpeg" alt="Wisdom Logo" />
                    </a>
                </div>
            
                {/* Center Section: Navigation */}
                <ul className="menu-container">
                    {/* Learn Dropdown */}
                    <li className="dropdown">
                        <button onClick={() => toggleDropdown("learn")}>Learn ▼</button>
                        {openDropdown === "learn" && (
                            <ul className="dropdown-menu">
                                <li><a href="/courses">📖 Courses</a></li>
                                <li><a href="/assessments">📝 Assessments</a></li>
                                <li><a href="/learningplans">📅 Learning Plans</a></li>
                                <li><a href="/certifications">🎓 Certifications</a></li>
                            </ul>
                                    )}
                    </li>
            
                    {/* Mentorship Dropdown */}
                    <li className="dropdown">
                        <button onClick={() => toggleDropdown("mentorship")}>Mentorship ▼</button>
                        {openDropdown === "mentorship" && (
                            <ul className="dropdown-menu">
                                <li><a href="/mentor/find">👨‍🏫 Find a Mentor</a></li>
                                <li><a href="/mentor/become">👨‍🎓 Become a Mentor</a></li>
                                <li><a href="/mentor/sessions">💬 Mentor Sessions</a></li>
                            </ul>
                                    )}
                    </li>
            
                    {/* Marketplace Dropdown */}
                    <li className="dropdown">
                        <button onClick={() => toggleDropdown("marketplace")}>Marketplace ▼</button>
                        {openDropdown === "marketplace" && (
                            <ul className="dropdown-menu">
                                <li><a href="/marketplace/buytokens">🎟 Buy Tokens</a></li>
                                <li><a href="/marketplace/earnrewards">🎁 Earn Rewards</a></li>
                                <li><a href="/marketplace/mytransactions">📜 My Transactions</a></li>
                            </ul>
                                    )}
                    </li>
            
                    {/* Community Dropdown */}
                    <li className="dropdown">
                        <button onClick={() => toggleDropdown("community")}>Community ▼</button>
                        {openDropdown === "community" && (
                            <ul className="dropdown-menu">
                                <li><a href="/community/discussions">📢 Discussions</a></li>
                                <li><a href="/community/leaderboard">🏆 Leaderboard</a></li>
                                <li><a href="/community/badges">🏅 Badges</a></li>
                                <li><a href="/community/events">🎟 Events & Webinars</a></li>
                            </ul>
                        )}
                    </li>
                </ul>
            
                {/* Right Section: User Profile */}
                <div className="menu-right">
                    <ul className="menu-container">
                        <li className="dropdown">
                            <button onClick={() => toggleDropdown("manage")}>Manage ▼</button>
                            {openDropdown === "manage" && (
                                <ul className="dropdown-menu">
                                    <li><Link to="/manage/courses">Create/Edit Courses</Link></li>
                                    <li><Link to="/manage/learning-plans">Manage Learning Plans</Link></li>
                                    <li><Link to="/manage/assessments">Manage Assessments</Link></li>
                                    <li><Link to="/manage/certifications">Manage Certificates</Link></li>
                                    <li><Link to="/manage/events">Manage Events</Link></li>
                                    <li><Link to="/manage/badges">Manage Badges</Link></li>
                                </ul>
                                        )}
                        </li>
                    <li className="dropdown">
                    <Link to="/profile">My Account</Link>
                    </li>
                    <li className="dropdown">
                    <a href="/contact">Contact</a>
                    </li>
                    </ul>
                </div>
            </nav>
            );
};

export default HeadMenu;
