import axios from 'axios';
import {jwtDecode} from "jwt-decode";

class AuthorizationService {
    apiUrl = 'http://localhost:8080/auth';

    async login(username, password) {
        try {
            const response = await axios.post(`${this.apiUrl}/sign-in`, {username, password});
            // Сохраняем токен в локальное хранилище
            localStorage.setItem('token', response.data.token);
            const decodedToken = jwtDecode(response.data.token);
            localStorage.setItem('id', decodedToken.id);
            console.log(localStorage.getItem('token'))
            return response.data;
        } catch (error) {
            throw error;
        }
    }

    async register(username, email, password) {
        try {
            const response = await axios.post(`${this.apiUrl}/sign-up`, {username, email, password});
            // Сохраняем токен в локальное хранилище
            localStorage.setItem('token', response.data.token);
            const decodedToken = jwtDecode(response.data.token);
            localStorage.setItem('id', decodedToken.id);
            return response.data;
        } catch (error) {
            throw error;
        }
    }
}

export default new AuthorizationService();
