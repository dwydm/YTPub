import { configureStore, applyMiddleware } from '@reduxjs/toolkit';
import tokenReducer from './token';
import userReducer from './loggedUser';
import thunk from 'redux-thunk';

export const store = configureStore({
  reducer: {
    token: tokenReducer,
    loggedUser: userReducer,
  },
  middleware: [...getDefaultMiddleware(), thunk],
})