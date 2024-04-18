import React from 'react';
import {useNavigate} from "react-router-dom";

function UserInfoForListComponent({ user }) {

    const navigate = useNavigate()
    const goToProfile = (userId) => {
        navigate(`/profile/${userId}`);
    };

    return (
        <div className="user-container">
            <div className="user-info">
                <div className="user-avatar">
                    <img src={user.media} alt="NO_PHOTO"/>
                </div>
                <div className="user-details">
                    <h2>{ user.name } { user.lastName }</h2>
                    <p>{ user.description }</p>
                    <p>Возраст: { user.age }</p>
                    <p>Пол: { user.gender ? 'Мужской' : 'Женский' }</p>
                </div>
            </div>
            <button onClick={() => goToProfile(user.id)}>Go to Profile</button>
        </div>
    );
}

export default UserInfoForListComponent;
