import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { FaClipboardList, FaLock, FaCheckCircle } from "react-icons/fa";
import "../../styles/Assessments.css";

const Assessments = () => {
    const [assessments, setAssessments] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const userWalletAddress = localStorage.getItem("user");

    useEffect(() => {
        const fetchAssessments = async () => {
            try {
                const response = await fetch("https://wisdomacademy.ai/api/assessments");
                if (!response.ok) {
                    throw new Error("Failed to fetch assessments");
                }
                const data = await response.json();
                setAssessments(data);
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchAssessments();
    }, []);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error}</p>;
    
    console.log(assessments);

    return (
        <div className="assessments-overview">
            <h1>Available Assessments</h1>
            <div className="assessment-list">
                {assessments.length > 0 ? (
                    assessments.map((assessment) => (
                        <div key={assessment.id} className="assessment-card">
                            <FaClipboardList className="icon" />
                            <h2>{assessment.title}</h2>
                            <p>{assessment.description}</p>
                            <p><strong>Questions:</strong> {assessment.totalquestions}</p>
                            <p><strong>Price:</strong> {assessment.price} WSD</p>

                            {assessment.status === "purchased" ? (
                                <p className="owned">
                        <FaCheckCircle className="icon success" /><br /> Purchased
                                </p>
                            ) : (
                                <p className="locked">
                                    <FaLock className="icon lock" /><br /> Locked
                                </p>
                            )}

                            <Link to={`/assessmentdetails/${assessment.id}`} className="view-details-btn">
                                View Details
                            </Link>
                        </div>
                    ))
                ) : (
                    <p>No assessments available.</p>
                )}
            </div>
        </div>
    );
};

export default Assessments;
