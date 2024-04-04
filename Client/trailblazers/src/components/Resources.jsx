import React, { useEffect, useState } from 'react';

const Resources = () => {
    const [loaded, setLoaded] = useState(false);

    useEffect(() => {
        setLoaded(true);
    }, []);
    
    const styles = {
        resourcesContainer: {
            backgroundColor: '#020b1d',
            padding: '20px',
            borderRadius: '5px',
            width: '100%',
            maxWidth: '600px',
            margin: 'auto',
            boxShadow: '0px 0px 10px rgba(0, 0, 0, 0.1)',
            border: '2px solid #00ff00',
            opacity: loaded ? 1 : 0,
            transition: 'opacity 1s ease-in-out',
            animationName: 'fadeIn',
            animationDuration: '2s'
        },
        resourcesTitle: {
            textAlign: 'center',
            marginBottom: '20px',
            color: '#00ff00',
            fontSize: '2em',
            fontFamily: 'Orbitron, sans-serif'
        },
        resourcesList: {
            listStyleType: 'none',
            padding: '0'
        },
        resourcesListItem: {
            marginBottom: '10px'
        },
        resourcesLink: {
            textDecoration: 'none',
            color: '#00ff00',
            fontSize: '1.2em',
            fontFamily: 'Orbitron, sans-serif'
        },
        explanation: {
            color: '#00ff00',
            fontSize: '1.2em',
            marginBottom: '20px',
            fontFamily: 'Orbitron, sans-serif'
        }
    };

    return (
        
        <div style={styles.resourcesContainer}>
             <h2 style={styles.resourcesTitle}>Resources</h2>
             <p style={styles.explanation}>This is the Resources page. Here you can find useful links to learn more about the algorithms used in this application.</p>
            <ul style={styles.resourcesList}>
                <li style={styles.resourcesListItem}>
                    <a style={styles.resourcesLink} href="https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/" target="_blank" rel="noopener noreferrer">
                        Dijkstra's Algorithm
                    </a>
                </li>
                <li style={styles.resourcesListItem}>
                    <a style={styles.resourcesLink} href="https://en.wikipedia.org/wiki/A*_search_algorithm" target="_blank" rel="noopener noreferrer">
                        A* Search Algorithm
                    </a>
                </li>
                <li style={styles.resourcesListItem}>
                    <a style={styles.resourcesLink} href="https://en.wikipedia.org/wiki/Breadth-first_search" target="_blank" rel="noopener noreferrer">
                        Breadth-First Search
                    </a>
                </li>
                <li style={styles.resourcesListItem}>
                    <a style={styles.resourcesLink} href="https://en.wikipedia.org/wiki/Depth-first_search" target="_blank" rel="noopener noreferrer">
                        Depth-First Search
                    </a>
                </li>
            </ul>
        </div>
    );
};

export default Resources;