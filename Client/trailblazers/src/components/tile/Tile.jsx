import "./Tile.css";

function className(...classes) {
  return classes.filter(Boolean).join(" ");
}

const Tile = ({ tileState, onClick, onMouseEnter }) => {
  return (
    <div
      className={className(tileState === 1 ? "active" : (tileState === 2 ? "start" : (tileState === 3? "end": "")), "tile")}
      onMouseDown={onClick}
      onMouseEnter={onMouseEnter}
    ></div>
  );
};

export default Tile;