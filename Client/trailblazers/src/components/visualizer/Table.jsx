import { useState, useEffect, useContext,StateContext} from "react";
import { useStateProvider } from "../../context/StateContext";
import { reducerCases } from "../../context/constants";
import useMousePosition from "../../utils/useMousePosition";
import Node from "./Node";

const Table = () => {
    const [{table, tableState}, dispatch] = useStateProvider();
    const [nodes, setNodes] = useState([]);
    const mousePosition = useMousePosition();

    useEffect(() => {

        if(!tableState){
            setNodes(Array.from({ length: 48 }, () => Array(48).fill(0)));
            dispatch({type: reducerCases.UPDATE_TABLE_STATE, newState: true});
        }
            
    });
    useEffect(() => {
        dispatch({
            type: reducerCases.UPDATE_TABLE, 
            newTable: nodes
        });
    }, [nodes]);




    return (
        <div className='grid'>
            {table && table.map((row, rowIdx) => (
            <div key={rowIdx}>
                {row.map((node, columnIdx) => (
                <Node key={`${rowIdx}-${columnIdx}`} rowIdx={rowIdx} columnIdx={columnIdx} /> // Ensure each Node has a unique key prop
            ))}
        </div>
      ))}
      {JSON.stringify(mousePosition)}
    </div>
    );
}
export default Table;