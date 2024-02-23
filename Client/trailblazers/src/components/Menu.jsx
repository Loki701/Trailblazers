import React, { useState } from 'react';
import { Dropdown, DropdownButton, Button } from 'react-bootstrap';
import Info from './Info';
import Table from './Table';
const Menu = () => {
  const [button1Title, setButton1Title] = useState("Algorithms");
  const [button2Title, setButton2Title] = useState("Pace");
  const [button3Title, setButton3Title] = useState("Maze");
  const [display, setDisplay] = useState(false);
  const [selectedOptions, setSelectedOptions] = useState([]); // Array to hold the selected options

  const buttonContainerStyle = {
    display: 'flex',
    alignItems: 'center',
  };

  const cardStyle = {
  position: 'relative', /* Change this to absolute if fixed is not working */
  bottom: '0',
  width: '100%'
  }
  const buttonStyle = {
    marginRight: '30px',
   // fontSize: '20vh',
   marginBottom: '20px'
    
     // Adjust this value to increase or decrease the gap
  };

  const startButtonStyle = {
    marginBottom: '20px',
    marginLeft: '50px' // Add a 50px gap to the left of the "Start" button
  };
  const handleSelect = (selectedKey, event) => {
    const { innerText } = event.target;
    const buttonIndex = parseInt(selectedKey.split('-')[0]) - 1;
    const newSelectedOptions = [...selectedOptions];
    newSelectedOptions[buttonIndex] = innerText;
    setSelectedOptions(newSelectedOptions); // Update the selected option in the array
    switch (buttonIndex + 1) {
      case 1:
        setButton1Title(innerText);
        setDisplay(true);
        break;
      case 2:
        setButton2Title(innerText);
        break;
      case 3:
        setButton3Title(innerText);
        break;
      default:
        break;
    }
  };
  

  const handleStartClick = () => {
    console.log(selectedOptions); // Print out the array when the start button is clicked
  };
  return (
    <div className="" style={buttonContainerStyle}>
      <div className='home'>
      <h1 style={{color: "white"}} >Welcome</h1>
      <div className='home menu'>
      <DropdownButton id="dropdown-basic-button-1" title={button1Title} style={buttonStyle} onSelect={handleSelect}>
        <Dropdown.Item eventKey="1-1">Dijkstra</Dropdown.Item>
        <Dropdown.Item eventKey="1-2">Bellman-Ford</Dropdown.Item>
        <Dropdown.Item eventKey="1-3">Breadth-first search</Dropdown.Item>
        <Dropdown.Item eventKey="1-4">Depth-first search</Dropdown.Item>
      </DropdownButton>

      <DropdownButton id="dropdown-basic-button-2" title={button2Title} style={buttonStyle} onSelect={handleSelect}>
        <Dropdown.Item eventKey="2-1">Option 4</Dropdown.Item>
        <Dropdown.Item eventKey="2-2">Option 5</Dropdown.Item>
        <Dropdown.Item eventKey="2-3">Option 6</Dropdown.Item>
      </DropdownButton>
      
      <DropdownButton id="dropdown-basic-button-3" title={button3Title} style={buttonStyle} onSelect={handleSelect}>
        <Dropdown.Item eventKey="3-1">Option 7</Dropdown.Item>
        <Dropdown.Item eventKey="3-2">Option 8</Dropdown.Item>
        <Dropdown.Item eventKey="3-3">Option 9</Dropdown.Item>
      </DropdownButton>
      <Button variant="btn btn-light" style={startButtonStyle} onClick={handleStartClick}>Start</Button>
      </div>
      <Table />
      {display && <Info title={button1Title} />}
      </div>
  
    </div>
     
  );
};

export default Menu;
