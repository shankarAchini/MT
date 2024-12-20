import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import Navi from "./Link/Navi";

const Hotel = () => {
  const [hotel, setHotel] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchHotels = async () => {
      try {
        const { data } = await axios.get("http://localhost:8080/fetchAllHotel");
        console.log(data.dataList);
        setHotel(data.dataList);
      } catch (error) {
        console.error("Error fetching hotels:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchHotels();
  }, []);
  const getAddressString = (address) => {
    if (!address) return "Address not available";
    const { addressCity, addressState, addressPinCode } = address;
    return `${addressCity || ""}, ${addressState || ""}, ${addressPinCode || ""}`.replace(/, ,/g, ",").trim();
  };
  return (
    <div style={styles.main}>
      {
      loading ? ( <p>Loading...</p>) : Array.isArray(hotel) && hotel.length > 0 ? (
        hotel.map((ele) => (
          <div key={ele.hotelId} style={styles.container}>
            <img
              src={ele.hotelImage}
              alt={ele.hotelName}
              style={styles.image}
            />
            <center>
              <h2>{ele.hotelName}</h2>
            </center>
            <p>{ele.hotelDescription}</p>
            <p>
              <strong>Rating:</strong> ★★★★☆ {ele.hotelRating}
            </p>
            <p>{ele.hotelEmail}</p>
            <p>
              <strong>Phone:</strong> {ele.hotelPhoneNumber}
            </p>
            <p>
              <strong>Address:</strong> {getAddressString(ele.address)}
            </p>
            <Link to={`/rooms/${ele.hotelId}`}>
              <button style={styles.button}>Rooms</button>
            </Link>
          </div>
          
        ))
      ) : (
        <p>No hotels available.</p>
      )}
    </div>
  );
};

const styles = {
  main: {
    width: "100%",
    display: "flex",
    flexWrap: "wrap",
    justifyContent: "center",
    gap: "10px",
  },
  container: {
    border: "1px solid #ccc",
    borderRadius: "10px",
    padding: "20px",
    marginBottom: "20px",
    maxWidth: "400px",
    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
  },
  image: {
    width: "100%",
    borderRadius: "10px",
  },
  button: {
    marginTop: "10px",
    padding: "10px 15px",
    backgroundColor: "blue",
    color: "white",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
  },
};

export default Hotel;
