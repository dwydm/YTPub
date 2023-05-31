import React from 'react'
import store from '../store';
import { useLocation } from "react-router-dom";
import GuestNavBar from './GuestNavBar'
import NavBar from './NavBar';

export default function MainNavBar() {
    const authenticated = store.getState().loggedUser;
    const currentPath = useLocation().pathname;

    let navBar;
    if(!authenticated){
        navBar = <GuestNavBar />;
    } else {
        navBar = <NavBar />
    }
  
    return (
    <nav>
        {navBar}
    </nav>
  )
}
