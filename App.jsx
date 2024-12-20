import React from 'react'
import { BrowserRouter, Routes, Route, Router } from 'react-router-dom';
import Hotel from './Hotel';
import Room from './Room';
import Booking from './Booking';
import BookingInfo from './BookingInfo';
import AddHotel from './AddHotel';
import AddRoom from './AddRoom';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Hotel />} />
        <Route path="/rooms/:hotelId" element={<Room />} />
        <Route path="/booking/:roomId" element={<Booking/>} />
        <Route path="/bookinginfo" element={<BookingInfo/>}/>
        <Route path="/addhotel" element={<AddHotel/>}/>
        <Route path="/addroom" element={<AddRoom/>}/>
      </Routes>
    </BrowserRouter>
  )
}

export default App