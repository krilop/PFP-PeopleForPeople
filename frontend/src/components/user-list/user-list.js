import React, { useState, useEffect } from 'react';
import UserInfoForList from '../user-info-for-list/user-info-for-list';
import HeaderComponent from '../header/header';
import UserService from '../../services/userService';

function UserListComponent() {
    const [userList, setUserList] = useState([]);
    const [id, setId] = useState('');

    useEffect(() => {
        async function fetchData() {
            await loadUsers();
            setId(localStorage.getItem('id'));
        }
        fetchData();
    }, []);

    const loadUsers = async () => {
        try {
            const response = await UserService.getUsers();
            setUserList(response.data);
        } catch (error) {
            console.error('Error loading users:', error);
        }
    };

    return (
        <div>
            <HeaderComponent style={{ marginBottom: '75px' }} />
            {userList && userList.length ? (
                <div style={{ marginTop: '75px' }}>
                    {userList.map(user => (
                        <React.Fragment key={user.id}>
                            {user.id !== +id && <UserInfoForList user={user} />}
                        </React.Fragment>
                    ))}
                </div>
            ) : (
                <div style={{ marginTop: '80px' }}>
                    <p>No users found.</p>
                </div>
            )}
        </div>
    );
}

export default UserListComponent;
