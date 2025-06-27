import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import "../../styles/AssessmentDetails.css";

const AssessmentDetails = () => {
    const { assessmentId } = useParams();
    const [assessment, setAssessment] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const userWalletAddress = localStorage.getItem("user");

    useEffect(() => {
        fetch(`https://wisdomacademy.ai/api/assessmentdetails/${assessmentId}`)
            .then(response => response.json())
            .then(data => {
                setAssessment(data);
                setLoading(false);
            })
            .catch(err => {
                setError(err.message);
                setLoading(false);
            });
    }, [assessmentId]);

    const handleBuyAssessment = async () => {
        const response = await fetch(`https://wisdomacademy.ai/api/assessment-purchases/buy/${assessmentId}?walletAddress=${userWalletAddress}`, {
            method: "POST",
        });

        const message = await response.text();
        alert(message);
    };

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error}</p>;
    if (!assessment) return <p>No assessment found.</p>;

    return (
        <div className="assessment-details">
            <h1>{assessment.title}</h1>
            <p>{assessment.description}</p>
            <p><strong>Price:</strong> {assessment.price} WSD</p>
            <p><strong>Total Questions:</strong> {assessment.totalQuestions}</p>

            <button onClick={handleBuyAssessment}>Buy Assessment</button>
        </div>
    );
};

export default AssessmentDetails;
