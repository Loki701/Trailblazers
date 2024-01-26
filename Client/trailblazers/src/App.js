// App.js
import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Nav  from './components/Nav';
import Home from "./components/Home";
import About from './components/About';
import OptionsTab from './components/OptionsTab.jsx';

function App() {
  return (
    <div><Nav></Nav></div>
    // <Router>
    //   <Navbar bg="light" expand="lg">
    //     <Navbar.Brand href="/">My Website</Navbar.Brand>
    //     <Navbar.Toggle aria-controls="basic-navbar-nav" />
    //     <Navbar.Collapse id="basic-navbar-nav">
    //       <Nav className="mr-auto">
    //         <Nav.Link href="/">Home</Nav.Link>
    //         <Nav.Link href="/about">About</Nav.Link>
    //       </Nav>
    //     </Navbar.Collapse>
    //   </Navbar>
    //   <OptionsTab />
    //   <Switch>
    //     <Route path="/" exact component={Home} />
    //     <Route path="/about" component={About} />
    //   </Switch>
    // </Router>
  );
}

export default App;