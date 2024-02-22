import React, { useState } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import {Logo} from "../images";

const Navbar = () => {
    const navigate = useNavigate();
    const [showMenu, setShowMenu] = useState(false);

    const handleLinkClick = (path) => {
        navigate(path);
        setShowMenu(false); // Close the menu after clicking a link
    };

    return (
        <nav className="navbar navbar-expand-lg" style={{ backgroundColor: '#90C2E7'}}>
            <div className="container">
                <div className="navbar-brand"> 
                    <NavLink to="/"  className="logo" onClick={() => handleLinkClick('/')} style={{ cursor: 'pointer' }}>
                        <img src={Logo} alt="Logo"  />
                    </NavLink>
                </div>
                <button className="navbar-toggler" type="button" onClick={() => setShowMenu(!showMenu)}>
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className={`collapse navbar-collapse ${showMenu ? 'show' : ''}`} style={{ color: 'white' }}>
                    <ul className="navbar-nav ml-auto mx-auto text-center">
                        <li className="nav-item">
                            <NavLink className="nav-link" to="/algorithms" onClick={() => handleLinkClick('/algorithms')}>ALGORITHMS</NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink className="nav-link" to="/mini-games" onClick={() => handleLinkClick('/mini-games')}>MINIGAMES</NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink className="nav-link" to="/community" onClick={() => handleLinkClick('/community')}>COMMUNITY</NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink className="nav-link" to="/resources" onClick={() => handleLinkClick('/resources')}>RESOURCES</NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink className="nav-link" to="/about-us" onClick={() => handleLinkClick('/about-us')}>ABOUT US</NavLink>
                        </li>
                    </ul>
                </div>
            </div>
            <style>
                {`
                    .nav-link {
                        font-size: 17px; /* Increase the font size of dropdown items */
                    }
                `}
            </style>
        </nav>
    );
};

export default Navbar;
