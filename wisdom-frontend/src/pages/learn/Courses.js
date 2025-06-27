import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";

// Web3
import { ethers } from "ethers";
import tokenAbi from "../../blockchain/abis/InitialToken.json";
import courseAbi from "../../blockchain/abis/CourseManager.json";

// Styles
import "../../styles/Courses.css";

const Courses = () => {
    const [courses, setCourses] = useState([]);
    const [bookings, setBookings] = useState([]);
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState("");
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const userWalletAddress = localStorage.getItem("user");

    const tokenAddress = userWalletAddress; // Replace with actual token address
    const courseManagerAddress = "0xYourCourseManagerAddress"; // Replace with actual manager address

    useEffect(() => {
        const fetchCourses = async () => {
            try {
                const response = await fetch("https://wisdomacademy.ai/api/courses", {
                    method: "GET",
                    mode: "cors",
                    credentials: "include",
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    }
                });

                if (!response.ok) {
                    throw new Error(`Server Error: ${response.status} ${response.statusText}`);
                }

                const data = await response.json();
                setCourses(data);
            } catch (error) {
                console.error("Fetch Error:", error);
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchCourses();
    }, []);

    useEffect(() => {
        const fetchBookings = async () => {
            try {
                const response = await fetch("https://wisdomacademy.ai/api/bookings/user/" + userWalletAddress, {
                    method: "GET",
                    mode: "cors",
                    credentials: "include",
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    }
                });

                if (!response.ok) {
                    throw new Error(`Server Error: ${response.status} ${response.statusText}`);
                }

                const data = await response.json();
                setBookings(data);
            } catch (error) {
                console.error("Fetch Error:", error);
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        if (userWalletAddress) {
            fetchBookings();
        }
    }, [userWalletAddress]);

    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const response = await fetch("https://wisdomacademy.ai/api/categories", {
                    method: "GET",
                    mode: "cors",
                    credentials: "include",
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    }
                });
                const data = await response.json();
                setCategories(data);
            } catch (error) {
                console.error("Category Fetch Error:", error);
            }
        };

        fetchCategories();
    }, []);

    const buyCourse = async (courseId, coursePrice) => {
        try {
            const provider = new ethers.BrowserProvider(window.ethereum);
            const signer = await provider.getSigner();

            const token = new ethers.Contract(tokenAddress, tokenAbi, signer);
            const course = new ethers.Contract(courseManagerAddress, courseAbi, signer);

            const price = ethers.parseUnits(coursePrice.toString(), 18);

            // Approve CourseManager to spend tokens
            const tx1 = await token.approve(courseManagerAddress, price);
            await tx1.wait();

            // Buy course
            const tx2 = await course.buyCourse(courseId);
            await tx2.wait();

            alert("🎉 Course purchased!");
        } catch (err) {
            console.error("Buy Course Error:", err);
            alert("Error buying course. Check console for details.");
        }
    };

    const filterByCategory = course => {
        return selectedCategory === "" || course.category?.name === selectedCategory;
    };

    const twoWeeksAgo = new Date();
    twoWeeksAgo.setDate(twoWeeksAgo.getDate() - 14);

    const ongoingCourses = bookings.filter(course => course.bookingstatus === "ONGOING" && course.bookedby === userWalletAddress && filterByCategory(course));
    const completedCourses = bookings.filter(course => course.bookingstatus === "COMPLETED" && course.bookedby === userWalletAddress && filterByCategory(course));
    const bookedCourseIds = new Set(bookings.map(course => course.courseid));
    let availableCourses = courses.filter(course => !bookedCourseIds.has(course.id) && filterByCategory(course));
    const newCourses = availableCourses.filter(course => new Date(course.createdat) > twoWeeksAgo);
    const newCourseIds = new Set(newCourses.map(course => course.id));
    availableCourses = availableCourses
        .filter(course => !newCourseIds.has(course.id))
        .filter(course => !bookedCourseIds.has(course.id));

    return (
        <div className="courses-page">
            <h1>Courses</h1>

            <div className="category-filter">
                <label htmlFor="category">Filter by Category:</label>
                <select id="category" value={selectedCategory} onChange={e => setSelectedCategory(e.target.value)}>
                    <option value="">All Categories</option>
                    {categories.map(cat => (
                        <option key={cat.id} value={cat.name}>{cat.name}</option>
                    ))}
                </select>
            </div>

            <section>
                <h2>New Courses</h2>
                <div className="courses-list">
                    {courses.length > 0 ? newCourses.map(course => (
                        <CourseCard key={course.id} course={course} status="NEW" onBuy={buyCourse} />
                    )) : <p>No platform-provided courses available.</p>}
                </div>
            </section>

            <section>
                <h2>Available Courses</h2>
                <div className="courses-list">
                    {courses.length > 0 ? availableCourses.map(course => (
                        <CourseCard key={course.id} course={course} status="OTHERS" onBuy={buyCourse} />
                    )) : <p>No platform-provided courses available.</p>}
                </div>
            </section>

            <section>
                <h2>Ongoing Courses</h2>
                <div className="courses-list">
                    {courses.length > 0 ? ongoingCourses.map(course => (
                        <CourseCard key={course.id} course={course} status="ONGOING" />
                    )) : <p>No platform-provided courses available.</p>}
                </div>
            </section>

            <section>
                <h2>Completed Courses</h2>
                <div className="courses-list">
                    {courses.length > 0 ? completedCourses.map(course => (
                        <CourseCard key={course.id} course={course} status="COMPLETED" />
                    )) : <p>No platform-provided courses available.</p>}
                </div>
            </section>
        </div>
    );
};

const CourseCard = ({ course, status, onBuy }) => {
    if (!status) status = "PLATFORM";

    const description = course.description || course.coursedescription || "";
    const id = course.id || course.courseid || 0;
    const imageurl = course.imageurl || course.courseimage || "/images/default-course.jpeg";
    const title = course.title || course.coursetitle || "";
    const priceText = course.price || "0";

    const showPrice = (status.toLowerCase() !== "ongoing" && status.toLowerCase() !== "completed");
    const priceHtml = `<strong>Price:</strong> ${priceText} WSD`;

    return (
        <div className={`course-card ${status.toLowerCase()}`}>
            <img src={imageurl} alt={title} className="course-image" />
            <h3>{title}</h3>
            <p>{description}</p>
            <span className={`status-badge ${status.toLowerCase()}`}>{status}</span>
            {showPrice && <p dangerouslySetInnerHTML={{ __html: priceHtml }} />}
            <Link to={`/courses/${id}`} className="enroll-btn">View Course</Link>
            {showPrice && onBuy &&
                <button className="buy-btn" onClick={() => onBuy(id, priceText)}>Buy with WSD</button>
            }
        </div>
    );
};

export default Courses;
