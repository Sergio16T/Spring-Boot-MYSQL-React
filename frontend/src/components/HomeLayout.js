import React from 'react';
import NavBar from './NavBar';

const HomeLayout = ({ children }) => {
    return (
        <div className="home-layout">
            <NavBar/>
            {children}
        </div>
    );
};

export default HomeLayout;