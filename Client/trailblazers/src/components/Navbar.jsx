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
        <nav className="navbar navbar-expand-lg" style={{ backgroundColor: '#90C2E7', color: 'white' }}>
            <div className="container">
                <div className="navbar-brand"> {/* Use a div instead of NavLink for the logo */}
                    <NavLink to="/"  className="logo" onClick={() => handleLinkClick('/')} style={{ cursor: 'pointer' }}><img src={Logo} alt="Logo"  />
</NavLink>
                </div>
                <button className="navbar-toggler" type="button" onClick={() => setShowMenu(!showMenu)}>
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className={`collapse navbar-collapse ${showMenu ? 'show' : ''}`}>
                    <ul className="navbar-nav m-auto">
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
        </nav>
    );
};

export default Navbar;
