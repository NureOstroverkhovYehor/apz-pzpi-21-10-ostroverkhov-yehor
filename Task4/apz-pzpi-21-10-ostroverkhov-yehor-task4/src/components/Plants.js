import React, {useState, useEffect} from "react";
import {request} from "../axios_helper";

const Plants = () => {
    const [plantName, setPlantName] = useState('');
    const [minLightLevel, setMinLightLevel] = useState('');
    const [maxLightLevel, setMaxLightLevel] = useState('');
    const [growthPercentage, setGrowthPercentage] = useState('');
    const [growthPercentage2, setGrowthPercentage2] = useState('');
    const [plantId, setPlantId] = useState('');
    const [plantsData, setPlantsData] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetchPlants();
    }, []);

    const fetchPlants = () => {
        request('GET', '/plants', {})
            .then((response) => {
                setPlantsData(response.data);
            })
            .catch((error) => {
                console.error('Error fetching plants data:', error);
            });
    }

    const onChangeHandler = (event) => {
        const {name, value} = event.target;
        if (name === 'plantName') {
            setPlantName(value);
        } else if (name === 'minLightLevel') {
            setMinLightLevel(value);
        } else if (name === 'maxLightLevel') {
            setMaxLightLevel(value);
        } else if (name === 'growthPercentage') {
            setGrowthPercentage(value);
        } else if (name === 'plantId') {
            setPlantId(value);
        } else if (name === 'growthPercentage2') {
            setGrowthPercentage2(value);
        }
        setError(null);
    };

    const deletePlant = (plantId) => {
        request('DELETE', `/plants/${plantId}`, {})
            .then((response) => {
                setPlantsData(response.data);
                setError(null);
            })
            .catch(() => {
                setPlantsData(null);
                setError('Something went wrong fetching plants.');
            });
    };

    const onSubmit = (event) => {
        event.preventDefault();

        saveNewPlant(plantName, minLightLevel, maxLightLevel, growthPercentage);
    }

    const saveNewPlant = (plantName, minLightLevel, maxLightLevel, growthPercentage) => {
        request('POST', `/plants`, {
            name: plantName,
            minLightLevel: minLightLevel,
            maxLightLevel: maxLightLevel,
            growthPercentage: growthPercentage
        })
            .then((response) => {
                fetchPlants()
            })
            .catch((error) => {
                console.error('Error adding plant:', error);
            });
    };

    const onSubmitChange = (event) => {
        event.preventDefault();

        changeGrowthPercentage(plantId, growthPercentage2);
    }

    const changeGrowthPercentage = (plantId, growthPercentage2) => {
        request('PATCH', `/plants`, {
            plantId: plantId,
            newPercentageValue: growthPercentage2
        })
            .then((response) => {
                fetchPlants()
            })
            .catch((error) => {
                console.error('Error changing growth percentage')
            })
    }

    return (
        <div className="col justify-content-center m-3">
            <div className="row justify-content-center m-3">
                <div className="col-md-4">
                    <div>
                        <h5>Add a New Plant:</h5>
                        <form onSubmit={onSubmit}>
                            <div className="form-outline mb-2">
                                <input
                                    type="text"
                                    id="plantName"
                                    name="plantName"
                                    className="form-control form-control-sm"
                                    style={{width: '250px'}}
                                    onChange={onChangeHandler}
                                    value={plantName}
                                    placeholder="Enter plant name"
                                />
                            </div>
                            <div className="form-outline mb-2">
                                <input
                                    type="number"
                                    id="minLightLevel"
                                    name="minLightLevel"
                                    className="form-control form-control-sm"
                                    style={{width: '250px'}}
                                    onChange={onChangeHandler}
                                    value={minLightLevel}
                                    placeholder="Enter plant min light"
                                />
                            </div>
                            <div className="form-outline mb-2">
                                <input
                                    type="number"
                                    id="maxLightLevel"
                                    name="maxLightLevel"
                                    className="form-control form-control-sm"
                                    style={{width: '250px'}}
                                    onChange={onChangeHandler}
                                    value={maxLightLevel}
                                    placeholder="Enter plant max light"
                                />
                            </div>
                            <div className="form-outline mb-2">
                                <input
                                    type="number"
                                    id="growthPercentage"
                                    name="growthPercentage"
                                    className="form-control form-control-sm"
                                    style={{width: '250px'}}
                                    onChange={onChangeHandler}
                                    value={growthPercentage}
                                    placeholder="Enter plant growth percentage"
                                />
                            </div>
                            <button type="submit" className="btn btn-success btn-block mb-4">
                                Save New Plant
                            </button>
                        </form>
                    </div>
                </div>
                <div className="col-md-4">
                    <div>
                        <h5>Change grow percentage:</h5>
                        <form onSubmit={onSubmitChange}>
                            <div className="form-outline mb-2">
                                <input
                                    type="number"
                                    id="plantId"
                                    name="plantId"
                                    className="form-control form-control-sm"
                                    style={{width: '250px'}}
                                    onChange={onChangeHandler}
                                    value={plantId}
                                    placeholder="Enter plant ID"
                                />
                            </div>
                            <div className="form-outline mb-2">
                                <input
                                    type="number"
                                    id="growthPercentage2"
                                    name="growthPercentage2"
                                    className="form-control form-control-sm"
                                    style={{width: '250px'}}
                                    onChange={onChangeHandler}
                                    value={growthPercentage2}
                                    placeholder="Enter plant growth percentage"
                                />
                            </div>
                            <button type="submit" className="btn btn-success btn-block mb-4">
                                Save New Growth Percentage
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

            {plantsData && (
                <div className="col-12">
                    <h5>Plants List:</h5>
                    <table className="table">
                        <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Name</th>
                            <th scope="col">Min Light Level</th>
                            <th scope="col">Max Light Level</th>
                            <th scope="col">Growth %</th>
                            <th scope="col">Sensors</th>
                            <th scope="col">Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        {plantsData.map((plant) => (
                            <tr key={plant.id}>
                                <td>{plant.id}</td>
                                <td>{plant.name}</td>
                                <td>{plant.minLightLevel}</td>
                                <td>{plant.maxLightLevel}</td>
                                <td>{plant.growthPercentage}</td>
                                <td>{plant.sensors.length > 0 ? (
                                    <ul className="list-unstyled">
                                        {plant.sensors.map((sensor, sensorIndex) => (
                                            <li key={sensorIndex}>
                                                Sensor ID: {sensor.id}, Name: {sensor.name}
                                                <ul>
                                                    {sensor.readings.length > 0 ? (
                                                        <ul className="list-group">
                                                            {sensor.readings.map((reading, readingIndex) => (
                                                                <li key={readingIndex}>
                                                                    <div><strong>Reading ID:</strong> {reading.id}
                                                                    </div>
                                                                    <div>
                                                                        <strong>Name:</strong> {reading.name}, <strong>Value:</strong> {reading.value}
                                                                    </div>
                                                                    <div><strong>Is
                                                                        Warning:</strong> {reading.isWarning ? 'true' : 'false'}
                                                                    </div>
                                                                    <div><strong>Date and
                                                                        Time:</strong> {new Date(reading.dateTime).toLocaleString()}
                                                                    </div>
                                                                </li>
                                                            ))}
                                                        </ul>
                                                    ) : 'No readings'}
                                                </ul>
                                                <br/>
                                                {sensor.lamps.length > 0 ? (
                                                    <ul className="list-bulleted">
                                                        {sensor.lamps.map((lamp, lampIndex) => (
                                                            <li key={lampIndex}>
                                                                <div><strong>Lamp ID:</strong> {lamp.id}</div>
                                                                <div><strong>Name:</strong> {lamp.name}</div>
                                                                <div><strong>Light Level:</strong> {lamp.lightLevel}
                                                                </div>
                                                                <div><strong>Spectrum:</strong> {lamp.spectrum}</div>
                                                            </li>
                                                        ))}
                                                    </ul>
                                                ) : 'No readings'}
                                            </li>
                                        ))}
                                    </ul>
                                ) : 'No sensors'}
                                </td>
                                <td>
                                    <button onClick={() => deletePlant(plant.id)} className={"btn btn-danger"}>
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

export default Plants;
