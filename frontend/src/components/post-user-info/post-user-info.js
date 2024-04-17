import React, { useState } from 'react';
import UserService from '../../services/userService';
import { useNavigate} from 'react-router-dom';

import './post-user-info.css'
function PostUserInfoComponent() {
    const [user, setUser] = useState({
        id: 0,
        name: '',
        lastName: '',
        description: '',
        dateOfBirth: null,
        gender: '',
        media: ''
    });
    const history = useNavigate();

    const onSubmit = () => {
        const newUser = { ...user };
        if (newUser.gender === 'male') {
            newUser.gender = true;
        } else {
            newUser.gender = false;
        }
        newUser.id =localStorage.getItem('id');
        const dateObject = new Date(newUser.dateOfBirth);
        newUser.dateOfBirth = dateObject.toISOString().slice(0, 10);

        UserService.saveUser(newUser)
            .then(response => {
                console.log('User saved successfully:', response);
                history(`/profile/${localStorage.getItem('id')}`);
            })
            .catch(error => {
                console.error('Error saving user:', error);
            });
    };

    const previewImage = () => {
        setUser({ ...user, media: user.imageLink });
    };

    return (
        <form onSubmit={onSubmit} className="form-container">
            <div className="form-table">
                <div className="form-group">
                    <label htmlFor="name">Имя</label>
                    <input type="text" id="name" name="name" value={user.name} onChange={e => setUser({ ...user, name: e.target.value })} required />
                </div>
                <div className="form-group">
                    <label htmlFor="lastName">Фамилия</label>
                    <input type="text" id="lastName" name="lastName" value={user.lastName} onChange={e => setUser({ ...user, lastName: e.target.value })} required />
                </div>
                <div className="form-group">
                    <label htmlFor="description">Описание</label>
                    <textarea id="description" name="description" value={user.description} onChange={e => setUser({ ...user, description: e.target.value })}></textarea>
                </div>
                <div className="form-group">
                    <label htmlFor="dateOfBirth">Дата рождения</label>
                    <input type="date" id="dateOfBirth" name="dateOfBirth" value={user.dateOfBirth} onChange={e => setUser({ ...user, dateOfBirth: e.target.value })} required />
                </div>
                <div className="form-group">
                    <label htmlFor="gender">Пол</label>
                    <select id="gender" name="gender" value={user.gender} onChange={e => setUser({ ...user, gender: e.target.value })} required>
                        <option value="" disabled selected>Выберите пол</option>
                        <option value="male">Мужской</option>
                        <option value="female">Женский</option>
                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="imageLink">Ссылка на изображение</label>
                    <input type="text" id="imageLink" name="imageLink" value={user.media} onChange={e => setUser({ ...user, media: e.target.value })} required />
                </div>
                <div className="form-group">
                    <button type="button" onClick={previewImage}>Предпросмотр</button>
                </div>
                <div className="form-group">
                    {user.media && <img src={user.media} alt="Preview" />}
                </div>
                <button type="submit">Сохранить</button>
            </div>
        </form>
    );
}

export default PostUserInfoComponent;
