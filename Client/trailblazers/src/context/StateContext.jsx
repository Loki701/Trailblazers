import React, { createContext, useContext, useReducer } from "react";
//import { StateProvider } from './StateContext';

export const StateContext = createContext();

export const StateProvider = ({ initialState, reducer, children }) => {
  return (
    <StateContext.Provider value={useReducer(reducer, initialState)}>
      {children}
    </StateContext.Provider>
  );
};

export const useStateProvider = () => useContext(StateContext);
