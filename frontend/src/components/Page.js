import React, { useState } from 'react';
import PropTypes from 'prop-types';
import Appbar from './Appbar';
import Drawer from './Drawer';


const Page = ({ text, children }) => {
    const [state, setState] = useState({
      top: false,
      left: false,
      bottom: false,
      right: false,
    });

    const toggleDrawer = (anchor, open) => (event) => {
      if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
        return;
      }

      setState({ ...state, [anchor]: open });
    };
    return (
        <div id="page-layout">
            <Appbar state={state} text={text} toggleDrawer={toggleDrawer}/>
            <Drawer state={state} toggleDrawer={toggleDrawer}/>
            {children}
        </div>
    );
};

Page.propTypes = {
    text: PropTypes.string.isRequired,
    children: PropTypes.object.isRequired
};

export default Page;