import './node.css';
import { useStateProvider } from "../../context/StateContext";
import { useState } from 'react';

function className(...classes) {
  return classes.filter(Boolean).join(" ");
}

const Node = ({rowIdx, columnIdx}) => {

  const [{table}, dispatch] = useStateProvider();
  const [color, setColor] = useState('white');
  
  const handleClick = () => {
    let newTable = table;
    setColor(color == 'white'? "black": "white");
    newTable[rowIdx][columnIdx] = 1;
    dispatch({
      type: 'UPDATE_NODE', 
      table: {
        newTable
      }
      });
  };
  return <div className={className(color == "white"? "white": "black", "node")} onClick={handleClick}>

  </div>;
};

export default Node;
