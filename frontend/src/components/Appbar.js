import React from 'react';
import PropTypes from 'prop-types';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import useStyles from './useStyles/AppBarStyles';
import UserMenu from './UserMenu';

const Appbar = ({ history, text, toggleDrawer }) => {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        onClick={toggleDrawer}
                        edge="start"
                        className={classes.menuButton}
                        color="inherit"
                        aria-label="menu"
                    >
                        <MenuIcon/>
                    </IconButton>
                    <Typography
                        variant="h6"
                        className={classes.title}
                    >
                        {text}
                    </Typography>
                    <UserMenu history={history}/>
                </Toolbar>
            </AppBar>
        </div>
    );
};

Appbar.propTypes = {
    text: PropTypes.string.isRequired,
};

export default Appbar;