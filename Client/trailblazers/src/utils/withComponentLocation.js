import React, { useRef, useEffect, useState } from 'react';

const withComponentLocation = (WrappedComponent) => {
  return (props) => {
    const componentRef = useRef(null);
    const [componentPosition, setComponentPosition] = useState({ top: 0, left: 0 });

    useEffect(() => {
      const updateComponentPosition = () => {
        const rect = componentRef.current.getBoundingClientRect();
        setComponentPosition({
          top: rect.top + window.scrollY,
          left: rect.left + window.scrollX,
        });
      };

      // Initial position
      updateComponentPosition();

      // Update position on resize (optional)
      window.addEventListener('resize', updateComponentPosition);

      return () => {
        window.removeEventListener('resize', updateComponentPosition);
      };
    }, []); // Empty dependency array ensures the effect runs only once on mount

    return (
      <div ref={componentRef} className='wrap'>
        <WrappedComponent {...props} componentPosition={componentPosition} />
      </div>
    );
  };
};

export default withComponentLocation;