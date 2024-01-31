import './App.css';
import {Navbar,Nav,Container} from 'react-bootstrap'
import {
  Route,
  Link, Routes, BrowserRouter
} from "react-router-dom";
import { Algorithm } from './components/Algorithm';
import { Community } from './components/Community';
import { MiniGames } from './components/MiniGames';
import { Resources} from './components/Resources';
import { AboutUs } from './components/AboutUs';
import Home from './components/Home'; 
import NavDropdown from 'react-bootstrap/NavDropdown';

import { StateProvider } from './context/StateContext';
import reducer, {initialState} from './context/StateReducers';


function App() {
  return (
    <StateProvider initialState={initialState} reducer={reducer}>
    <BrowserRouter>
    <div className="App">
      <>
      <Navbar style={{backgroundColor:'#008ae6'}} data-bs-theme="dark">
        <Container>
        <Link to="/" style={{ textDecoration: 'none' }}>
                <Navbar.Brand style={{ fontWeight: 'bolder' , color: 'black'}}>TrailBlazers</Navbar.Brand>
              </Link>
            <Nav className='ml-auto'>
            <Nav.Link as={Link} to="/">Home</Nav.Link>
              <NavDropdown title="Algorithm" id="navbarScrollingDropdown">
              <NavDropdown.Item href="#action1">Bellman Ford Algorithm</NavDropdown.Item>
              <NavDropdown.Item href="#action2">
                Dijkstra's Algorithm
              </NavDropdown.Item>
              <NavDropdown.Item href="#action3">
                Algorithm three
              </NavDropdown.Item>
            </NavDropdown>
              <Nav.Link as={Link} to="/mini-games">MiniGames</Nav.Link>
              <Nav.Link as={Link} to="/community">Community</Nav.Link>
              <Nav.Link as={Link} to="/resources">Resources</Nav.Link>
              <Nav.Link as={Link} to="/about-us">About Us</Nav.Link>
            </Nav>
        
        </Container>
      </Navbar>
      
    </>
    <div>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/algorithm" element={<Algorithm/>}/>
        <Route path="/mini-games" element={<MiniGames/>}/>
        <Route path="/community" element={<Community/>}/>
        <Route path="/resources" element={<Resources/>}/>
        <Route path="/about-us" element={<AboutUs/>}/>

      </Routes>
    </div>
    </div>
    </BrowserRouter>
    </StateProvider>
  );
}

export default App;
