import './App.css';
import {
  Route,
   Routes, BrowserRouter
} from "react-router-dom";
import AboutUs from './components/AboutUs';

// import { Algorithm, Community, MiniGames, Resources, Navbar, AboutUs, Home } from './components';

import LandingPage from './components/header/landingPage/LandingPage';
import Header from './components/header/Header';

function App() {
  return (
    <BrowserRouter>
    <div className="app">

      <Header />
    
      <Routes>
        <Route path="/" element={<LandingPage/>}/>
        {/* <Route path="/algorithms" element={<Algorithm/>}/> */}
        {/* <Route path="/mini-games" element={<MiniGames/>}/> */}
        {/* <Route path="/community" element={<Community/>}/> */}
        {/* <Route path="/resources" element={<Resources/>}/> */}
        <Route path="/about-us" element={<AboutUs/>}/>
      </Routes>
    </div>
    </BrowserRouter>
  );
}

export default App;