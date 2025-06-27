import React, { useState, useEffect } from "react";
import axios from "axios";
import "../../styles/Manage.css";

const ManageCertifications = () => {
    const [certifications, setCertifications] = useState([]);
    const [editingCert, setEditingCert] = useState(null);
    const [coursetitle, setCoursetitle] = useState("");
    const [description, setDescription] = useState("");
    const [imageUrl, setImageUrl] = useState("");

    // Replace with your method of retrieving the current user ID
    const userId = localStorage.getItem("user");

    useEffect(() => {
        fetchCertifications();
    }, []);

    const fetchCertifications = async () => {
        const res = await axios.get("/api/certifications");
        setCertifications(res.data);
    };

    const resetForm = () => {
        setCoursetitle("");
        setDescription("");
        setImageUrl("");
        setEditingCert(null);
    };

    const saveCertification = async () => {
        const payload = {
            coursetitle,
            description,
            imageUrl,
            userId
        };

        if (editingCert) {
            await axios.put(`/api/certifications/${editingCert.id}`, payload);
        } else {
            await axios.post("/api/certifications", payload);
        }

        fetchCertifications();
        resetForm();
    };

    const deleteCertification = async (id) => {
        await axios.delete(`/api/certifications/${id}`);
        fetchCertifications();
    };

    const editCertification = (cert) => {
        setEditingCert(cert);
        setCoursetitle(cert.coursetitle);
        setDescription(cert.description);
        setImageUrl(cert.imageUrl || "");
    };

    return (
        <div className="manage-container">
            <h2>Manage Certifications</h2>

            <div className="form">
                <input
                    type="text"
                    placeholder="Course Title"
                    value={coursetitle}
                    onChange={(e) => setCoursetitle(e.target.value)}
                    style={{ marginBottom: "10px", width: "100%" }}
                />

                <textarea
                    placeholder="Description"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    style={{ marginBottom: "10px", width: "100%" }}
                />

                <input
                    type="text"
                    placeholder="Image URL (optional)"
                    value={imageUrl}
                    onChange={(e) => setImageUrl(e.target.value)}
                    style={{ marginBottom: "10px", width: "100%" }}
                />

                <div style={{ display: "flex", gap: "10px", marginBottom: "20px" }}>
                    <button className="add-btn save-btn" onClick={saveCertification}>
                        {editingCert ? "Update" : "Add"} Certification
                    </button>
                    {editingCert && (
                        <button className="delete-btn cancel-btn" onClick={resetForm}>
                            Cancel
                        </button>
                    )}
                </div>
            </div>

            <div className="plan-list">
                {certifications.map((cert) => (
                    <div key={cert.id} className="item">
                        <div style={{ textAlign: "left" }}>
                            <h4>{cert.coursetitle}</h4>
                            <p>{cert.description}</p>
                            {cert.imageUrl && (
                                <img src={cert.imageUrl} alt={cert.coursetitle} style={{ maxWidth: "100px", marginTop: "10px" }} />
                            )}
                        </div>
                        <div>
                            <button className="edit-btn" onClick={() => editCertification(cert)}>Edit</button>
                            <button className="delete-btn" onClick={() => deleteCertification(cert.id)}>Delete</button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ManageCertifications;
