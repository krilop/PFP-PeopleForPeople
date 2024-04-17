import React from 'react';
import './main-page.css'
import {useNavigate} from "react-router-dom";
function MainPageComponent() {
    localStorage.clear();
    const navigate = useNavigate();
    const goToRegistration = () => {
        navigate('registration');
    };

    const goToAuthentication = () => {
        navigate('authorization');
    };

    return (
        <div>
            <app-welcome-information/>

            <div className="authentication">
                <h2>Welcome to PFP - People for People</h2>
                <button onClick={goToRegistration}>Register</button>
                <button onClick={goToAuthentication}>Login</button>
            </div>
        </div>
    );
}

export default MainPageComponent;
