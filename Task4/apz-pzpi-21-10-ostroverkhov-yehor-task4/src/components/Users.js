import React, {useEffect, useState} from "react";
import {request} from "../axios_helper";
import {Link} from "react-router-dom";

export default function Users() {
    const [usersData, setUsersData] = useState([]);

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = () => {
        request('GET', '/users', {})
            .then((response) => {
                setUsersData(response.data);
            })
            .catch((error) => {
                console.error('Error fetching user data:', error);
            });
    }

    const deleteUser = (login) => {
        request('DELETE', `/users/${login}`, {})
            .then(response => {
                setUsersData(response.data);
            });
    }

    return (
        <div className="container mt-4">
            <div className="row">
                <div className="col">
                    <table className="table">
                        <thead>
                        <tr>
                            <th>Created At</th>
                            <th>Login</th>
                            <th>Roles</th>
                            <th>Edit Roles</th>
                            <th>Info</th>
                            <th>Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        {usersData.map((userData, index) => (
                            <tr key={index}>
                                <td>{new Date(userData.createdAt).toLocaleString()}</td>
                                <td>{userData.login}</td>
                                <td>
                                    {userData.roles && Array.isArray(userData.roles) && (
                                        <>
                                            {userData.roles.map((role, index) => (
                                                <tr key={index}>
                                                    <td>{role}</td>
                                                </tr>
                                            ))}
                                        </>
                                    )}
                                </td>
                                <td>
                                    <Link to={`/edit-roles/${userData.login}`}
                                          className="btn btn-success">
                                        Edit Roles
                                    </Link>
                                </td>
                                <td>
                                    <Link to={`/users/${userData.login}`}
                                          className="btn btn-primary">
                                        Show Details
                                    </Link>
                                </td>
                                <td>
                                    <button onClick={() => deleteUser(userData.login)}
                                            className={"btn btn-danger"}>Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}