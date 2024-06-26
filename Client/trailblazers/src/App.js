import './App.css';
import {
  Route,
   Routes, BrowserRouter
} from "react-router-dom";
import AboutUs from './components/AboutUs';
import Community from './components/Community';
import Algorithm from './components/Algorithm';
// import { Algorithm, Community, MiniGames, Resources, Navbar, AboutUs, Home } from './components';
import Resources from './components/Resources';
import LandingPage from './components/header/landingPage/LandingPage';
import Header from './components/header/Header';
import Footer from './components/Footer';

function App() {
  return (
    <BrowserRouter>
    <div className="app">

      <Header />
    
      <Routes>
        <Route path="/" element={<LandingPage/>}/>
        <Route path="/algorithms" element={<Algorithm/>}/>
        {/* <Route path="/mini-games" element={<MiniGames/>}/> */}
        <Route path="/community" element={<Community/>}/>
        <Route path="/resources" element={<Resources/>}/>
        <Route path="/about-us" element={<AboutUs/>}/>
      </Routes>

      <Footer />
    </div>
    </BrowserRouter>
  );
}

export default App;