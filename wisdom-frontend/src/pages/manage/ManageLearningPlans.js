import React, { useEffect, useState } from "react";
import axios from "axios";
import "../../styles/Manage.css";

const ManageLearningPlans = () => {
    const [learningPlans, setLearningPlans] = useState([]);
    const [editingPlan, setEditingPlan] = useState(null);
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [categoryId, setCategoryId] = useState("");
    const [selectedCourses, setSelectedCourses] = useState([]);
    const [selectedAssessments, setSelectedAssessments] = useState([]);
    const [selectedBadges, setSelectedBadges] = useState([]);

    const [allCourses, setAllCourses] = useState([]);
    const [allAssessments, setAllAssessments] = useState([]);
    const [allBadges, setAllBadges] = useState([]);
    const [allCategories, setAllCategories] = useState([]);

    const userWalletAddress = localStorage.getItem("user");

    useEffect(() => {
        fetchLearningPlans();
        fetchAllResources();
        fetchCategories();
    }, []);

    const fetchLearningPlans = async () => {
        const res = await axios.get(`/api/learningplans/user/${userWalletAddress}`);
        setLearningPlans(res.data);
    };

    const fetchAllResources = async () => {
        const [courses, assessments, badges] = await Promise.all([
            axios.get("/api/courses"),
            axios.get("/api/assessments"),
            axios.get("/api/badges"),
        ]);
        setAllCourses(courses.data);
        setAllAssessments(assessments.data);
        setAllBadges(badges.data);
    };

    const fetchCategories = async () => {
        const res = await axios.get("/api/categories");
        setAllCategories(res.data);
    };

    const resetForm = () => {
        setTitle("");
        setDescription("");
        setCategoryId(-1);
        setSelectedCourses([]);
        setSelectedAssessments([]);
        setSelectedBadges([]);
        setEditingPlan(null);
    };

    const savePlan = async () => {
        const payload = {
            title,
            description,
            categoryId,
            courseIds: selectedCourses,
            assessmentIds: selectedAssessments,
            badgeIds: selectedBadges,
        };

        if (editingPlan) {
            await axios.put(`/api/learningplans/${editingPlan.id}`, payload);
        } else {
            await axios.post("/api/learningplans", {
                ...payload,
                createdBy: userWalletAddress,
            });
        }

        fetchLearningPlans();
        resetForm();
    };

    const deletePlan = async (id) => {
        await axios.delete(`/api/learningplans/${id}`);
        fetchLearningPlans();
    };

    const editPlan = (plan) => {
        setEditingPlan(plan);
        setTitle(plan.title);
        setDescription(plan.description || "");
        setCategoryId(plan.categoryId || "");
        setSelectedCourses(plan.courseIds || []);
        setSelectedAssessments(plan.assessmentIds || []);
        setSelectedBadges(plan.badgeIds || []);
    };

    const renderSelect = (items, selected, setSelected, label) => (
        <div className="form-group">
            <label>{label}</label>
            <select
                multiple
                value={selected}
                onChange={(e) => {
                    const options = [...e.target.options];
                    const values = options.filter(o => o.selected).map(o => o.value);
                    setSelected(values);
                }}
            >
                {items.map(item => (
                    <option key={item.id} value={item.id}>{item.title}</option>
                ))}
            </select>
        </div>
    );
    
    const renderSelectBadges = (items, selected, setSelected, label) => (
        <div className="form-group">
            <label>{label}</label>
            <select
                multiple
                value={selected}
                onChange={(e) => {
                    const options = [...e.target.options];
                    const values = options.filter(o => o.selected).map(o => o.value);
                    setSelected(values);
                }}
                className="fancy-dropdown"
                style={{ width: '100%', height: 'auto', minHeight: '120px' }}
            >
                {items.map(item => (
                    <option key={item.id} value={item.id}>
                        {item.description || item.title || `Item ${item.id}`}
                    </option>
                ))}
            </select>

            {/* Custom badge preview if rendering badges */}
            {label === "Badges" && (
                <div className="badge-preview-list" style={{ display: 'flex', flexWrap: 'wrap', gap: '10px', marginTop: '10px' }}>
                    {items
                        .filter(badge => selected.includes(badge.id.toString()))
                        .map(badge => (
                            <div key={badge.id} style={{ textAlign: 'center' }}>
                                <img
                                    src={badge.imageUrl}
                                    alt={badge.description}
                                    style={{ width: "60px", height: "60px", objectFit: "contain", borderRadius: "8px" }}
                                />
                                <div style={{ fontSize: "12px", marginTop: "5px" }}>{badge.description}</div>
                            </div>
                        ))}
                </div>
            )}
        </div>
    );

    return (
        <div className="manage-container">
            <h2>Manage Learning Plans</h2>

            <div className="form">
                <input
                    className="fancy-dropdown"
                    type="text"
                    placeholder="Plan Title"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    style={{ width: '100%', marginBottom: '15px' }}
                />

                <input
                    className="fancy-dropdown"
                    type="text"
                    placeholder="Description"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    style={{ width: '100%', marginBottom: '15px' }}
                />

                <div className="form-group">
                    <label>Category</label>
                    <select
                        value={categoryId}
                        onChange={(e) => setCategoryId(e.target.value)}
                        className="fancy-dropdown"
                        style={{ width: '100%', marginBottom: '15px' }}
                    >
                        <option value="">Select a category</option>
                        {allCategories.map(cat => (
                            <option key={cat.id} value={cat.id}>
                                {cat.name}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="fancy-select-group">
                    {renderSelect(allCourses, selectedCourses, setSelectedCourses, "Courses")}
                </div>

                <div className="fancy-select-group">
                    {renderSelect(allAssessments, selectedAssessments, setSelectedAssessments, "Assessments")}
                </div>

                <div className="fancy-select-group">
                    {renderSelectBadges(allBadges, selectedBadges, setSelectedBadges, "Badges")}
                </div>

                <div style={{ display: 'flex', justifyContent: 'center', gap: '10px', flexWrap: 'wrap' }}>
                    <button className="add-btn save-btn" onClick={savePlan}>
                        {editingPlan ? "Update" : "Add"} Plan
                    </button>
                    {editingPlan && (
                        <button className="delete-btn cancel-btn" onClick={resetForm}>
                            Cancel
                        </button>
                    )}
                </div>
            </div>

            <div className="plan-list" style={{ marginTop: '40px' }}>
                {learningPlans.map(plan => (
                    <div key={plan.id} className="item">
                        <div style={{ textAlign: 'left' }}>
                            <h4 style={{ margin: '0 0 5px' }}>{plan.title}</h4>
                        </div>
                        <div>
                            <button className="edit-btn" onClick={() => editPlan(plan)}>Edit</button>
                            <button className="delete-btn" onClick={() => deletePlan(plan.id)}>Delete</button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ManageLearningPlans;
