import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom'; // Подключаем хук useParams для получения параметров маршрута
import InterestsService from '../../services/interestService'; // Подключаем сервис для работы с интересами

function InterestProfileComponent() {
    const [interests, setInterests] = useState([]); // Состояние для списка интересов
    const { id } = useParams(); // Получаем параметр id из маршрута

    useEffect(() => {
        getInterests(); // Получаем интересы при загрузке компонента
    }, [id]); // Перезагружаем компонент при изменении id

    // Функция для получения интересов пользователя
    const getInterests = async () => {
        try {
            const interestsData = await InterestsService.getInterestsOfUser(id);
            setInterests(interestsData || []);
        } catch (error) {
            console.error('Error fetching interests:', error);
        }
    };

    return (
        <div className="interests-container">
            <h1>Интересы пользователя</h1>
            <div className="interests">
                {interests.map(interest => (
                    <div key={interest.id} className="interest">
                        <img src={interest.icon} alt="Interest" title={interest.titleOfType} />
                        <p>{interest.titleOfType}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default InterestProfileComponent;
