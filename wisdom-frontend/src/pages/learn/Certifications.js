import React, { useEffect, useState } from "react";
import "../../styles/Certifications.css";

const Certifications = () => {
    const [earnedCerts, setEarnedCerts] = useState([]);
    const [availableCerts, setAvailableCerts] = useState([]);
    const [selectedCert, setSelectedCert] = useState(null);

    const wallet = localStorage.getItem("user");

    useEffect(() => {
        fetch(`https://wisdomacademy.ai/api/certifications/user/${wallet}`)
            .then(res => res.json())
            .then(data => setEarnedCerts(data));

        fetch(`https://wisdomacademy.ai/api/certifications/available`)
            .then(res => res.json())
            .then(data => setAvailableCerts(data));
    }, [wallet]);

    const handleViewRequirements = (certId) => {
        fetch(`https://wisdomacademy.ai/api/certifications/${certId}/requirements`)
            .then(res => res.json())
            .then(data => setSelectedCert({ ...data, id: certId }));
    };

    return (
        <div className="certification-container">
            <h1>Your Certifications</h1>
            {earnedCerts.length === 0 ? (
                <p>You don’t have any certifications yet.</p>
            ) : (
                <div className="certification-list">
                    {earnedCerts.map(cert => (
                        <div key={cert.id} className="cert-card">
                            <h2>{cert.title}</h2>
                            <p>{cert.description}</p>
                            <p><strong>Issued By:</strong> {cert.issuedBy}</p>
                            <p><strong>Issued On:</strong> {new Date(cert.issueDate).toLocaleDateString()}</p>
                        </div>
                    ))}
                </div>
            )}

            <h2>Discover Certifications</h2>
            <div className="certification-list">
                {availableCerts.map(cert => (
                    <div key={cert.id} className="cert-card">
                        <h3>{cert.title}</h3>
                        <p>{cert.description}</p>
                        <button onClick={() => handleViewRequirements(cert.id)}>View Requirements</button>
                        {selectedCert && selectedCert.id === cert.id && (
                            <div className="requirement-box">
                                <h4>Learning Plan</h4>
                                <ul>
                                    {selectedCert.learningPlan.map((item, index) => (
                                        <li key={index}>{item}</li>
                                    ))}
                                </ul>
                                <h4>Assessments</h4>
                                <ul>
                                    {selectedCert.assessments.map((test, index) => (
                                        <li key={index}>{test}</li>
                                    ))}
                                </ul>
                            </div>
                        )}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Certifications;
