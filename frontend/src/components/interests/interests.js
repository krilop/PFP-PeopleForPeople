import React, {useState, useEffect} from 'react';
import {useNavigate} from 'react-router-dom';
import InterestsService from '../../services/interestService'; // Service for handling interests

import './interests.css';

function InterestsComponent() {
    const [interests, setInterests] = useState([]);
    const [newInterest, setNewInterest] = useState({icon: '', titleOfType: ''});
    const navigate = useNavigate();

    useEffect(() => {
        getInterests(); // Fetch the list of interests on component mount
    }, []);

    const getInterests = async () => {
        try {
            const interests = await InterestsService.getInterestsForUser();
            setInterests(interests);
        } catch (error) {
            console.error('Error fetching interests:', error);
        }
    };

    const addInterestToUser = async (interestId) => {
        console.log('Adding interest with ID:', interestId);
        try {
            await InterestsService.addInterestForUser(interestId);
            // Remove the interest from the list
            setInterests(interests.filter(interest => interest.id !== interestId));
        } catch (error) {
            console.error('Error adding interest:', error);
        }
    };

    const addInterest = async (event) => {
        event.preventDefault();
        console.log('Adding new interest:', newInterest);
        try {
            await InterestsService.saveInterest(newInterest);
            alert('New interest added successfully');
            setNewInterest({icon: '', titleOfType: ''}); // Clear fields after adding
            getInterests(); // Reload interests list
        } catch (error) {
            console.error('Error adding new interest:', error);
        }
    };

    const finish = () => {
        const id = localStorage.getItem('id');
        navigate(`/profile/${id}`);
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
                    >
                        <img src={interest.icon} alt="Interest" title={interest.titleOfType}/>
                    </div>
                ))}
            </div>
            <div className="add-interest">
                <h2>Не нашел своего интереса? ДОБАВЬ СВОЙ!</h2>
                <form onSubmit={addInterest}>
                    <div className="form-table">
                        <div className="form-group">
                            <label htmlFor="imageUrl">Ссылка на изображение:<br/>
                                <input
                                    type="text"
                                    id="imageUrl"
                                    name="imageUrl"
                                    value={newInterest.icon}
                                    onChange={(e) => setNewInterest({...newInterest, icon: e.target.value})}
                                    required
                                /></label>
                            <div className="interests">
                                <img src={newInterest.icon} alt="Interest Preview" className="interest"/>
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="name">Название интереса:<br/>
                                <input
                                    type="text"
                                    id="name"
                                    name="name"
                                    value={newInterest.titleOfType}
                                    onChange={(e) => setNewInterest({...newInterest, titleOfType: e.target.value})}
                                    required
                                /></label>
                        </div>
                        <button type="submit" disabled={!newInterest.icon || !newInterest.titleOfType}>Добавить интерес</button>
                    </div>
                </form>
            </div>
            <button className="ready-btn" onClick={finish}>Готово</button>
        </div>
    );
}

export default InterestsComponent;
