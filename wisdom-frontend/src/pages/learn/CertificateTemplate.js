import React from "react";
import "../../styles/CertificateTemplate.css";

const CertificateTemplate = ({ courseTitle, userName, issuedDate }) => {
    return (
        <div className="certificate-container">
            <div className="certificate-border">
                <div className="certificate-inner">
                    <h1 className="certificate-title">Certificate of Completion</h1>
                    <p className="certificate-subtitle">This certifies that</p>
                    <h2 className="certificate-name">{userName}</h2>
                    <p className="certificate-subtitle">has successfully completed</p>
                    <h3 className="certificate-course">"{courseTitle}"</h3>
                    <p className="certificate-date">Issued on {issuedDate}</p>
                    <div className="certificate-signature">
                        <div className="line"></div>
                        <p className="signature-text">Wisdom Academy</p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default CertificateTemplate;