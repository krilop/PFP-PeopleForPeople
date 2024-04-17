import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Header from '../header/header';
//import InterestProfile from '../interest-profile/InterestProfileComponent';
import UserService from '../../services/userService';
import './profile.css';

function ProfileComponent() {
    const [user, setUser] = useState(null);
    const [showEditButton, setShowEditButton] = useState(false);
    const params = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        const userIdFromStorage = localStorage.getItem('id');
        const userIdFromRoute = params.id;
        setShowEditButton(userIdFromRoute === userIdFromStorage);
        loadUser(userIdFromRoute);
    }, [params.id]);

    const loadUser = (userId) => {
        if (userId) {
            UserService.getUser(userId)
                .then((userData) => {
                    console.log('User data received:', userData); // Вывести данные пользователя в консоль для отладки
                    setUser(userData.data); // Сохранить данные пользователя в состоянии
                })
                .catch((err) => {
                    console.error('Error fetching user:', err);
                    // Optionally redirect or show an error message
                });
        }
    };


    const navigateToReg2 = () => {
        navigate('/registration/part2');
    };

    if (!user) return <div>Loading...</div>;

    return (
        <>
            <Header style = {{marginBottom:'75px'}} />
            <div className="user-container">
                <div className="user-info">
                    <div className="user-avatar">
                        <img src={user.media} alt="Avatar" />
                    </div>
                    <div className="user-details">
                        <h2>{user.name} {user.lastName}</h2>
                        <p>{user.description}</p>
                        <p>Возраст: {user.age}</p>
                        <p>Пол: {user.gender ? 'Мужской' : 'Женский'}</p>
                    </div>
                </div>
                {showEditButton && (
                    <div className="button-box">
                        <button onClick={navigateToReg2}>Отредактировать информацию</button>
                    </div>
                )}
            </div>

        </>
    );

    // <InterestProfile />
}

export default ProfileComponent;
