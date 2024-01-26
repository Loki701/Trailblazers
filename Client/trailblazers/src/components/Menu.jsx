import { Tab, Nav } from 'react-bootstrap';
//import React, { useRef } from 'react';
function Menu() {
    return (
        <Tab.Container id="left-tabs-example" defaultActiveKey="first">
            <Nav variant="pills" className="flex-column">
                <Nav.Item>
                    <Nav.Link eventKey="first">Option 1</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="second">Option 2</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="third">Option 3</Nav.Link>
                </Nav.Item>
            </Nav>
            <Tab.Content>
                <Tab.Pane eventKey="first">
                    <p>Content for Option 1</p>
                </Tab.Pane>
                <Tab.Pane eventKey="second">
                    <p>Content for Option 2</p>
                </Tab.Pane>
                <Tab.Pane eventKey="third">
                    <p>Content for Option 3</p>
                </Tab.Pane>
            </Tab.Content>
        </Tab.Container>
    );
}

export default Menu;