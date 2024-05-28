import React, {useState} from "react";
import {request} from "../axios_helper";

const Sensors = () => {
    const [sensorsData, setSensorsData] = useState(null);
    const [error, setError] = useState(null);
    const [sensorName, setSensorName] = useState('');
    const [plantId, setPlantId] = useState('');
    const [newPlantId, setNewPlantId] = useState('');

    const onChangeHandler = (event) => {
        const {name, value} = event.target;
        if (name === 'plantId') {
            setPlantId(value);
        } else if (name === 'sensorName') {
            setSensorName(value);
        } else if (name === 'newPlantId') {
            setNewPlantId(value);
        }
        setError(null);
    };

    const onSubmitPlantId = (event) => {
        event.preventDefault();
        fetchSensorsData(plantId);
    };

    const fetchSensorsData = (plantId) => {
        request('GET', `/sensors/${plantId}`, {})
            .then((response) => {
                setSensorsData(response.data);
                setError(null);
            })
            .catch(() => {
                setSensorsData(null);
                setError('Something went wrong fetching sensors.');
            });
    };

    const deleteSensor = (sensorName) => {
        request('DELETE', `/sensors`, {plantId: plantId, name: sensorName})
            .then((response) => {
                setSensorsData(response.data);
                setError(null);
            })
            .catch(() => {
                setSensorsData(null);
                setError('Something went wrong fetching sensors.');
            });
    };

    const onSubmit = (event) => {
        event.preventDefault();

        saveNewSensor(newPlantId, sensorName);
    }

    const saveNewSensor = (newPlantId, sensorName) => {
        request('POST', `/sensors`, {plantId: newPlantId, name: sensorName})
            .then((response) => {
                fetchSensorsData(newPlantId)
            })
            .catch((error) => {
                console.error('Error adding sensor:', error);
            });
    };

    return (
        <div className="col justify-content-center m-3">
            <div className="row justify-content-center m-3">
                <div className="col-md-6">
                    <div>
                        <h5>Get sensors by plant ID:</h5>
                        <form onSubmit={onSubmitPlantId}>
                            <div className="form-outline mb-2">
                                <input
                                    type="number"
                                    id="plantId"
                                    name="plantId"
                                    className="form-control form-control-sm"
                                    style={{width: "150px"}}
                                    onChange={onChangeHandler}
                                    value={plantId}
                                    placeholder="Enter plant ID"
                                />
                            </div>
                            <button type="submit" className="btn btn-primary btn-block mb-4">
                                Get sensors
                            </button>
                        </form>
                    </div>
                </div>
                <div className="col-md-6">
                    <div>
                        <h5>Add a New Sensor:</h5>
                        <form onSubmit={onSubmit}>
                            <div className="form-outline mb-2">
                                <input
                                    type="text"
                                    id="sensorName"
                                    name="sensorName"
                                    className="form-control form-control-sm"
                                    style={{width: '150px'}}
                                    onChange={onChangeHandler}
                                    value={sensorName}
                                    placeholder="Enter sensor name"
                                />
                                <input
                                    type="number"
                                    id="newPlantId"
                                    name="newPlantId"
                                    className="form-control form-control-sm"
                                    style={{width: '150px'}}
                                    onChange={onChangeHandler}
                                    value={newPlantId}
                                    placeholder="Enter plant ID"
                                />
                            </div>
                            <button type="submit" className="btn btn-success btn-block mb-4">
                                Save New Sensor
                            </button>
                        </form>
                    </div>
                </div>
            </div>


            {error && (
                <div className="col-8">
                    <p className="text-danger">{error}</p>
                </div>
            )}

            {sensorsData && (
                <div className="col-12">
                <h5>Sensors List:</h5>
                    <table className="table">
                        <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Name</th>
                            <th scope="col">Readings</th>
                            <th scope="col">Lamps</th>
                            <th scope="col">Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        {sensorsData.map((sensor) => (
                            <tr key={sensor.id}>
                                <td>{sensor.id}</td>
                                <td>{sensor.name}</td>
                                <td>
                                    {sensor.readings.length > 0 ? (
                                        <ul className="list-group">
                                            {sensor.readings.map((reading, readingIndex) => (
                                                <li key={readingIndex}>
                                                    <div>
                                                        <strong>Reading ID:</strong> {reading.id}
                                                    </div>
                                                    <div>
                                                        <strong>Name:</strong> {reading.name}, <strong>Value:</strong> {reading.value}
                                                    </div>
                                                    <div>
                                                        <strong>Is
                                                            Warning:</strong> {reading.isWarning ? 'true' : 'false'}
                                                    </div>
                                                    <div>
                                                        <strong>Date and
                                                            Time:</strong> {new Date(reading.dateTime).toLocaleString()}
                                                    </div>
                                                </li>
                                            ))}
                                        </ul>
                                    ) : 'No readings'}
                                </td>
                                <td>
                                    {sensor.lamps.length > 0 ? (
                                        <ul className="list-bulleted">
                                            {sensor.lamps.map((lamp, lampIndex) => (
                                                <li key={lampIndex}>
                                                    Lamp Id: {lamp.id}, Name: {lamp.name},
                                                    Light Level: {lamp.lightLevel}
                                                </li>
                                            ))}
                                        </ul>
                                    ) : 'No readings'}
                                </td>
                                <td>
                                    <button onClick={() => deleteSensor(sensor.name)} className={"btn btn-danger"}>
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default Sensors;
