import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import InterestsService from '../../services/interestService'; // Подключаем сервис для работы с интересами

import './interests.css'
function InterestsComponent() {
    const [interests, setInterests] = useState([]); // Состояние для списка интересов
    const [newInterest, setNewInterest] = useState({ icon: '', titleOfType: '' }); // Состояние для нового интереса
    const [showInterest, setShowInterest] = useState(false); // Состояние для отображения названия интереса при наведении
    const [hoveredInterestName, setHoveredInterestName] = useState(''); // Состояние для названия интереса, над которым находится курсор

    const navigate = useNavigate();

    useEffect(() => {
        getInterests(); // Получаем список интересов при загрузке компонента
    }, []);

    // Функция для получения списка интересов
    const getInterests = async () => {
        try {
            const interests = await InterestsService.getInterestsForUser();
            setInterests(interests);
        } catch (error) {
            console.error('Error fetching interests:', error);
        }
    };

    // Функция для добавления интереса к пользователю
    const addInterestToUser = async (interestId) => {
        console.log('Adding interest with ID:', interestId);
        try {
            const response = await InterestsService.addInterestForUser(interestId);

            console.log('Interest added successfully:', response);
        } catch (error) {
            console.error('Error adding interest:', error);
        }
    };

    // Функция для добавления нового интереса
    const addInterest = async (event) => {
        event.preventDefault();
        console.log('Adding new interest:', newInterest);
        try {
            const response = await InterestsService.saveInterest(newInterest);
            console.log('New interest added successfully:', response);
            alert('New interest added successfully');
            setNewInterest({ icon: '', titleOfType: '' }); // Очищаем поля после успешного добавления интереса
        } catch (error) {
            console.error('Error adding new interest:', error);
        }
    };

    // Функция для завершения процесса выбора интересов
    const finish = () => {
        const id = localStorage.getItem('id');
        navigate(`/profile/${id}`);
    };

    // Функция для отображения названия интереса при наведении
    const showInterestName = (name) => {
        setHoveredInterestName(name);
        setShowInterest(true);
    };

    // Функция для скрытия названия интереса
    const hideInterestName = () => {
        setShowInterest(false);
    };

    return (
        <div className="interests-container">
            <h1>Выберите ваши интересы</h1>
            <div className="interests">
                {interests.map(interest => (
                    <div
                        key={interest.id}
                        className="interest"
                        onClick={() => addInterestToUser(interest.id)}
                        onMouseOver={() => showInterestName(interest.titleOfType)}
                        onMouseLeave={hideInterestName}
                    >
                        <img src={interest.icon} alt="Interest" title={interest.titleOfType} />
                    </div>
                ))}
            </div>

            <div className="add-interest">
                <h2>Не нашел своего интереса? ДОБАВЬ СВОЙ!</h2>
                <form onSubmit={addInterest}>
                    <div className="form-group">
                        <label htmlFor="imageUrl">Ссылка на изображение:</label>
                        <input
                            type="text"
                            id="imageUrl"
                            name="imageUrl"
                            value={newInterest.icon}
                            onChange={(e) => setNewInterest({ ...newInterest, icon: e.target.value })}
                            required
                        />
                        <div className="interests">
                            <img src={newInterest.icon} alt="Interest Preview" className="interest" />
                        </div>
                    </div>
                    <div className="form-group">
                        <label htmlFor="name">Название интереса:</label>
                        <input
                            type="text"
                            id="name"
                            name="name"
                            value={newInterest.titleOfType}
                            onChange={(e) => setNewInterest({ ...newInterest, titleOfType: e.target.value })}
                            required
                        />
                    </div>
                    <button type="submit" disabled={!newInterest.icon || !newInterest.titleOfType}>Добавить интерес</button>
                </form>
            </div>

            <button className="ready-btn" onClick={finish}>Готово</button>
        </div>
    );
}

export default InterestsComponent;
