import React, { useEffect, useState } from 'react';
import AuthorizationService from '../../services/authorizationService'; // Import the service
import './authorization.css';
import { useNavigate } from 'react-router-dom';

function AuthorizationComponent() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault(); // Prevent the default form submission

        try {
            const response = await AuthorizationService.login(username, password); // Await the login operation
            // If login is successful, navigate to '/userlist'
            navigate('/userlist');
        } catch (error) {
            // Set error message if login fails
            setErrorMessage('Ошибка аутентификации. Проверьте введенные данные.');
            console.error('Login error', error);
        }
    };

    return (
        <div className="auth-container">
            <h2>Authorization</h2>
            <form onSubmit={handleLogin}> {/* Bind the form submission to handleLogin */}
                <label>
                    Username:
                    <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
                </label>
                <br />
                <label>
                    Password:
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </label>
                <br />
                <button type="submit">Login</button>
            </form>
            {errorMessage && <p>{errorMessage}</p>}
        </div>
    );
}

export default AuthorizationComponent;
