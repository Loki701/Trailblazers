import { useState } from "react"
import Table from "../table/Table";
import "./Home.css";
import "./DropdownStyle.scss";

const Home = () => {

  const [playStatus, setPlayStatus] = useState(true);

  return (
    <div className="home">
      <div className="home-header">
        <h1>Welcome Trailblazers</h1>
      </div>
      <div className="home-dropdown-container">
        <div className="dropdown-algo">
          <input type="checkbox" id="dropdown-algo" />
          <label className="dropdown-algo__face" for="dropdown-algo">
            <div className="dropdown-algo__text">Algorithm</div>
            <div className="dropdown-algo__arrow"></div>
          </label>
          <ul className="dropdown-algo__items">
            <li>Algo 1</li>
            <li>Algo 2</li>
            <li>Algo 3</li>
            <li>Algo 4</li>
            <li>Algo 5</li>
          </ul>
        </div>
        <div className="dropdown-pace">
          <input type="checkbox" id="dropdown-pace" />
          <label className="dropdown-pace__face" for="dropdown-pace">
            <div className="dropdown-pace__text">Pace</div>
            <div className="dropdown-pace__arrow"></div>
          </label>
          <ul className="dropdown-pace__items">
            <li>Pace 1</li>
            <li>Pace 2</li>
            <li>Pace 3</li>
            <li>Pace 4</li>
            <li>Pace 5</li>
          </ul>
        </div>
        <div className="dropdown-maze">
          <input type="checkbox" id="dropdown-maze" />
          <label className="dropdown-maze__face" for="dropdown-maze">
            <div className="dropdown-maze__text">Maze</div>
            <div className="dropdown-maze__arrow"></div>
          </label>
          <ul className="dropdown-maze__items">
            <li>Maze 1</li>
            <li>Maze 2</li>
            <li>Maze 3</li>
            <li>Maze 4</li>
            <li>Maze 5</li>
          </ul>
        </div>
      </div>
      <svg>
        <filter id="goo">
          <feGaussianBlur in="SourceGraphic" stdDeviation="10" result="blur" />
          <feColorMatrix in="blur" type="matrix" values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 18 -7" result="goo" />
          <feBlend in="SourceGraphic" in2="goo" />
        </filter>
      </svg>
      <div className="home-content">
        <div className="table-container">
          <Table />
          <div className="play-button">
            {playStatus ? (
              <span class="material-symbols-outlined">
                play_arrow
              </span>
            ) : (
              <span class="material-symbols-outlined">
                replay
              </span>
            )}
          </div>
        </div>
        <div className="algorithm-info">
          <h2>Dijstram</h2>
          <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Impedit, sed nihil consequuntur sequi nisi repudiandae. Blanditiis omnis ipsa perspiciatis est. Quis soluta fugit et possimus minus omnis quos, non sequi?</p>
        </div>
      </div>
    </div>
  );
};

export default Home;
