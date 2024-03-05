import { useEffect, useState } from "react"
import Table from "../table/Table";
import "./Home.css";
import "./DropdownStyle.scss";


import axios from "../../api/axios";

const Home = () => {

  const [playStatus, setPlayStatus] = useState(true);
  const [currentAlgo, setCurrentAlgo] = useState("Algorithm");
  const [currentPace, setCurrentPace] = useState("Pace");
  const [currentMaze, setCurrentMaze] = useState("Maze");
  const [visualizationResult, setVisualizationResult] = useState(null);

  const handleCurrentOnClick = (selector, value) => {
    switch (selector) {
      case "algo":
        setCurrentAlgo(value);
        break;
      case "pace":
        setCurrentPace(value);
        break;
      case "maze":
        setCurrentMaze(value);
        break;
      default:
        break;
    }
  }
  const handlePlayClick = () => {
    if (currentMaze !== "Maze" && currentAlgo !== "Algorithm" && currentPace !== "Pace") {
      try {
        const response = axios.post("/api/algorithm",
          JSON.stringify({ currentAlgo, currentPace, currentMaze }),
          {
            headers: { 'Content-Type': 'application/json' },
            withCredentials: true
          }
        );
        if (response.isCompletable === true) {
          setVisualizationResult(response);
          setPlayStatus(!playStatus);
        }else{
          // let user know its not completable
        }
      } catch (err) {
        if (!err?.response) {
          // setErrMsg('No Server Response');
        } else if (err.response?.status === 400) {
          // setErrMsg('Incorrect Username or Password');
        } else if (err.response?.status === 401) {
          // setErrMsg('Unauthorized');
        } else {
          // setErrMsg('Login Failed');
        }
      }

    }
    else {
      console.log("Please select an algorithm, pace and maze");
    }
  }
  const startVisualization = () => {
    // Start the visualization
  }

  useEffect(() => {
    if (visualizationResult !== null) {
      startVisualization();
    }
  }, [visualizationResult])
  return (
    <div className="home">
      <div className="home-header">
        <h1>Welcome Trailblazers</h1>
      </div>
      <div className="home-dropdown-container">
        <div className="dropdown-algo">
          <input type="checkbox" id="dropdown-algo" />
          <label className="dropdown-algo__face" for="dropdown-algo">
            <div className="dropdown-algo__text">{currentAlgo}</div>
            <div className="dropdown-algo__arrow"></div>
          </label>
          <ul className="dropdown-algo__items">
            <li onClick={() => handleCurrentOnClick("algo", "BFS")}>BFS</li>
            <li onClick={() => handleCurrentOnClick("algo", "DFS")}>DFS</li>
            <li onClick={() => handleCurrentOnClick("algo", "Dijkstra")}>Dijkstra</li>
            <li onClick={() => handleCurrentOnClick("algo", "A*")}>A*</li>
            <li onClick={() => handleCurrentOnClick("algo", "Dijkstra")}>Bell ...</li>
          </ul>
        </div>
        <div className="dropdown-pace">
          <input type="checkbox" id="dropdown-pace" />
          <label className="dropdown-pace__face" for="dropdown-pace">
            <div className="dropdown-pace__text">{currentPace}</div>
            <div className="dropdown-pace__arrow"></div>
          </label>
          <ul className="dropdown-pace__items">
            <li onClick={() => handleCurrentOnClick("pace", "x.5")}>x.5</li>
            <li onClick={() => handleCurrentOnClick("pace", "Actual")}>Actual</li>
            <li onClick={() => handleCurrentOnClick("pace", "x2")}>x2</li>
          </ul>
        </div>
        <div className="dropdown-maze">
          <input type="checkbox" id="dropdown-maze" />
          <label className="dropdown-maze__face" for="dropdown-maze">
            <div className="dropdown-maze__text">{currentMaze}</div>
            <div className="dropdown-maze__arrow"></div>
          </label>
          <ul className="dropdown-maze__items">
            <li onClick={() => handleCurrentOnClick("maze", "Custom Maze")}>Custom Maze</li>
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
          <div className="play-button" onClick={handlePlayClick}>
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
          <h2>Dijkstra</h2>
          <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Impedit, sed nihil consequuntur sequi nisi repudiandae. Blanditiis omnis ipsa perspiciatis est. Quis soluta fugit et possimus minus omnis quos, non sequi?</p>
        </div>
      </div>
    </div>
  );
};

export default Home;
