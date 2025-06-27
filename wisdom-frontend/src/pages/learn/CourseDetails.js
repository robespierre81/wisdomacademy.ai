import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { FaVideo, FaHeadphones, FaFileAlt, FaDumbbell, FaCheckCircle } from "react-icons/fa";

// Web3
import { ethers } from "ethers";
import tokenAbi from "../../blockchain/abis/InitialToken.json";
import courseAbi from "../../blockchain/abis/CourseManager.json";

// Styles
import "../../styles/CourseDetails.css";

const CourseDetails = () => {
    const {courseId} = useParams();
    const [course, setCourse] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [buying, setBuying] = useState(false);

    const userWalletAddress = localStorage.getItem("user");

    const tokenAddress = "0x5FbDB2315678afecb367f032d93F642f64180aa3"; // Replace with actual token address
    const courseManagerAddress = "0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266"; // Replace with actual manager address

    useEffect(() => {
        const fetchCourseDetails = async () => {
            try {
                const response = await fetch(`https://wisdomacademy.ai/api/coursedetails/${courseId}`, {
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

                if (Array.isArray(data) && data.length > 0) {
                    setCourse(data[0]); // Extract the first object from the array
                } else {
                    setCourse(null);
                }
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchCourseDetails();
    }, [courseId]);
    
    
            
    const waitForReceipt = async (txHash, provider, retries = 15, delay = 1000) => {
    await new Promise(resolve => setTimeout(resolve, delay)); // small initial wait

    for (let i = 0; i < retries; i++) {
        try {
            const receipt = await provider.getTransactionReceipt(txHash);
            if (receipt && receipt.blockNumber) {
                return receipt;
            }
        } catch (err) {
            console.warn(`⚠️ Receipt not ready (attempt ${i + 1}):`, err.message);
        }

        await new Promise(resolve => setTimeout(resolve, delay));
    }

    throw new Error("⛔ Transaction indexing timeout after retries");
};

    const buyCourse = async (courseId, coursePrice) => {
        console.log(courseId);
        console.log(coursePrice);
        
        try {
            const provider = new ethers.BrowserProvider(window.ethereum);
            const signer = await provider.getSigner();

            const token = new ethers.Contract(tokenAddress, tokenAbi.abi, signer);
            const course = new ethers.Contract(courseManagerAddress, courseAbi.abi, signer);
            const price = ethers.parseUnits(coursePrice.toString(), 18);

            // Approve CourseManager to spend tokens
            const tx1 = await token.approve(courseManagerAddress, price);
            await waitForReceipt(tx1.hash, provider); // ✅ Wait with retry

            // Buy course
            console.log("Buying course ID:", courseId, typeof courseId);
            
            const tx2 = await course.buyCourse(ethers.toBigInt(courseId));
            await waitForReceipt(tx2.hash, provider); // 💪 This works even if indexing is slow

            alert("🎉 Course purchased!");
        } catch (err) {
            console.error("Buy Course Error:", err);
            alert("Error buying course. Check console for details.");
        }
    };


    const getLessonIcon = (type) => {
        switch (type) {
            case "video":
                return <FaVideo className="lesson-icon video" />;
            case "audio":
                return <FaHeadphones className="lesson-icon audio" />;
            case "presentation":
                return <FaFileAlt className="lesson-icon presentation" />;
            case "exercise":
                return <FaDumbbell className="lesson-icon exercise" />;
            case "assessment":
                return <FaCheckCircle className="lesson-icon assessment" />;
            default:
                return null;
        }
    };

    if (loading)
        return <p>Loading...</p>;
    if (error)
        return <p>Error: {error}</p>;
    if (!course)
        return <p>No course data available.</p>;

    const priceText = course?.course_price || course?.price || "0";
    const priceHtml = `<strong>Price:</strong> ${priceText} WSD`;

    return (
            <div className="course-details">
                <h1>{course?.course_title}</h1>
                <p>{course?.course_description}</p>
            
                {course.lessons && course.lessons.length > 0 && (
                                    <p><strong>Total Duration:</strong> {
                                                course.lessons.reduce((total, lesson) => total + lesson.lesson_duration, 0)
                                        } minutes</p>
                                    )}
            
                {course?.booked_by ? (
                            <div>
                                <p><strong>Progress:</strong> {course?.completion_percentage ?? 0}%</p>
                                <div className="progress-bar">
                                    <div className="progress" style={{width: `${course?.completion_percentage ?? 0}%`}}></div>
                                </div>
                            </div>
                            ) : (
                            <div>
                                <p><strong>Price:</strong> {course?.course_price} WSD</p>
                                <button className="buy-btn" onClick={() => buyCourse(course.course_id, priceText)}>Buy with WSD</button>
                            </div>
                            )}
            
                <h2>Lessons</h2>
                <div className="lessons-list">
                    {course?.lessons && course.lessons.length > 0 ? (
                                course.lessons.map((lesson) => (
                                        <div key={lesson.lesson_id} className={`lesson-card ${lesson.lesson_status}`}>
                                            {getLessonIcon(lesson.lesson_type)}
                                            <h3>{lesson.lesson_title}</h3>
                                            <p><strong>Duration:</strong> {lesson.lesson_duration} mins</p>
                                            <p><strong>Status:</strong> {lesson.lesson_status}</p>
                                        </div>
                                            ))
                                ) : (
                            <p>No lessons available yet.</p>
                                )}
                </div>
            </div>
            );
};

export default CourseDetails;
