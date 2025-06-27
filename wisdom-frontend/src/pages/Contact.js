import React, { useState } from "react";
import "../../styles/Contact.css";

const Contact = () => {
    const [message, setMessage] = useState("");
    const [subject, setSubject] = useState("");
    const [status, setStatus] = useState("");
    const user = localStorage.getItem("user");

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!user || !message || !subject) {
            setStatus("Please fill in all fields.");
            return;
        }

        const res = await fetch("https://wisdomacademy.ai/api/contact", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ userId: user, subject, message })
        });

        if (res.ok) {
            setStatus("Message sent successfully.");
            setMessage("");
            setSubject("");
        } else {
            setStatus("Failed to send message.");
        }
    };

    return (
        <div className="contact-container">
            <h2>Contact e-softworks, inc.</h2>
            <form onSubmit={handleSubmit} className="contact-form">
                <label>Subject:</label>
                <input 
                    type="text" 
                    value={subject} 
                    onChange={(e) => setSubject(e.target.value)} 
                    required 
                />

                <label>Message:</label>
                <textarea 
                    value={message} 
                    onChange={(e) => setMessage(e.target.value)} 
                    rows={5}
                    required 
                />

                <button type="submit">Send</button>
            </form>
            {status && <p className="status-message">{status}</p>}
        </div>
    );
};

export default Contact;
