import React, { useEffect, useState } from "react";

const UserCertificates = ({ walletAddress }) => {
    const [certificates, setCertificates] = useState([]);
    const userWalletAddress = localStorage.getItem("user");
    
    useEffect(() => {
        if (userWalletAddress) {
            fetch(`http://wisdomacademy.ai/api/certifications/user/${userWalletAddress}`)
                .then(res => res.json())
                .then(setCertificates);
        }
    }, [walletAddress]);

    if (!walletAddress) return <p>Please connect your wallet.</p>;

    console.log(certificates);
    
    return (
        <div className="user-certificates">
            <h2>Your Certificates</h2>
            {certificates.length === 0 ? (
                <p>No certificates yet.</p>
            ) : (
                <ul>
                    {certificates.map(cert => (
                        <li key={cert.id}>
                            <a href={`/certificates/${cert.id}`} target="_blank" rel="noreferrer">
                                {cert.coursetitle} - {cert.issueddate}
                            </a>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default UserCertificates;