import axios from "axios";

const apiUrl = "http://localhost:8080/api/v1/PFP";

const InterestsService = {
    async getInterests() {
        const token = localStorage.getItem('token');
        try {
            const response = await axios.get(`${apiUrl}/interests/all`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            throw error;
        }
    },
    async getInterestsForUser() {
        const token = localStorage.getItem('token');
        const userId = localStorage.getItem('id');
        try {
            const response = await axios.get(`${apiUrl}/interests/${userId}/new`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            throw error;
        }
    },
    async getInterestsOfUser(id) {
        const token = localStorage.getItem('token');
        try {
            const response = await axios.get(`${apiUrl}/interests/${id}`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            throw error;
        }
    },
    async deleteInterestOfUser(id) {
        const token = localStorage.getItem('token');
        const userId = localStorage.getItem('id');
        try {
            const response = await axios.delete(`${apiUrl}/interests/${userId}/${id}`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            throw error;
        }
    },
    async saveInterest(interest) {
        const token = localStorage.getItem('token');
        try {
            const response = await axios.post(`${apiUrl}/interests/addNew`, interest, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            throw error;
        }
    },
    async addInterestForUser(id) {
        const token = localStorage.getItem('token');
        const userId = localStorage.getItem('id');
        try {
            const response = await axios.post(`${apiUrl}/interests/${userId}/${id}`, '', {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            throw error;
        }
    }
};

export default InterestsService;
