
function className(...classes) {
  return classes.filter(Boolean).join(" ");
}

const Tile = ({ tileState, onClick, onMouseEnter }) => {
    return (
      <div
        className={className(tileState == 1? "active": "", "tile")}
        onMouseDown={onClick}
        onMouseEnter={onMouseEnter}
      ></div>
    );
  };

export default Tile;