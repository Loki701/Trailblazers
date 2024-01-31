import { reducerCases } from "./constants";

export const initialState = {
    table: [],
    tableState: false
};

const reducer = (state, action) => {
    switch (action.type) {
        case reducerCases.UPDATE_TABLE:
            return {
                ...state,
                table: action.newTable,
            };
        case reducerCases.UPDATE_TABLE_STATE:
            return {
                ...state,
                tableState: action.newState,
            };
        default:
            return state;
    }
};

export default reducer;