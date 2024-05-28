import React, { useState } from 'react';
import { request } from '../axios_helper';

const AddSensorForm = () => {
    const [login, setLogin] = useState('');
    const [sensorName, setSensorName] = useState('');

    const onChangeHandler = (event) => {
        const { name, value } = event.target;
        if (name === 'login') {
            setLogin(value);
        } else if (name === 'sensorName') {
            setSensorName(value);
        }
    };

    const onSubmit = (event) => {
        event.preventDefault();
        saveNewSensor(login, sensorName);
    };

    const saveNewSensor = (login, sensorName) => {
        request('POST', `/sensors`, { userLogin: login, name: sensorName })
            .then((response) => {
                console.log('Sensor added:', response.data);
            })
            .catch((error) => {
                console.error('Error adding sensor:', error);
            });
    };

    return (
        <div>
            <h5>Add a New Sensor:</h5>
            <form onSubmit={onSubmit}>
                <div className="form-outline mb-2">
                    <input
                        type="text"
                        id="loginName"
                        name="login"
                        className="form-control form-control-sm"
                        style={{ width: '150px' }}
                        onChange={onChangeHandler}
                        value={login}
                        placeholder="Enter user login"
                    />
                </div>
                <div className="form-outline mb-2">
                    <input
                        type="text"
                        id="sensorName"
                        name="sensorName"
                        className="form-control form-control-sm"
                        style={{ width: '150px' }}
                        onChange={onChangeHandler}
                        value={sensorName}
                        placeholder="Enter sensor name"
                    />
                </div>
                <button type="submit" className="btn btn-success btn-block mb-4">
                    Save New Sensor
                </button>
            </form>
        </div>
    );
};

export default AddSensorForm;
