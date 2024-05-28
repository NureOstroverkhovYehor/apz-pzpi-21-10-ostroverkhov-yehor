import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import {request} from "../axios_helper";

const UserDetails = () => {
    const { userLogin } = useParams();
    const [userDetails, setUserDetails] = useState(null);

    useEffect(() => {
        request('GET', `/users/${userLogin}`, {})
            .then(response => {
                setUserDetails(response.data);
            })
            .catch(error => {
                console.error('Error fetching user details:', error);
            });
    }, [userLogin]);

    if (!userDetails) {
        return <div>User not exists</div>;
    }

    return (
        <div>
            <h2>User Details</h2>
            <p><strong>Login:</strong> {userDetails.login}</p>
            <p><strong>Created at:</strong> {new Date(userDetails.createdAt).toLocaleString() }</p>
        </div>
    );
};

export default UserDetails;