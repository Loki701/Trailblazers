// import React, { useState, useEffect } from "react";
import Table from './visualizer/Table';

const Home = () => {
  function refreshPage(){ 
    window.location.reload(); 
}
  return (
    <div className='home'>
      <Table />
    </div>
  );
};

export default Home;