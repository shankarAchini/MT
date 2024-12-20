import React, { useState } from 'react';
import axios from 'axios';

const AddHotel = () => {
    const [hotel, setHotel] = useState({
        hotelName: '',
        hotelPhoneNumber: '',
        hotelEmail: '',
        hotelDescription: '',
        hotelRating: '',
        hotelImage: '',
        address: {
            addressCity: '',
            addressState: '',
            addressPinCode: '',
        }
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setHotel((prevHotel) => ({
            ...prevHotel,
            [name]: value
        }));
    };

    const handleAddressChange = (e) => {
        const { name, value } = e.target;
        setHotel((prevHotel) => ({
            ...prevHotel,
            address: {
                ...prevHotel.address,
                [name]: value
            }
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post('http://localhost:8080/saveHotel', hotel);
            alert('Hotel added successfully');
            setHotel({
                hotelName: '',
                hotelPhoneNumber: '',
                hotelEmail: '',
                hotelDescription: '',
                hotelRating: '',
                hotelImage: '',
                address: {
                    addressCity: '',
                    addressState: '',
                    addressPinCode: '',
                }
            });
        } catch (error) {
            console.error('Error adding hotel:', error);
            alert('Error adding hotel');
        }
    };

   
    return (
        <div style={formContainerStyle}>
            <h2 style={formHeadingStyle}>Add Hotel</h2>
            <form onSubmit={handleSubmit}>
                <div style={formGroupStyle}>
                    <label style={labelStyle}>Hotel Name:</label>
                    <input
                        type="text"
                        name="hotelName"
                        value={hotel.hotelName}
                        onChange={handleInputChange}
                        style={inputStyle}
                        required
                    />
                </div>
                <div style={formGroupStyle}>
                    <label style={labelStyle}>Hotel Phone Number:</label>
                    <input
                        type="tel"
                        name="hotelPhoneNumber"
                        value={hotel.hotelPhoneNumber}
                        onChange={handleInputChange}
                        style={inputStyle}
                        required
                    />
                </div>
                <div style={formGroupStyle}>
                    <label style={labelStyle}>Hotel Email:</label>
                    <input
                        type="email"
                        name="hotelEmail"
                        value={hotel.hotelEmail}
                        onChange={handleInputChange}
                        style={inputStyle}
                        required
                    />
                </div>
                <div style={formGroupStyle}>
                    <label style={labelStyle}>Hotel Description:</label>
                    <textarea
                        name="hotelDescription"
                        value={hotel.hotelDescription}
                        onChange={handleInputChange}
                        style={textareaStyle}
                        required
                    />
                </div>
                <div style={formGroupStyle}>
                    <label style={labelStyle}>Hotel Rating:</label>
                    <input
                        type="number"
                        name="hotelRating"
                        value={hotel.hotelRating}
                        onChange={handleInputChange}
                        style={inputStyle}
                        required
                    />
                </div>
                <div style={formGroupStyle}>
                    <label style={labelStyle}>Hotel Image URL:</label>
                    <input
                        type="text"
                        name="hotelImage"
                        value={hotel.hotelImage}
                        onChange={handleInputChange}
                        style={inputStyle}
                    />
                </div>
                <div style={formGroupStyle}>
                    <h3>Address</h3>
                    <div>
                        <div style={formGroupStyle}>
                            <label style={labelStyle}>Address City:</label>
                            <input
                                type="text"
                                name="addressCity"
                                value={hotel.address.addressCity}
                                onChange={handleAddressChange}
                                style={inputStyle}
                                required
                            />
                        </div>
                        <div style={formGroupStyle}>
                            <label style={labelStyle}>Address State:</label>
                            <input
                                type="text"
                                name="addressState"
                                value={hotel.address.addressState}
                                onChange={handleAddressChange}
                                style={inputStyle}
                                required
                            />
                        </div>
                        <div style={formGroupStyle}>
                            <label style={labelStyle}>Address Pin Code:</label>
                            <input
                                type="text"
                                name="addressPinCode"
                                value={hotel.address.addressPinCode}
                                onChange={handleAddressChange}
                                style={inputStyle}
                                required
                            />
                        </div>
                    </div>
                </div>
                
                <button type="submit"  style={buttonStyle}>Add Hotel</button>
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


export default AddHotel;
