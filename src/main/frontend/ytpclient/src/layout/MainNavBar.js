import React from 'react';
import { BrowserRouter, Routes, Route, useLocation } from "react-router-dom";
import GuestNavBar from './GuestNavBar';
import NavBar from './NavBar';
import { useSelector } from 'react-redux';

export default function MainNavBar() {
    const authenticated = useSelector((state) => state.loggedUser.user);
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
