import React, { useContext, useEffect } from 'react';
import { Redirect } from 'react-router-dom';
import PropTypes from 'prop-types';
import Appbar from './Appbar';
import useAuth from '../Hooks/useAuth';
import { Context } from '../../App';

const Page = ({ children, history, text }) => {
    const { state } = useAuth();
    const { dispatch, toggleDrawer } = useContext(Context);
    const {
        error,
        loadComplete,
        user,
    } = state;

    useEffect(() => {
        if (loadComplete && error) {
            dispatch({ type: "RESET_INITIAL_STATE" });
        }
    }, [dispatch, error, loadComplete]);

    if (!loadComplete) {
        return null;
    } else if (error) {
        return <Redirect to="/signin"/>;
    } else if (user) {
        return (
            <div id="page-layout">
                <Appbar
                    text={text}
                    toggleDrawer={toggleDrawer}
                    history={history}
                />
                {children}
            </div>
        );
    }
};

Page.propTypes = {
    children: PropTypes.object.isRequired,
    history: PropTypes.object.isRequired,
    text: PropTypes.string.isRequired,
};

export default Page;