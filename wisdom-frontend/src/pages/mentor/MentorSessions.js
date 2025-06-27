import React, { useEffect, useState } from "react";
import "../../styles/Mentorship.css";

const MentorSessions = () => {
    const [sessions, setSessions] = useState([]);
    const userWallet = localStorage.getItem("user");

    useEffect(() => {
        fetch(`https://wisdomacademy.ai/api/mentor-sessions/user/${userWallet}`)
            .then(res => res.json())
            .then(setSessions);
    }, [userWallet]);

    return (
        <div className="mentor-sessions">
            <h2>Your Mentor Sessions</h2>
            {sessions.length > 0 ? (
                sessions.map(session => (
                    <div key={session.id} className="session-card">
                        <p><strong>Session ID:</strong> {session.id}</p>
                        <p><strong>Time:</strong> {new Date(session.sessionTime).toLocaleString()}</p>
                        <p><strong>Confirmed:</strong> {session.isConfirmed ? "✅ Yes" : "❌ No"}</p>
                    </div>
                ))
            ) : (
                <div className="no-sessions">
                    <p>No sessions available at the moment.</p>
                    <p>Check back later or <a href="/mentor/find">connect with a mentor</a> to schedule one.</p>
                </div>
            )}
        </div>
    );
};

export default MentorSessions;
