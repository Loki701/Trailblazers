import "./Header.css";
import { useState } from "react";
import Logo from "../../images/logo.png";
import { useNavigate } from "react-router-dom";

function className(...classes) {
    return classes.filter(Boolean).join(" ");
  }


const Header  = () => {
    const [menuActive, setMenuActive] = useState(false);
    const navigate = useNavigate();

    const handleExploreClick = (path) => {
        setMenuActive(false);
        navigate(path);
    }


    const handleMobileMenuClick = () => {
        setMenuActive(!menuActive);
    } 
    return(
        <header id="header" className="header">
            <div className="header-container">
                <div className="content">
                    <img src={Logo} alt="Logo" onClick={() => handleExploreClick("/")}/>
                </div>

                {menuActive? (
                    <span class="material-symbols-outlined" onClick={handleMobileMenuClick}>
                    close
                    </span>
                ):(    
                <span className="material-symbols-outlined" onClick={handleMobileMenuClick}>
                menu
                </span>
                )}

                <div className = {className(menuActive? "active": "", "navbar")} >
                    <ul className="column">
                        <li className="link">
                            <a onClick={() => handleExploreClick("/algorithms")}>Explore Algorithms</a>
                        </li>
                        <li className="link">
                            <a onClick={() => handleExploreClick("/about-us")}>About Us</a>
                        </li>
                        <li className="link">
                            <a onClick={()=> handleExploreClick("/community")}>FAQ</a>
                        </li>
                        <li className="link">
                            <a onClick={()=> handleExploreClick("/resources")}>Resources</a>
                        </li>
                    </ul>
                    {/* <div className="actions">
                        <button className="button">Sing Up</button>
                        <button className="button">Log In</button>
                    </div> */}
                </div>
                
            </div>
        </header>
    );
}

export default Header;