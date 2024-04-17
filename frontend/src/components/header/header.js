import React from 'react';
import './header.css';
import { Link, useNavigate } from "react-router-dom";

function Header() {
    const navigate = useNavigate();

    const navigateToProfile = () => {
        // Ваша логика перехода на страницу профиля
        navigate(`/profile/${localStorage.getItem('id')}`);
    };

    const logOut = () => {
        localStorage.clear();
        navigate('/'); // Используйте navigate для перехода на главную страницу
    };

    return (
        <header>
            <nav>
                <ul>
                    <li><Link to="/userlist">Список пользователей</Link></li>
                    <li><button onClick={navigateToProfile}>Профиль</button></li>
                    <li><Link to="/interests">Добавить новые интересы</Link></li>
                    <li><button onClick={logOut}>Выход</button></li>
                </ul>
            </nav>
        </header>
    );
}

export default Header;
