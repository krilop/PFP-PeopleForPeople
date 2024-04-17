import React, { useState } from 'react';
import AuthorizationService from '../../services/authorizationService';
import './registration.css'
import {useNavigate} from "react-router-dom";
function RegistrationComponent() {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const register = async (event) => {
        event.preventDefault();
        try {
            const response = await AuthorizationService.register(username, email, password);
            navigate('/registration/part2');
        } catch (error) {
            console.error('Registration error', error);
        }
    };

    return (
        <div className="registration-container">
            <h2>Register on PFP - People for People</h2>
            <form onSubmit={register}>
                <div className="form-group">
                    <label htmlFor="username">Username:</label>
                    <input type="text" id="username" name="username" value={username} onChange={(e) => setUsername(e.target.value)} required />
                </div>
                <div className="form-group">
                    <label htmlFor="email">Email:</label>
                    <input type="email" id="email" name="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                    {email && !/\S+@\S+\.\S+/.test(email) && (
                        <div className="text-danger">Invalid email format.</div>
                    )}
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password:</label>
                    <input type="password" id="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                </div>
                <button type="submit" disabled={!username || !email || !password || !/\S+@\S+\.\S+/.test(email)}>Register</button>
            </form>
        </div>
    );
}

export default RegistrationComponent;
