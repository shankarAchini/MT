import axios from 'axios';
import React, { useState } from 'react'

const BookingInfo = () => {
    const [email, setEmail] = useState('');
    const [bookings, setBookings] = useState([]);

    let booking = async () => {
        try {
            let response = await axios.get(`http://localhost:8080/fetchBookingByEmail?Email=${email}`);
            console.log("API Response:", response.data);
            setBookings(response.data.dataList || []); 
        } catch (error) {
            console.error("Error fetching bookings:", error);
            setBookings([]); // Reset bookings on error
        }
    };
    
    let handleDelete = async (bookingId) => {
        await axios.delete(`http://localhost:8080/deleteBookingById?bookingId=${bookingId}`);
        booking()
    }
    return (
        <div style={containerStyle}>
            <h2>Booking List</h2>
            <input
                type="email"
                placeholder="Enter Customer Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                style={inputStyle}
            />
            <button onClick={booking} style={buttonStyle}>
                Get Bookings
            </button>

            <ul style={listStyle}>
                {Array.isArray(bookings) && bookings.map((booking, ind) => (
                    <li key={ind} style={listItemStyle}>
                        <p><strong>Name:</strong> {booking.customerName}</p>
                        <p><strong>Email:</strong> {booking.customerEmail}</p>
                        <p><strong>Start Date:</strong> {booking.startDate}</p>
                        <p><strong>End Date:</strong> {booking.endDate}</p>
                        <p><strong>Total Price:</strong> {booking.totalPrice}</p>
                        <button
                            style={deleteButtonStyle}
                            onClick={() => handleDelete(booking.bookingId)}
                        >
                            Delete Booking
                        </button>
                    </li>
                ))}
                {Array.isArray(bookings) && bookings.length === 0 && (
                    <p>No bookings available for this email.</p>
                )}
            </ul>
        </div>


    )
}
const containerStyle = {
    width: '80%',
    maxWidth: '600px',
    margin: '20px auto',
    padding: '20px',
    backgroundColor: '#ffffff',
    borderRadius: '8px',
    boxShadow: '0 2px 10px rgba(0, 0, 0, 0.1)',
};

const inputStyle = {
    padding: '10px',
    margin: '5px 0',
    border: '1px solid #ccc',
    borderRadius: '4px',
    fontSize: '1rem',
    width: '100%',
};

const buttonStyle = {
    backgroundColor: '#4CAF50',
    color: 'white',
    padding: '10px',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
    fontSize: '1rem',
    width: '100%',
};

const listStyle = {
    listStyleType: 'none',
    padding: '0',
};

const listItemStyle = {
    backgroundColor: '#f9f9f9',
    margin: '10px 0',
    padding: '15px',
    borderRadius: '6px',
    boxShadow: '0 2px 5px rgba(0, 0, 0, 0.1)',
};

const deleteButtonStyle = {
    backgroundColor: '#f44336',
    color: 'white',
    padding: '8px',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
    fontSize: '0.9rem',
    marginTop: '10px',
};

export default BookingInfo