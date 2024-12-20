import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, Links, useParams } from "react-router-dom";
import Navi from "./Link/Navi";

const Room = () => {
  let [room, setRoom] = useState([])
  let { hotelId } = useParams();


  useEffect(() => {
    let handleRoom = async () => {
      const id = parseInt(hotelId, 10);
      let {data} = await axios.get(`http://localhost:8080/fetchRoomByHotelId?hotelId=${id}`)
      console.log(data.dataList);  
      setRoom(data.dataList)
    }
    handleRoom()
  }, [hotelId])
  return (<div style={styles.main}>
         <Navi/>
         
    <img src="https://images.unsplash.com/photo-1631049552057-403cdb8f0658?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGhvdGVsJTIwYmVkcm9vbXxlbnwwfHwwfHx8MA%3D%3Dhttps://images.unsplash.com/photo-1631049552057-403cdb8f0658?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGhvdGVsJTIwYmVkcm9vbXxlbnwwfHwwfHx8MA%3D%3D" alt="image" height={350} width="100%" />
    {
      room == [] ? "loading.." : room.map((ele, ind) => {
        
        return <div style={styles.container} key={ind}>
          <img src={ele.roomImage} style={styles.image} />
          <p>
           {ele.roomDiscription}
          </p>
          <h4>RoomType: {ele.roomType}</h4>
          <p>
            <strong>Price:</strong> ₹ {ele.roomPrice}
          </p>
          <p>
            <strong>Available:</strong>{ele.roomIsAvailable ? "Yes" : "No"}
          </p>
          <Link to={`/booking/${ele.roomId}`}> 
          <button style={styles.button} >Book</button>
          </Link>
        </div>
      })
    }
  
  </div>

  )
};

const styles = {
  main: {
    width: "100%",
    display: "flex",
    flexWrap: "wrap",
    justifyContent: "center",
    gap: "10px"
  },
  container: {
    width: "20%",
    border: "1px solid #ddd",
    borderRadius: "10px",
    padding: "15px",
    backgroundColor: "#f9f9f9",
  },
  image: {
    width: "100%",
    height: "150px",
    objectFit: "cover",
    borderRadius: "10px",
    marginBottom: "10px",
  },
  button: {
    color: "white",
    borderRadius: "5px",
    backgroundColor: "blue",

  }
};

export default Room;
