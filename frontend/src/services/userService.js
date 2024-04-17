import axios from "axios";

const apiUrl = "http://localhost:8080/api/v1/PFP";

const UserService = {
    async getUsers(){
        const token = localStorage.getItem('token');
        try {
            return await axios.get(`${apiUrl}/userlist`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });
        } catch (error) {
            throw error;  // Здесь можно обработать ошибку
        }
    },

    async getUser(id){
        const token = localStorage.getItem('token');
        try {
            return await axios.get(`${apiUrl}/profile/${id}/info`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });
        } catch (error) {
            throw error;  // Здесь можно обработать ошибку
        }
    },

    async saveUser(user){
        const token = localStorage.getItem('token');
        const userId = localStorage.getItem('id');
        try {
            return await axios.post(`${apiUrl}/profile/${userId}/change`, user, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });
        } catch (error) {
            throw error;  // Здесь можно обработать ошибку
        }
    }
};

export default UserService;
