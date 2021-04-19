import React, { useState } from 'react';
import { Redirect } from 'react-router-dom';
import PropTypes from 'prop-types';
import Appbar from './Appbar';
import Drawer from './Drawer';
import useAuth from './hooks/useAuth';

const Page = ({ text, children }) => {
    const { state: { user, loadComplete, error } } = useAuth();
    const [isOpen, setIsOpen] = useState(false);


    const toggleDrawer = (event) => {
        if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
            return;
        }

        setIsOpen(!isOpen);
    };

    if (!loadComplete) return null;
    if (error) {
        return <Redirect to="/signin"/>;
    }
    return (
        <div id="page-layout">
            <Appbar
                text={text}
                toggleDrawer={toggleDrawer}
            />
            <Drawer
                isOpen={isOpen}
                toggleDrawer={toggleDrawer}
            />
            {children}
        </div>
    );
};

Page.propTypes = {
    text: PropTypes.string.isRequired,
    children: PropTypes.object.isRequired
};

export default Page;