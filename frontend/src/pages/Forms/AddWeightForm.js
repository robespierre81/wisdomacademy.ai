import React, { useState, useEffect } from 'react';


const AddWeightForm = () => {
    const [formData, setFormData] = useState({weight: '', neck: '', belly: '', date: ''});

    useEffect(() => {
        const today = new Date().toISOString().split('T')[0]; // Format as YYYY-MM-DD
        setFormData((prevData) => ({...prevData, date: today}));
    }, []);

    const handleChange = (e) => {
        setFormData({...formData, [e.target.name]: e.target.value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        await fetch('http://e-softworks.consulting:8585/api/weights/add', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(formData),
        });
        alert('Weight entry added successfully');
    };

    return (
            <form onSubmit={handleSubmit} id="AddWeightForm">
                <label>
                    <span className="labelform">Weight (kg):</span>
                    <input type="number" name="weight" value={formData.weight} onChange={handleChange} required />
                </label><br />
                <label>
                    <span className="labelform">Neck (cm):</span>
                    <input type="number" name="neck" value={formData.neck} onChange={handleChange} />
                </label><br />
                <label>
                    <span className="labelform">Belly (cm):</span>
                    <input type="number" name="belly" value={formData.belly} onChange={handleChange} />
                </label><br />
                <label>
                    <span className="labelform">Date:</span>
                    <input type="date" name="date" value={formData.date} onChange={handleChange} required />
                </label><br />
                <button type="submit">Save Record</button>
            </form>
            );
};

export default AddWeightForm;