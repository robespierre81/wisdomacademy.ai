import React, { useState, useEffect } from "react";
import axios from "axios";
import "../../styles/Manage.css";

const ManageBadges = () => {
    const [badges, setBadges] = useState([]);
    const [editingBadge, setEditingBadge] = useState(null);
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [iconUrl, setIconUrl] = useState("");
    const [points, setPoints] = useState(0);
    const [rank, setRank] = useState(0);

    useEffect(() => {
        fetchBadges();
    }, []);

    const fetchBadges = async () => {
        const res = await axios.get("/api/badges");
        setBadges(res.data);
    };

    const resetForm = () => {
        setTitle("");
        setDescription("");
        setIconUrl("");
        setPoints(0);
        setRank(0);
        setEditingBadge(null);
    };

    const saveBadge = async () => {
        const payload = {title, description, iconUrl, points, rank};
        if (editingBadge) {
            await axios.put(`/api/badges/${editingBadge.id}`, payload);
        } else {
            await axios.post("/api/badges", payload);
        }
        fetchBadges();
        resetForm();
    };

    const editBadge = (badge) => {
        setEditingBadge(badge);
        setTitle(badge.title);
        setDescription(badge.description);
        setIconUrl(badge.iconUrl);
        setPoints(badge.points || 0);
        setRank(badge.rank || 0);
    };

    const deleteBadge = async (id) => {
        await axios.delete(`/api/badges/${id}`);
        fetchBadges();
    };

    return (
            <div className="manage-container">
                <h2>Manage Badges</h2>
            
                <div className="form">
                    <input
                        className="fancy-dropdown"
                        type="text"
                        placeholder="Title"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        />
                    <input
                        className="fancy-dropdown"
                        type="text"
                        placeholder="Description"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        />
                    <input
                        className="fancy-dropdown"
                        type="text"
                        placeholder="Icon URL"
                        value={iconUrl}
                        onChange={(e) => setIconUrl(e.target.value)}
                        />
                    <label style={{marginTop: "10px", fontWeight: "bold"}}>
                        Points
                        <span className="info-icon" title="Points represent the value of this badge. Higher points can be used for gamification, rankings, or rewards.">
                            ℹ️
                        </span>
                    </label>
                    <input
                        className="fancy-dropdown"
                        type="number"
                        placeholder="Points"
                        value={points}
                        onChange={(e) => setPoints(Number(e.target.value))}
                        />
            
                    <label style={{marginTop: "10px", fontWeight: "bold"}}>
                        Rank
                        <span className="info-icon" title="Rank defines the level of the badge (e.g., 1 for Beginner, 5 for Expert). Higher rank means greater prestige.">
                            ℹ️
                        </span>
                    </label>
                    <input
                        className="fancy-dropdown"
                        type="number"
                        placeholder="Rank"
                        value={rank}
                        onChange={(e) => setRank(Number(e.target.value))}
                        />
            
            
                    <button className="add-btn save-btn" onClick={saveBadge}>
                        {editingBadge ? "Update" : "Add"} Badge
                    </button>
                    {editingBadge && (
                            <button className="delete-btn cancel-btn" onClick={resetForm}>
                                Cancel
                            </button>
                                )}
                </div>
            
                <div className="plan-list" style={{marginTop: "30px"}}>
                    {badges.map((b) => (
                                <div key={b.id} className="item">
                                    <div style={{textAlign: "left"}}>
                                        <h4>{b.title}</h4>
                                        <p>{b.description}</p>
                                        <img src={b.iconUrl} alt="badge-icon" width="50" height="50" />
                                        <div>Points: {b.points} | Rank: {b.rank}</div>
                                    </div>
                                    <div>
                                        <button className="edit-btn" onClick={() => editBadge(b)}>Edit</button>
                                        <button className="delete-btn" onClick={() => deleteBadge(b.id)}>Delete</button>
                                    </div>
                                </div>
                                    ))}
                </div>
            </div>
            );
};

export default ManageBadges;
