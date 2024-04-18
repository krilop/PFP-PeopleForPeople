import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Header from '../header/header';
//import InterestProfile from '../interest-profile/InterestProfileComponent';
import UserService from '../../services/userService';
import './profile.css';
import InterestProfileComponent from "../interest-profile/interest-profile";
function ProfileComponent() {
    const [user, setUser] = useState(null);
    const [showEditButton, setShowEditButton] = useState(false);
    const params = useParams();
    const navigate = useNavigate();
    let userIdFromRoute;
    useEffect(() => {
        const userIdFromStorage = localStorage.getItem('id');
        userIdFromRoute = params.id;
        setShowEditButton(userIdFromRoute === userIdFromStorage);
        loadUser(userIdFromRoute);
    }, [params.id]);

    async function loadUser(userId) {
        if (userId) {
            try {
                const userData = await UserService.getUser(userId);
                console.log('User data received:', userData); // Log user data for debugging
                setUser(userData.data); // Set user data in state
                localStorage.setItem('gender', userData.data.gender)
            } catch (err) {
                console.error('Error fetching user:', err);
                // Handle error - display error message or redirect
                // For now, let's set user to null to indicate loading error
                setUser(null);
            }
        }
    }


    const navigateToEditProfile = () => {
        navigate(`/change`); // Navigate to edit profile page with current user ID
    };

    if (!user) return <div>Error: Failed to load user data.</div>; // Display error message if user data is null or loading failed

    return (
        <>
            <Header style={{ marginBottom: '75px' }} />
            <div className="user-container">
                <div className="user-info">
                    <div className="user-avatar">
                        <img src={user.media} alt="Avatar" />
                    </div>
                    <div className="user-details">
                        <h2>{user.name} {user.lastName}</h2>
                        <p>{user.description}</p>
                        <p>Age: {user.age}</p>
                        <p>Gender: {user.gender ? 'male' : 'female'}</p>
                    </div>
                </div>
                {showEditButton && (
                    <div className="button-box">
                        <button onClick={navigateToEditProfile}>Edit Profile</button>
                    </div>
                )}
            </div>
            <InterestProfileComponent id = {{userIdFromRoute}}/>
        </>
    );
}

export default ProfileComponent;