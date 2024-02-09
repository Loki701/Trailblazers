import './App.css';
import {
  Route,
  Link, Routes, BrowserRouter
} from "react-router-dom";
import { Algorithm, Community, MiniGames, Resources, Navbar, AboutUs, Home } from './components';

import { StateProvider } from './context/StateContext';
import reducer, {initialState} from './context/StateReducers';


function App() {
  return (
    <StateProvider initialState={initialState} reducer={reducer}>
    <BrowserRouter>
    <div className="App">

      <Navbar />
    
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/algorithms" element={<Algorithm/>}/>
        <Route path="/mini-games" element={<MiniGames/>}/>
        <Route path="/community" element={<Community/>}/>
        <Route path="/resources" element={<Resources/>}/>
        <Route path="/about-us" element={<AboutUs/>}/>
      </Routes>
    </div>
    </BrowserRouter>
    </StateProvider>
  );
}

export default App;
