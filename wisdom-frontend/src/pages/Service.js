import axios from "axios";

// Define API URL
const API_URL = "http://localhost:5000/api";

// Create an Axios instance with custom HTTPS agent
const axiosInstance = axios.create({
  baseURL: API_URL,
  withCredentials: true, // Ensures cookies/session data are included
});


export const fetchData = async () => {
  try {
    const response = await axios.get(`${API_URL}/data`);
        
    return response.data;
    
    //return {"message": "Hello, World!"};
  } catch (error) {
    console.error("Error fetching data:", error);
    return null;
  }
};

export const sendData = async (data) => {
  try {
    const response = await axios.post(`${API_URL}/post`, data);
    return response.data;
  } catch (error) {
    console.error("Error sending data:", error);
    return null;
  }
};

