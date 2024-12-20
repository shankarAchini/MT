import React, { useState } from 'react';
import axios from 'axios';

const AddRoom = () => {
    const [room, setRoom] = useState({
        roomType: '',
        roomPrice: '',
        roomNumber: '',
        roomIsAvailable: true,
        roomImage: '',
        floorNumber: '',
        roomDescription: '',
        roomCapacity: '',
        hotelId: '',

    });
    
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setRoom((prevRoom) => ({
            ...prevRoom,
            [name]: value
        }));
    };


    const handleSubmit = async (e) => {
        e.preventDefault();


        try {
            console.log("Room data to send:", room);
            await axios.post(`http://localhost:8080/saveRoom?hotelId=${room.hotelId}`, room);
            alert('Room added successfully');
            setRoom({
                roomType: '',
                roomPrice: '',
                roomNumber: '',
                roomIsAvailable: true,
                roomImage: '',
                floorNumber: '',
                roomDescription: '',
                roomCapacity: '',
                hotelId: ''
            });
        } catch (error) {
            console.error('Error adding room:', error);
            alert('Error adding room');
        }
    };


    return (
        <div style={formContainerStyle}>
            <h2 style={formHeadingStyle}>Add Room</h2>
            <form onSubmit={handleSubmit}>
                <div style={formGroupStyle}>
                    <label style={labelStyle}>Room Type:</label>
                    <input
                        type="text"
                        name="roomType"
                        value={room.roomType}
                        onChange={handleInputChange}
                        style={inputStyle}
                        required
                    />
                </div>
                <div style={formGroupStyle}>
                    <label style={labelStyle}>Room Price:</label>
                    <input
                        type="number"
                        name="roomPrice"
                        value={room.roomPrice}
                        onChange={handleInputChange}
                        style={inputStyle}
                        required
                    />
                </div>
                <div style={formGroupStyle}>
                    <label style={labelStyle}>Room Number:</label>
                    <input
                        type="text"
                        name="roomNumber"
                        value={room.roomNumber}
                        onChange={handleInputChange}
                        style={inputStyle}
                        required
                    />
                </div>
                <div style={formGroupStyle}>
                    <label style={labelStyle}>Room Image URL:</label>
                    <input
                        type="text"
                        name="roomImage"
                        value={room.roomImage}
                        onChange={handleInputChange}
                        style={inputStyle}
                    />
                </div>
                <div style={formGroupStyle}>
                    <label style={labelStyle}>Floor Number:</label>
                    <input
                        type="number"
                        name="floorNumber"
                        value={room.floorNumber}
                        onChange={handleInputChange}
                        style={inputStyle}
                        required
                    />
                </div>
                <div style={formGroupStyle}>
                    <label style={labelStyle}>Room Description:</label>
                    <textarea
                        name="roomDescription"
                        value={room.roomDescription}
                        onChange={handleInputChange}
                        style={textareaStyle}
                        required
                    />
                </div>
                <div style={formGroupStyle}>
                    <label style={labelStyle}>Room Capacity:</label>
                    <input
                        type="number"
                        name="roomCapacity"
                        value={room.roomCapacity}
                        onChange={handleInputChange}
                        style={inputStyle}
                        required
                    />
                </div>
                <div style={formGroupStyle}>
                    <label style={labelStyle}>Hotel ID (for room association):</label>
                    <input
                        type="number"
                        name="hotelId"
                        value={room.hotelId}
                        onChange={handleInputChange}
                        style={inputStyle}
                        required
                    />
                </div>
                <button type="submit" style={buttonStyle}>Add Room</button>
            </form>
        </div>
    );
};

const formContainerStyle = {
    backgroundColor: 'white',
    padding: '20px',
    borderRadius: '8px',
    boxShadow: '0 2px 10px rgba(0, 0, 0, 0.1)',
    width: '400px',
    maxWidth: '90%',
    margin: 'auto'
};

const formHeadingStyle = {
    textAlign: 'center',
    marginBottom: '20px',
    color: '#333'
};

const formGroupStyle = {
    marginBottom: '15px'
};

const labelStyle = {
    fontWeight: 'bold',
    marginBottom: '5px'
};

const inputStyle = {
    width: '100%',
    padding: '10px',
    borderRadius: '4px',
    border: '1px solid #ddd',
    fontSize: '14px'
};

const textareaStyle = {
    width: '100%',
    padding: '10px',
    borderRadius: '4px',
    border: '1px solid #ddd',
    fontSize: '14px',
    resize: 'vertical'
};

const buttonStyle = {
    backgroundColor: '#007BFF',
    color: 'white',
    padding: '10px 20px',
    border: 'none',
    borderRadius: '4px',
    fontSize: '16px',
    cursor: 'pointer',
    width: '100%'
};

export default AddRoom;
