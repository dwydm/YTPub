import { createStore } from 'redux';

const initialState = {
  token: null,
  loggedUser: null
};
const reducer = (state = initialState, action) => {
  switch (action.type) {
    case 'SET_TOKEN':
      return {
        ...state,
        token: action.payload
      };
      case 'SET_USER':
      return {
        ...state,
        loggedUser: action.payload
      };
    default:
      return state;
  }
};
const store = createStore(reducer);
export default store;