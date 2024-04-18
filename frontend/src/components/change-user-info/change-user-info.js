import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import userService from "../../services/userService";

function ChangeUserInfoComponent({ userInfo }) {
    const [user, setUser] = useState({
        id: '',
        name: '',
        lastName: '',
        description: '',
        dateOfBirth: null,
        gender: '',
        media: ''
    });

    const navigate = useNavigate();

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setUser((prevUser) => ({ ...prevUser, [name]: value }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const updatedUser = { ...user, id: localStorage.getItem('id') };
        if (user.gender === 'male') {
            updatedUser.gender = true;
        } else {
            updatedUser.gender = false;
        }

        if (user.dateOfBirth) {
            const dateObject = new Date(user.dateOfBirth);
            updatedUser.dateOfBirth = dateObject.toISOString().slice(0, 10);
        } else {
            updatedUser.dateOfBirth = null;
        }

        // Assuming `userService.changeUser` is an API call function you need to implement
        userService.changeUser(updatedUser).then(response => {
            console.log('User saved successfully:', response);
            navigate(`/profile/${localStorage.getItem('id')}`);
        }).catch(error => {
            console.error('Error saving user:', error);
        });
    };

    const previewImage = () => {
        setUser((prevUser) => ({ ...prevUser, media: user.imageLink }));
    };

    return (
        <form onSubmit={handleSubmit} className="form-container">
            <div className="form-table">
                {/* Name Input */}
                <div className="form-group">
                    <label htmlFor="name">Имя</label>
                    <input type="text" id="name" name="name" value={user.name} onChange={handleInputChange} />
                </div>

                {/* Last Name Input */}
                <div className="form-group">
                    <label htmlFor="lastName">Фамилия</label>
                    <input type="text" id="lastName" name="lastName" value={user.lastName} onChange={handleInputChange} />
                </div>

                {/* Description Input */}
                <div className="form-group">
                    <label htmlFor="description">Описание</label>
                    <textarea id="description" name="description" value={user.description} onChange={handleInputChange}></textarea>
                </div>

                {/* Date of Birth Input */}
                <div className="form-group">
                    <label htmlFor="dateOfBirth">Дата рождения</label>
                    <input type="date" id="dateOfBirth" name="dateOfBirth" value={user.dateOfBirth || ''} onChange={handleInputChange} />
                </div>

                {/* Image Link Input */}
                <div className="form-group">
                    <label htmlFor="imageLink">Ссылка на изображение</label>
                    <input type="text" id="imageLink" name="imageLink" value={user.media} onChange={handleInputChange} />
                    <button type="button" onClick={previewImage}>Предпросмотр</button>
                </div>

                {/* Image Preview */}
                <div className="form-group">
                    {user.media && <img src={user.media} alt="Preview" />}
                </div>

                {/* Submit Button */}
                <button type="submit">Сохранить</button>
            </div>
        </form>
    );
}

export default ChangeUserInfoComponent;
