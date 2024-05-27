import React from 'react';
import ReactDOM from 'react-dom/client';
import { Footer, Navbar } from './components/Layout';
import Home from './pages/Home';
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import About_us from './pages/About_us';
import Not_found from './pages/Not_found';
import SightsList from './pages/admin/sights/SightsList';


function App() {
  return (
    <BrowserRouter>
      <Navbar/>
      <Routes>
        <Route path='/' element={<Home/>} />
        <Route path='/about' element={<About_us/>} />
        <Route path='/admin/sights' element={<SightsList/>} />
        <Route path='*' element={<Not_found/>} />
      </Routes>
      <Footer/>
    </BrowserRouter>
  )
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

