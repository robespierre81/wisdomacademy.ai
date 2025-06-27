import React, { useEffect, useState } from "react";
import axios from "axios";
import "../../styles/Manage.css";

const ManageEvents = () => {
    const [events, setEvents] = useState([]);
    const [editingEvent, setEditingEvent] = useState(null);
    const userWalletAddress = localStorage.getItem("user");
    const [formData, setFormData] = useState({
        title: "",
        description: "",
        speaker: "",
        startTime: "",
        endTime: "",
        registrationLink: ""
    });

    useEffect(() => {
        fetchEvents();
    }, []);

    const fetchEvents = async () => {
        const res = await axios.get("/api/events");
        setEvents(res.data);
    };

    const resetForm = () => {
        setFormData({
            title: "",
            description: "",
            speaker: "",
            startTime: "",
            endTime: "",
            registrationLink: "",
            userId: userWalletAddress
        });
        setEditingEvent(null);
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const saveEvent = async () => {
        const payload = {
            ...formData,
            startTime: new Date(formData.startTime),
            endTime: new Date(formData.endTime)
        };

        if (editingEvent) {
            await axios.put(`/api/events/${editingEvent.id}`, payload);
        } else {
            await axios.post("/api/events", payload);
        }

        fetchEvents();
        resetForm();
    };

    const deleteEvent = async (id) => {
        await axios.delete(`/api/events/${id}`);
        fetchEvents();
    };

    const editEvent = (event) => {
        setEditingEvent(event);
        setFormData({
            title: event.title || "",
            description: event.description || "",
            speaker: event.speaker || "",
            startTime: event.startTime?.slice(0, 16),
            endTime: event.endTime?.slice(0, 16),
            registrationLink: event.registrationLink || ""
        });
    };

    return (
        <div className="manage-container">
            <h2>Manage Events</h2>

            <div className="form">
                <input name="title" className="fancy-dropdown" type="text" placeholder="Title" value={formData.title} onChange={handleChange} />
                <input name="speaker" className="fancy-dropdown" type="text" placeholder="Speaker" value={formData.speaker} onChange={handleChange} />
                <input name="startTime" className="fancy-dropdown" type="datetime-local" value={formData.startTime} onChange={handleChange} />
                <input name="endTime" className="fancy-dropdown" type="datetime-local" value={formData.endTime} onChange={handleChange} />
                <textarea name="description" placeholder="Description" value={formData.description} onChange={handleChange} />
                <input name="registrationLink" className="fancy-dropdown" type="text" placeholder="Registration Link" value={formData.registrationLink} onChange={handleChange} />

                <div style={{ display: "flex", gap: "10px", marginTop: "15px" }}>
                    <button className="add-btn save-btn" onClick={saveEvent}>
                        {editingEvent ? "Update" : "Add"} Event
                    </button>
                    {editingEvent && (
                        <button className="delete-btn cancel-btn" onClick={resetForm}>Cancel</button>
                    )}
                </div>
            </div>

            <div className="plan-list" style={{ marginTop: "40px" }}>
                {events.map((event) => (
                    <div key={event.id} className="item">
                        <div style={{ textAlign: "left" }}>
                            <h4>{event.title}</h4>
                            <p>{event.speaker}</p>
                            <small>{new Date(event.startTime).toLocaleString()} – {new Date(event.endTime).toLocaleString()}</small>
                        </div>
                        <div>
                            <button className="edit-btn" onClick={() => editEvent(event)}>Edit</button>
                            <button className="delete-btn" onClick={() => deleteEvent(event.id)}>Delete</button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ManageEvents;
