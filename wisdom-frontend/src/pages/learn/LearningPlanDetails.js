import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

import "../../styles/LearningPlans.css";

const LearningPlanDetails = () => {
    const { id } = useParams();
    const [plan, setPlan] = useState(null);
    const [loading, setLoading] = useState(true);
    const userWalletAddress = localStorage.getItem("user");

    useEffect(() => {
        fetch(`https://wisdomacademy.ai/api/learningplans/${id}`)
            .then((res) => res.json())
            .then((data) => {
                setPlan(data);
                setLoading(false);
            });
    }, [id]);

    if (loading) return <p>Loading...</p>;
    if (!plan) return <p>Learning Plan not found.</p>;
    
    const courseTotal = plan.courses.reduce((sum, c) => sum + parseFloat(c.price), 0);
    const assessmentTotal = plan.assessments.reduce((sum, a) => sum + parseFloat(a.price), 0);
    const fullPrice = courseTotal + assessmentTotal;
    const discountedPrice = (fullPrice * 0.9).toFixed(2);

    const handleBuyPlan = async () => {
        const response = await fetch(`https://wisdomacademy.ai/api/learningplans/buy/${id}?walletAddress=${userWalletAddress}`, {
            method: "POST",
        });

        const message = await response.text();
        alert(message);
    };

    return (
        <div className="lp-details-container">
            <div className="lp-header">
                <h1>{plan.title}</h1>
                <p className="lp-subtitle">{plan.description}</p>
                <p className="lp-creator">👤 Created by: <strong>{plan.createdBy}</strong></p>
            </div>

            <div className="lp-section">
                <h2>📚 Courses</h2>
                <ul className="lp-list">
                    {plan.courses.map(course => (
                        <li key={course.id}>
                            <span className="lp-item-title">{course.title}</span>
                            <span className="lp-price">{course.price} WSD</span>
                        </li>
                    ))}
                </ul>
            </div>

            <div className="lp-section">
                <h2>📝 Assessments</h2>
                <ul className="lp-list">
                    {plan.assessments.map(assessment => (
                        <li key={assessment.id}>
                            <span className="lp-item-title">{assessment.title}</span>
                            <span className="lp-price">{assessment.price} WSD</span>
                        </li>
                    ))}
                </ul>
            </div>

            <div className="lp-summary">
                <p>Total before discount: <strong>{fullPrice.toFixed(2)} WSD</strong></p>
                <p>Discounted Price: <strong className="lp-discount">{discountedPrice} WSD</strong></p>
                <button className="lp-buy-button" onClick={handleBuyPlan}>
                    🛒 Buy This Learning Plan
                </button>
            </div>
        </div>
    );
};

export default LearningPlanDetails ;
