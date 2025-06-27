import React, { useState, useEffect } from "react";
import axios from "axios";
import "../../styles/Manage.css";

const ManageAssessments = () => {
    const [assessments, setAssessments] = useState([]);
    const [editingAssessment, setEditingAssessment] = useState(null);
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [questions, setQuestions] = useState([]);

    useEffect(() => {
        fetchAssessments();
    }, []);

    const fetchAssessments = async () => {
        const res = await axios.get("/api/assessments");
        setAssessments(res.data);
    };

    const resetForm = () => {
        setTitle("");
        setDescription("");
        setQuestions([]);
        setEditingAssessment(null);
    };

    const saveAssessment = async () => {
        const payload = {
            title,
            description,
            totalQuestions: questions.length,
            questions: questions.map(q => ({
                text: q.text,
                answers: q.answers.map(a => ({
                    text: a.text,
                    correct: a.correct
                }))
            }))
        };

        if (editingAssessment) {
            await axios.put(`/api/assessments/${editingAssessment.id}`, payload);
        } else {
            await axios.post("/api/assessments", payload);
        }

        fetchAssessments();
        resetForm();
    };

    const editAssessment = (assessment) => {
        setEditingAssessment(assessment);
        setTitle(assessment.title);
        setDescription(assessment.description);
        setQuestions(
            (assessment.questions || []).map(q => ({
                text: q.text,
                newAnswerText: "",
                answers: (q.answers || []).map(a => ({
                    text: a.text || "",
                    correct: a.correct || false
                }))
            }))
        );
    };

    const deleteAssessment = async (id) => {
        await axios.delete(`/api/assessments/${id}`);
        fetchAssessments();
    };

    const addQuestion = () => {
        setQuestions([
            ...questions,
            {
                text: "",
                newAnswerText: "",
                answers: [{ text: "", correct: false }]
            }
        ]);
    };

    const updateQuestionText = (qIndex, value) => {
        const updated = [...questions];
        updated[qIndex].text = value;
        setQuestions(updated);
    };

    const addAnswer = (qIndex) => {
        const updated = [...questions];
        if (updated[qIndex].answers.length < 6) {
            updated[qIndex].answers.push({ text: "", correct: false });
            setQuestions(updated);
        }
    };

    const updateAnswerText = (qIndex, aIndex, value) => {
        const updated = [...questions];
        updated[qIndex].answers[aIndex].text = value;
        setQuestions(updated);
    };

    const toggleAnswerCorrect = (qIndex, aIndex) => {
        const updated = [...questions];
        updated[qIndex].answers[aIndex].correct = !updated[qIndex].answers[aIndex].correct;
        setQuestions(updated);
    };

    const removeAnswer = (qIndex, aIndex) => {
        const updated = [...questions];
        updated[qIndex].answers.splice(aIndex, 1);
        setQuestions(updated);
    };

    const updateNewAnswerText = (qIndex, value) => {
        const updated = [...questions];
        updated[qIndex].newAnswerText = value;
        setQuestions(updated);
    };

    const submitNewAnswer = (qIndex) => {
        const updated = [...questions];
        const newText = updated[qIndex].newAnswerText.trim();

        if (newText && updated[qIndex].answers.length < 6) {
            updated[qIndex].answers.push({ text: newText, correct: false });
            updated[qIndex].newAnswerText = "";
            setQuestions(updated);
        }
    };

    const renderQuestions = () => (
        <div style={{ marginTop: "20px" }}>
            <h3>Questions</h3>
            {questions.map((q, qIndex) => (
                <div key={qIndex} className="question-block" style={{ marginBottom: "20px" }}>
                    <input
                        className="fancy-dropdown"
                        type="text"
                        placeholder={`Question ${qIndex + 1}`}
                        value={q.text}
                        onChange={(e) => updateQuestionText(qIndex, e.target.value)}
                        style={{ width: '100%', marginBottom: '10px' }}
                    />
                    {(q.answers || []).map((a, aIndex) => (
                        <div key={aIndex} style={{ display: "flex", alignItems: "center", gap: "10px", marginBottom: "5px" }}>
                            <input
                                type="text"
                                placeholder={`Answer ${aIndex + 1}`}
                                value={a?.text ?? ""}
                                onChange={(e) => updateAnswerText(qIndex, aIndex, e.target.value)}
                                style={{ flex: 1 }}
                            />
                            <label style={{ display: "flex", alignItems: "center", gap: "5px" }}>
                                <input
                                    type="checkbox"
                                    checked={a?.correct ?? false}
                                    onChange={() => toggleAnswerCorrect(qIndex, aIndex)}
                                />
                                Correct
                            </label>
                            <button className="delete-btn" onClick={() => removeAnswer(qIndex, aIndex)}>X</button>
                        </div>
                    ))}
                    {q.answers.length < 6 && (
                        <div style={{ display: "flex", gap: "10px", marginBottom: "10px", marginTop: "5px" }}>
                            <input
                                className="fancy-dropdown"
                                type="text"
                                placeholder="New answer..."
                                value={q.newAnswerText || ""}
                                onChange={(e) => updateNewAnswerText(qIndex, e.target.value)}
                                onKeyDown={(e) => {
                                    if (e.key === "Enter") submitNewAnswer(qIndex);
                                }}
                                style={{ flex: 1 }}
                            />
                            <button className="add-btn" onClick={() => submitNewAnswer(qIndex)}>Add</button>
                        </div>
                    )}
                    <hr />
                </div>
            ))}
            <button className="add-btn" onClick={addQuestion}>+ Add Question</button>
        </div>
    );

    return (
        <div className="manage-container">
            <h2>Manage Assessments</h2>

            <div className="form">
                <input
                    className="fancy-dropdown"
                    type="text"
                    placeholder="Assessment Title"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    style={{ marginBottom: "10px" }}
                />

                <input
                    className="fancy-dropdown"
                    type="text"
                    placeholder="Description"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    style={{ marginBottom: "10px" }}
                />

                {renderQuestions()}

                <div style={{ display: "flex", gap: "10px", marginTop: "20px" }}>
                    <button className="add-btn save-btn" onClick={saveAssessment}>
                        {editingAssessment ? "Update" : "Add"} Assessment
                    </button>
                    {editingAssessment && (
                        <button className="delete-btn cancel-btn" onClick={resetForm}>
                            Cancel
                        </button>
                    )}
                </div>
            </div>

            <div className="plan-list" style={{ marginTop: "40px" }}>
                {assessments.map((a) => (
                    <div key={a.id} className="item">
                        <div style={{ textAlign: "left" }}>
                            <h4 style={{ margin: "0 0 5px" }}>{a.title}</h4>
                            <p style={{ margin: 0 }}>{a.description}</p>
                            <small>{a.totalQuestions || (a.questions?.length || 0)} questions</small>
                        </div>
                        <div>
                            <button className="edit-btn" onClick={() => editAssessment(a)}>Edit</button>
                            <button className="delete-btn" onClick={() => deleteAssessment(a.id)}>Delete</button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ManageAssessments;
