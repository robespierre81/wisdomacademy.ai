import React, { useState } from "react";
import "../../styles/Mentorship.css";

const BecomeMentor = () => {
    const [form, setForm] = useState({ name: "", bio: "", contactEmail: "", expertise: "" });

    const handleSubmit = async (e) => {
        e.preventDefault();
        const res = await fetch("https://wisdomacademy.ai/api/mentors/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(form),
        });
        const msg = await res.text();
        alert("Mentor registered!");
    };

    return (
        <div className="become-mentor">
            <h2>Become a Mentor</h2>
            <form onSubmit={handleSubmit}>
                <input placeholder="Name" onChange={e => setForm({...form, name: e.target.value})} />
                <input placeholder="Email" onChange={e => setForm({...form, contactEmail: e.target.value})} />
                <input placeholder="Expertise" onChange={e => setForm({...form, expertise: e.target.value})} />
                <textarea placeholder="Bio" onChange={e => setForm({...form, bio: e.target.value})}></textarea>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default BecomeMentor;
