
const Resources = () => {

    return (
        
        <div className="resources">
             <h2 className="resources-title">Resources</h2>
             <p className="resources-description">We have provided a list of resources that will be useful to do further research about each algorithm and their applications.</p>
            <ul className="resources-content">
                <li >
                    <a href="https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/" target="_blank" rel="noopener noreferrer">
                        Dijkstra's Algorithm
                    </a>
                </li>
                <li >
                    <a href="https://en.wikipedia.org/wiki/A*_search_algorithm" target="_blank" rel="noopener noreferrer">
                        A* Search Algorithm
                    </a>
                </li>
                <li>
                    <a href="https://en.wikipedia.org/wiki/Breadth-first_search" target="_blank" rel="noopener noreferrer">
                        Breadth-First Search
                    </a>
                </li>
                <li>
                    <a href="https://en.wikipedia.org/wiki/Depth-first_search" target="_blank" rel="noopener noreferrer">
                        Depth-First Search
                    </a>
                </li>
            </ul>
        </div>
    );
};

export default Resources;