import {Logo} from "../images"

import { useNavigate } from "react-router-dom";
const Navbar = () =>{
    const navigate = useNavigate();

    const handleLinkClick = (e)=>{
        navigate(`/${e.target.id}`)
    }
    return(
        <div className="header">
            <div className="logo">
                <img  src={Logo} alt="Logo" onClick={(e) => handleLinkClick(e)}/>
            </div>
            <ul className="links">
                <li id="algorithms" onClick={(e) => handleLinkClick(e)}>Algorithms</li>
                <li id="mini-games" onClick={(e) => handleLinkClick(e)}>MiniGames</li>
                <li id="community" onClick={(e) => handleLinkClick(e)}>Community</li>
                <li id="resources" onClick={(e) => handleLinkClick(e)}>Resources</li>
                <li id="about-us" onClick={(e) => handleLinkClick(e)}>About Us</li>
                <span className="active-nav"></span>
            </ul>

        </div>
    );
}

export default Navbar;