import React, { useContext } from 'react';
import { Redirect } from 'react-router-dom';
import PropTypes from 'prop-types';
import Appbar from './Appbar';
import useAuth from './hooks/useAuth';
import { Context } from '../App';

const Page = ({ text, children, history }) => {
    const { state: { loadComplete, error } } = useAuth();
    const { toggleDrawer } = useContext(Context);

    if (!loadComplete) return null;
    if (error) {
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
    text: PropTypes.string.isRequired,
    children: PropTypes.object.isRequired,
};

export default Page;