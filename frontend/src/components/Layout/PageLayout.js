import React, { useContext, useEffect } from 'react';
import { Redirect } from 'react-router-dom';
import PropTypes from 'prop-types';
import Appbar from './Appbar';
import useAuth from '../hooks/useAuth';
import { Context } from '../../App';

const Page = ({ children, history, text }) => {
    const user = useAuth();
    const { state, dispatch, toggleDrawer } = useContext(Context);
    const {
        error,
        loadComplete,
    } = state;

    useEffect(() => {
        if (loadComplete && error) {
            dispatch({ type: "RESET_INITIAL_STATE" });
        }
    }, [dispatch, error, loadComplete]);

    if (!loadComplete) {
        return null;
    }
    if (error || !user) {
        return <Redirect to="/signin"/>;
    }
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
};

Page.propTypes = {
    children: PropTypes.object.isRequired,
    history: PropTypes.object.isRequired,
    text: PropTypes.string.isRequired,
};

export default Page;