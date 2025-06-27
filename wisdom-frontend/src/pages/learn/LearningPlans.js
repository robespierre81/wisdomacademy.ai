import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";

import "../../styles/LearningPlans.css";

const LearningPlans = () => {
    const [plans, setPlans] = useState([]);
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState("");
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetch("https://wisdomacademy.ai/api/learningplans")
            .then((res) => res.json())
            .then((data) => {
                setPlans(data);
                setLoading(false);
            });
    }, []);

    useEffect(() => {
        fetch("https://wisdomacademy.ai/api/categories")
            .then((res) => res.json())
            .then((data) => {
                setCategories(data);
            });
    }, []);

    const filteredPlans = plans.filter(plan => 
        selectedCategory === "" || plan.category?.name === selectedCategory
    );

    if (loading) return <div className="lp-loading">Loading learning plans...</div>;

    return (
        <div className="lp-container">
            <h1 className="lp-title">🎓 Available Learning Plans</h1>

            <div className="category-filter">
                <label htmlFor="category">Filter by Category:</label>
                <select id="category" value={selectedCategory} onChange={e => setSelectedCategory(e.target.value)}>
                    <option value="">All Categories</option>
                    {categories.map(cat => (
                        <option key={cat.id} value={cat.name}>{cat.name}</option>
                    ))}
                </select>
            </div>

            <div className="lp-grid">
                {filteredPlans.map((plan) => (
                    <Link to={`/learningplans/${plan.id}`} key={plan.id} className="lp-card">
                        <div className="lp-card-content">
                            <h2 className="lp-card-title">{plan.title}</h2>
                            <p className="lp-card-description">{plan.description}</p>
                            <p className="lp-card-creator">👤 Created by: <strong>{plan.createdBy}</strong></p>
                        </div>
                    </Link>
                ))}
            </div>
        </div>
    );
};

export default LearningPlans;
