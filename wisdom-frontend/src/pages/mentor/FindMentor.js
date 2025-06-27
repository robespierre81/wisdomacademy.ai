import React, { useEffect, useState } from "react";
import "../../styles/Mentorship.css";

const FindMentor = () => {
    const [mentors, setMentors] = useState([]);

    useEffect(() => {
        fetch("https://wisdomacademy.ai/api/mentors/available")
            .then(res => res.json())
            .then(setMentors);
    }, []);

    return (
        <div className="mentor-list">
            <h2>Available Mentors</h2>
            {mentors.length > 0 ? (
                mentors.map(mentor => (
                    <div className="mentor-card" key={mentor.id}>
                        <h3>{mentor.name}</h3>
                        <p><strong>Expertise:</strong> {mentor.expertise}</p>
                        <p>{mentor.bio}</p>
                        <p><strong>Contact:</strong> {mentor.contactEmail}</p>
                    </div>
                ))
            ) : (
                <div className="no-mentors">
                    <p>No mentors are currently available.</p>
                    <p>Would you like to <a href="/mentor/become">become a mentor</a> and help others on their journey?</p>
                </div>
            )}
        </div>
    );
};

export default FindMentor;