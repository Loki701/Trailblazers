import { useState, useEffect, useContext, StateContext } from "react";
import Tile from "./Tile";

const Table = () => {
  const [tiles, setTiles] = useState(
    Array.from({ length: 48 * 48 }, () => false)
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
    const newTiles = [...tiles];
    newTiles[index] = !tiles[index]; // Change the color to black (you can modify this as needed)
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