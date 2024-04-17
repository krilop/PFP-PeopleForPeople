import React from 'react';
import {useNavigate} from "react-router-dom";

function UserInfoForListComponent({ user }) {

    const navigate = useNavigate()
    const goToProfile = (userId) => {
        navigate(`/profile/${userId}`);
    };

    return (
        <div className="user-container">
            <h3>{user.name} {user.lastName}</h3>
            <p>Email: {user.email}</p>
            <p>Age: {user.age}</p>
            <button onClick={() => goToProfile(user.id)}>Go to Profile</button>
        </div>
    );
}

export default UserInfoForListComponent;
