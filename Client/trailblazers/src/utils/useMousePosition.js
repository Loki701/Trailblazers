import React, { useState, useEffect } from 'react';

const useMousePosition = () => {
    const [isMouseDown, setIsMouseDown] = useState(false);
  const [
    mousePosition, 
    setMousePosition
  ] = useState({ x: 0, y: 0 });

  useEffect(() => {
    const handleMouseMove = (event) => {
      if (isMouseDown) {
        setMousePosition({ x: event.clientX, y: event.clientY });
      }
    };

    const handleMouseDown = () => {
      setIsMouseDown(true);
    };

    const handleMouseUp = () => {
      setIsMouseDown(false);
    };

    document.addEventListener('mousemove', handleMouseMove);
    document.addEventListener('mousedown', handleMouseDown);
    document.addEventListener('mouseup', handleMouseUp);

    return () => {
      document.removeEventListener('mousemove', handleMouseMove);
      document.removeEventListener('mousedown', handleMouseDown);
      document.removeEventListener('mouseup', handleMouseUp);
    };
  }, [isMouseDown]);

  return mousePosition;
};
export default useMousePosition;