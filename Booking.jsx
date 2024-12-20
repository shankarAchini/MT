import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom';


const Booking = () => {
    let { roomId } = useParams();
    let [Rooms, setRooms] = useState([])
    const [totalPrice, setTotalPrice] = useState(0);
    console.log(totalPrice);
    
    useEffect(() => {

        let fetchData = async () => {
            let { data } = await axios.get(`http://localhost:8080/fetchRoomById?roomId=${roomId}`)
            console.log(data.data);
            setRooms([data.data]);
        }
        fetchData()
    }, [roomId])

    const [formData, setFormData] = useState({

        customerName: "",
        customerEmail: "",
        startDate: "",
        endDate: "",
        totalPrice:totalPrice,
        roomId: roomId
    });
    useEffect(() => {

        if (formData.startDate && formData.endDate && Rooms.length > 0) {
            const start = new Date(formData.startDate);
            const end = new Date(formData.endDate);
            const days = Math.ceil((end - start) / (1000 * 60 * 60 * 24))+1;
            if (days > 0) {
                const roomPrice = Rooms[0].roomPrice;
                setTotalPrice(days * roomPrice);
            } else {
                setTotalPrice(0);
            }
        }
    }, [formData.startDate, formData.endDate, Rooms]);
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            console.log("Sending FormData:", formData);

            const response = axios.post(`http://localhost:8080/saveBooking?roomId=${roomId}`,
                formData,
                { headers: { "Content-Type": "application/json" } }
            );
            console.log("Response from Backend:", (await response).status);
            alert("Booking successful!");
            if (response.status === 200) {
                setFormData({
                    customerName: "",
                    customerEmail: "",
                    startDate: "",
                    endDate: "",
                     totalPrice:totalPrice,
                    roomId: roomId,
                });
                setTotalPrice(0);
             
            } 
        } catch (error) {
            console.error("Error occurred:", error);

            if (error.response) {
                console.error("Backend Error Response:", error.response);
                alert(`Error: ${error.response.data.message || "An error occurred."}`);
            } else {
                alert("An unexpected error occurred. Please try again.");
            }
        }
    };

    return (<div style={styles.formContainer}>
        <h2>Book a Room</h2>
        {
            Rooms == [] ? "loading.." : Rooms.map((ele, ind) => {
                return <div style={styles.formGroup} key={ind}>
                    <label style={styles.label}>Room:{ele.roomNumber}</label>
                </div>
            })
        }
        <form onSubmit={handleSubmit} >

            <div style={styles.formGroup}>
                <input type="hidden" name="roomId" value={formData.roomId} />

                <label style={styles.label}>Customer Name:</label>
                <input style={styles.input}
                    type="text"
                    name="customerName"
                    value={formData.customerName}
                    onChange={(e) => setFormData({ ...formData, customerName: e.target.value })}
                    required
                />
            </div>
            <div style={styles.formGroup}>
                <label style={styles.label}>Customer Email:</label>
                <input
                    style={styles.input}
                    type="email"
                    name="customerEmail"
                    value={formData.customerEmail}
                    onChange={(e) => setFormData({ ...formData, customerEmail: e.target.value })}
                    required
                />
            </div>
            <div style={styles.formGroup}>
                <label style={styles.label}>Start Date:</label>
                <input
                    style={styles.input}
                    type="date"
                    name="startDate"
                    value={formData.startDate}
                    onChange={(e) => setFormData({ ...formData, startDate: e.target.value })}
                    required
                />
            </div>
            <div style={styles.formGroup}>
                <label style={styles.label}>End Date:</label>
                <input
                    style={styles.input}
                    type="date"
                    name="endDate"
                    value={formData.endDate}
                    onChange={(e) => setFormData({ ...formData, endDate: e.target.value })}
                    required
                />
            </div>

            <div style={styles.formGroup}>
                <p>
                    <span style={styles.totalPrice}>Total Price:$ {totalPrice} </span>
                </p>
            </div>
            <button
                type="submit"
                style={{ ...styles.button }}
                onMouseOver={(e) => (e.target.style.backgroundColor = styles.buttonHover.backgroundColor)}
                onMouseOut={(e) => (e.target.style.backgroundColor = styles.button.backgroundColor)}
            >
                Book Now
            </button>
            <br /><br />

            <Link to={"/bookinginfo"}>
                <button style={styles.button} >Booking Info</button>
            </Link>
        </form>
    </div>
    )
}

export default Booking

const styles = {
    formContainer: {
        maxWidth: "450px",
        margin: "0 auto",
        padding: "1em",
        border: "1px solid #ccc",
        borderRadius: "5px",
        boxShadow: "0px 4px 6px rgba(0, 0, 0, 0.1)",

    },
    formGroup: {
        marginBottom: "1em",
    },
    label: {
        marginBottom: ".5em",
        color: "#333",
        display: "block",
        fontWeight: "bold",
    },
    input: {
        border: "1px solid #ccc",
        padding: ".5em",
        fontSize: "1em",
        width: "400px",
        borderRadius: "4px",
    },
    button: {
        padding: "0.7em",
        color: "#fff",
        backgroundColor: "#007bff",
        border: "none",
        borderRadius: "5px",
        cursor: "pointer",
    },
    buttonHover: {
        backgroundColor: "#0056b3",
    },
    totalPrice: {
        fontWeight: "bold",
        fontSize: "1.2em",
        color: "#007bff",
    },
};