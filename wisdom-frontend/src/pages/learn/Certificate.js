import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import '../../styles/Certificate.css';

const Certificate = () => {
  const { id } = useParams();
  const [certificate, setCertificate] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`https://wisdomacademy.ai/api/certifications/${id}`)
      .then((res) => res.json())
      .then((data) => {
        setCertificate(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching certificate:", err);
        setLoading(false);
      });
  }, [id]);

  if (loading) return <p>Loading...</p>;
  if (!certificate) return <p>Certificate not found.</p>;

  return (
    <div className="certificate-container">
      <div className="certificate">
        <h1>Certificate of Completion</h1>
        <p className="subtitle">This certifies that</p>
        <h2 className="username">{certificate.userid}</h2>
        <p>has successfully completed the course</p>
        <h3 className="course-title">"{certificate.coursetitle}"</h3>
        <p className="date">Issued on: {certificate.issueddate}</p>
        <div className="signature-section">
          <div className="signature">
            <p>Olga Smirnov</p>
            <span>Director, Wisdom Academy</span>
          </div>
          <div className="signature">
            <p>AI Verification</p>
            <span>Blockchain Verified</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Certificate;