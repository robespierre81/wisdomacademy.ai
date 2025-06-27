import React, { useState, useEffect } from "react";
import axios from "axios";
import "../../styles/Manage.css";

const initialCourse = {
    name: "",
    description: "",
    lessons: [],
    assessments: [],
    videos: [],
    audios: [],
    trainings: [],
};

const ManageCourses = () => {
    const [courses, setCourses] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [formData, setFormData] = useState(initialCourse);
    const [editingIndex, setEditingIndex] = useState(null);

    const [newCourse, setNewCourse] = useState({
        title: "",
        description: "",
        categoryId: "",
        price: "",
        imageUrl: "",
        lessons: []
    });

    const [categories, setCategories] = useState([]);
    const userWalletAddress = localStorage.getItem("user");

    useEffect(() => {
        axios
                .get(`/api/courses/my-courses?userId=${userWalletAddress}`)
                .then((res) => setCourses(res.data))
                .catch((err) => console.error("Failed to fetch courses", err));
    }, []);

    useEffect(() => {
        axios
                .get("/api/categories")
                .then((res) => {
                    console.log("Fetched categories:", res.data);
                    setCategories(res.data);
                })
                .catch((err) => console.error("Failed to fetch categories", err));
    }, []);

    const handleLessonTypeChange = (index, type) => {
        const updatedLessons = [...formData.lessons];
        updatedLessons[index].type = type;
        setFormData({...formData, lessons: updatedLessons});
    };

    const handleLessonTitleChange = (index, title) => {
        const updatedLessons = [...formData.lessons];
        updatedLessons[index].title = title;
        setFormData({...formData, lessons: updatedLessons});
    };

    const handleLessonFileUpload = async (index, file) => {
        if (!file)
            return;
        if (!formData.id) {
            alert("Save the course first before uploading lessons.");
        }

        const lesson = formData.lessons[index];
        const courseId = formData.id || formData.courseId;

        if (!courseId) {
            alert("Please save the course before uploading lessons.");
            return;
        }

        const data = new FormData();
        data.append("file", file);
        data.append("type", lesson.type);          // video, audio, presentation
        data.append("title", lesson.title);        // lesson title
        data.append("courseId", courseId);         // link to course

        try {
            const res = await axios.post("/api/lessons/upload", data, {
                headers: {"Content-Type": "multipart/form-data"}
            });

            console.log("Lesson uploaded with ID:", res.data.lessonId);

            const updatedLessons = [...formData.lessons];
            updatedLessons[index].uploaded = true;
            updatedLessons[index].id = res.data.lessonId;
            setFormData({...formData, lessons: updatedLessons});
        } catch (error) {
            console.error("Lesson file upload failed:", error);
            alert("Failed to upload lesson file.");
        }
    };


    const addNewLesson = () => {
        const newLesson = {
            title: "",
            type: "text"
        };
        setFormData({...formData, lessons: [...formData.lessons, newLesson]});
    };

    const handleChange = (e) => {
        setNewCourse({...newCourse, [e.target.name]: e.target.value});
    };

    const handleImageUpload = async (e) => {
        const file = e.target.files[0];
        if (!file)
            return;

        const formData = new FormData();
        formData.append("image", file);

        try {
            const res = await axios.post("/api/courses/upload-image", formData, {
                headers: {"Content-Type": "multipart/form-data"}
            });

            setNewCourse({...newCourse, imageUrl: res.data.imageUrl});
            console.log("Uploaded image URL:", res.data.imageUrl);
        } catch (error) {
            console.error("Image upload failed:", error);
            alert("Failed to upload image.");
        }
    };

    const handleSubmit = () => {
        if (!userWalletAddress) {
            alert("No wallet address found. Please connect your wallet.");
            return;
        }

        const payload = {
            ...newCourse,
            userId: userWalletAddress,
            price: parseFloat(newCourse.price)
        };

        console.log("New course POST body:", payload);

        axios.post("/api/courses", payload)
                .then(() => {
                    alert("Course added!");
                    setNewCourse({title: "", description: "", categoryId: ""});
                })
                .catch((error) => {
                    console.error("Error adding course:", error);
                    alert("Failed to add course. Please try again.");
                });
    };

    const editCourse = (index) => {
        const courseToEdit = courses[index];
        setFormData(courseToEdit);
        setEditingIndex(index);
        setShowForm(true);
    };

    const deleteCourse = async (index) => {
        const courseToDelete = courses[index];
        try {
            await axios.delete(`/api/courses/${courseToDelete.id}`);
            setCourses(courses.filter((_, i) => i !== index));
        } catch (error) {
            console.error("Failed to delete course:", error);
            alert("Could not delete course.");
        }
    };


    return (
            <div className="manage-container">
                <h2>Manage Courses</h2>
            
                <div className="form">
                    <input
                        type="text"
                        name="title"
                        placeholder="Course Title"
                        value={newCourse.title}
                        onChange={handleChange}
                        />
                    <input
                        type="number"
                        name="price"
                        placeholder="Course Price"
                        value={newCourse.price}
                        onChange={handleChange}
                        />
            
                    <input
                        type="file"
                        accept="image/*"
                        onChange={(e) => handleImageUpload(e)}
                        />
                    {newCourse.imageUrl && (
                                    <img src={newCourse.imageUrl} alt="Course Preview" style={{maxWidth: "200px", marginTop: "10px"}} />
                        )}
                    <textarea
                        name="description"
                        placeholder="Course Description"
                        value={newCourse.description}
                        onChange={handleChange}
                        />
                    <select
                        name="categoryId"
                        value={newCourse.categoryId}
                        onChange={handleChange}
                        className="fancy-dropdown"
                        >
                        <option value="">-- Select Category --</option>
                        {categories.map((cat) => (
                                <option key={cat.id} value={cat.id}>
                                    {cat.name}
                                </option>
                            ))}
                    </select>
                    {formData.lessons.map((lesson, idx) => (
                                <div key={idx}>
                                    <select value={lesson.type} onChange={(e) => handleLessonTypeChange(idx, e.target.value)} className="fancy-dropdown">
                                        <option value="video">Video</option>
                                        <option value="audio">Audio</option>
                                        <option value="text">Text</option>
                                        <option value="presentation">Presentation</option>
                                    </select>
                                    <input type="text" value={lesson.title} onChange={(e) => handleLessonTitleChange(idx, e.target.value)} placeholder="Lesson Title" />
                                    {(lesson.type === "video" || lesson.type === "audio" || lesson.type === "presentation") && (
                                        <input type="file" onChange={(e) => handleLessonFileUpload(idx, e.target.files[0])} />
                                            )}
                                </div>
                            ))}
                    <button onClick={addNewLesson}>➕ Add Lesson</button>
                    <button onClick={handleSubmit}>Save Course</button>
                </div>
            
                <ul className="item-list">
                    {Array.isArray(courses) && courses.map((course, index) => (
                                <li key={index}>
                                    {course.title}
                                    <button onClick={() => editCourse(index)}>Edit</button>
                                    <button onClick={() => deleteCourse(index)}>Delete</button>
                                </li>
                            ))}
                </ul>
            </div>
            );
};

export default ManageCourses;
