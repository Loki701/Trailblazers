import { useState, useEffect, useContext, StateContext } from "react";
import Tile from "../tile/Tile";
import "./Table.css";

const Table = () => {
  const [tiles, setTiles] = useState(
    Array.from({ length: 30 * 20 }, (_, index) => {
      if(index === 0){
        return 2;
      } else if (index === 30*20-1){
        return 3;
      }else{
        return 0;
      }
    })
  );
  const [isMouseDown, setIsMouseDown] = useState(false);

  const handleMouseDown = (index) => {
    setIsMouseDown(true);
    handleTileClick(index);
  };

  const handleMouseEnter = (index) => {
    if (isMouseDown) {
      handleTileClick(index);
    }
  };

  const handleMouseUp = () => {
    setIsMouseDown(false);
  };

  const handleTileClick = (index) => {
    if (tiles[index] === 2 || tiles[index] === 3) {
      return;
    }
    const newTiles = [...tiles];
    if (newTiles[index] === 1) {
      newTiles[index] = 0; // Change the color to white (you can modify this as needed)
    } else {
      newTiles[index] = 1; // Change the color to black (you can modify this as needed)
    }
    setTiles(newTiles);
  };

  return (
    <div className="table" onMouseUp={handleMouseUp}>
      <div className="grid">
        {tiles.map((state, index) => (
          <Tile
            key={index}
            tileState={state}
            onClick={() => handleMouseDown(index)}
            onMouseEnter={() => handleMouseEnter(index)}
          />
        ))}
      </div>
    </div>
  );
};
export default Table;