import axios from 'axios';

const API_URL = 'https://wisdomacademy.ai/api/visitors';
const IP_API_URL = 'https://api.ipify.org?format=json';

export const logVisitor = async () => {
    try {
        // Fetch IP address from ipify
        const ipResponse = await axios.get(IP_API_URL);
        const ipAddress = ipResponse.data.ip;

        const payload = {
            userAgent: navigator.userAgent,
            website: 'wisdomacademy.ai',
            ipAddress: ipAddress
        };

        const response = await axios.post(`${API_URL}/log`, payload, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        console.log('Visitor log response:', response.data);
        return response.data;
    } catch (error) {
        console.error('Error logging visitor:', error.response ? error.response.data : error.message);
        throw error;
    }
};

export const getVisitorStats = async (website) => {
    try {
        const response = await axios.get(`${API_URL}/stats`, { params: { website } });
        return response.data;
    } catch (error) {
        console.error('Error fetching visitor stats:', error.response ? error.response.data : error.message);
        throw error;
    }
};