import React, {useState} from "react";
import {request} from "../axios_helper";

const Readings = () => {
    const [sensorId, setSensorId] = useState('');
    const [readingsData, setReadingsData] = useState(null);
    const [error, setError] = useState(null);
    const [userLogin, setUserLogin] = useState('')
    const [plantId, setPlantId] = useState('')

    const onChangeHandler = (event) => {
        const {name, value} = event.target;
        setSensorId(value);
        setError(null);
    };

    const onSubmitSensorId = (event) => {
        event.preventDefault();
        fetchReadingsData(sensorId);
    };

    const fetchReadingsData = (sensorId) => {
        request('GET', `/readings/${sensorId}`, {})
            .then((response) => {
                setReadingsData(response.data);
                setError(null);
            })
            .catch(() => {
                setReadingsData(null);
                setError('Something went wrong fetching readings.');
            });
    };

    const onChangePlantId = (event) => {
        const {value} = event.target;
        setPlantId(value);
    };

    const onSubmitPlantId = (event) => {
        event.preventDefault();
        fetchWarningsByPlantId(plantId);
    };

    const fetchWarningsByPlantId = (plantId) => {
        request('GET', `/readings/warnings/${plantId}`, {})
            .then((response) => {
                setReadingsData(response.data);
            })
            .catch(() => {
                console.log('Something went wrong fetching warnings.');
            });
    };

    const [newReading, setNewReading] = useState({
        sensorId: '',
        name: '',
        value: '',
        isWarning: false,
    });

    const onChangeNewReading = (event) => {
        const {name, value} = event.target;
        setNewReading({
            ...newReading,
            [name]: name === 'isWarning' ? event.target.checked : value,
        });
    };

    const onSubmitNewReading = (event) => {
        event.preventDefault();
        addNewReading(newReading);
    };

    const addNewReading = (newReading) => {
        request('POST', '/readings', newReading)
            .then((response) => {
                setReadingsData(response.data)
                setNewReading({
                    sensorId: '',
                    name: '',
                    value: '',
                    isWarning: false,
                });
            })
            .catch((error) => {
                console.error('Error adding new reading:', error);
            });
    };

    return (
        <div className="col justify-content-center m-3">
            <div className="row justify-content-center m-3">
                <div className="col-md-4">
                    <div>
                        <h5>Get readings by sensor ID:</h5>
                        <form onSubmit={onSubmitSensorId}>
                            <div className="form-outline mb-2">
                                <input
                                    type="text"
                                    id="sensorId"
                                    name="sensorId"
                                    className="form-control form-control-sm"
                                    style={{width: "150px"}}
                                    onChange={onChangeHandler}
                                    value={sensorId}
                                    placeholder="Enter sensor Id"
                                />
                            </div>
                            <button type="submit" className="btn btn-primary btn-block mb-4">
                                Get readings
                            </button>
                        </form>
                    </div>
                </div>

                <div className="col-md-4">
                    <div>
                        <h5>Get warnings by plant ID:</h5>
                        <form onSubmit={onSubmitPlantId}>
                            <div className="form-outline mb-2">
                                <input
                                    type="text"
                                    id="plantId"
                                    name="plantId"
                                    className="form-control form-control-sm"
                                    style={{width: "150px"}}
                                    onChange={onChangePlantId}
                                    value={plantId}
                                    placeholder="Enter plant ID"
                                />
                            </div>
                            <button type="submit" className="btn btn-warning btn-block mb-4">
                                Get warnings
                            </button>
                        </form>
                    </div>
                </div>

                <div className="col-md-4">
                    <div>
                        <h5>Add New Reading:</h5>
                        <form onSubmit={onSubmitNewReading}>
                            <div className="form-outline mb-2">
                                <input
                                    type="text"
                                    id="newSensorId"
                                    name="sensorId"
                                    className="form-control form-control-sm"
                                    style={{width: '150px'}}
                                    onChange={onChangeNewReading}
                                    value={newReading.sensorId}
                                    placeholder="Sensor Id"
                                />
                            </div>
                            <div className="form-outline mb-2">
                                <input
                                    type="text"
                                    id="newName"
                                    name="name"
                                    className="form-control form-control-sm"
                                    style={{width: '150px'}}
                                    onChange={onChangeNewReading}
                                    value={newReading.name}
                                    placeholder="Name"
                                />
                            </div>
                            <div className="form-outline mb-2">
                                <input
                                    type="number"
                                    id="newValue"
                                    name="value"
                                    className="form-control form-control-sm"
                                    style={{width: '150px'}}
                                    onChange={onChangeNewReading}
                                    value={newReading.value}
                                    placeholder="Value"
                                />
                            </div>
                            <div className="form-check mb-2">
                                <input
                                    type="checkbox"
                                    id="newIsWarning"
                                    name="isWarning"
                                    className="form-check-input"
                                    onChange={onChangeNewReading}
                                    checked={newReading.isWarning}
                                />
                                <label htmlFor="newIsWarning" className="form-check-label">
                                    Is Warning
                                </label>
                            </div>
                            <button type="submit" className="btn btn-success btn-block mb-4">
                                Add Reading
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

            {readingsData && (
                <div className="col-12">
                    <h5>Readings List:</h5>
                    <table className="table">
                        <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Name</th>
                            <th scope="col">Value</th>
                            <th scope="col">Is Warning</th>
                            <th scope="col">Date Time</th>
                        </tr>
                        </thead>
                        <tbody>
                        {readingsData.map((reading) => (
                            <tr key={reading.id}>
                                <td>{reading.id}</td>
                                <td>{reading.name}</td>
                                <td>{reading.value}</td>
                                <td>{reading.isWarning ? 'true' : 'false'}</td>
                                <td>{new Date(reading.dateTime).toLocaleString()}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default Readings;
