import React, { Fragment } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import IconButton from '@material-ui/core/IconButton';
import { Link } from 'react-router-dom';
import AccountCircle from '@material-ui/icons/AccountCircle';
import MenuItem from '@material-ui/core/MenuItem';
import Menu from '@material-ui/core/Menu';
import authService from '../services/authService';

const useStyles = makeStyles((theme) => ({
    root: {
        marginLeft: 'auto'
    },
    menuButton: {
      marginRight: theme.spacing(2),
    },
    title: {
      flexGrow: 1,
    },
    secondaryText: {
      color:"#6682A0",
      '&:hover': {
        color: "#6682A0",
      }
    }
  }));

const UserMenu = ({ history }) => {
    const classes = useStyles();
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);

    const handleMenu = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const logOut = async () => {
        try {
            await authService.logout();
            document.cookie = "jwt=; expires=0;"
            history.push("/signin");
        }  catch (err) {
            console.log(err);
        }
    }

	const renderLink = React.forwardRef((itemProps, ref) => <Link to="/settings" ref={ref} {...itemProps}/>);
    return (
        <Fragment>
            <IconButton
                aria-label="User Account Button"
                aria-controls="menu-appbar"
                aria-haspopup="true"
                onClick={handleMenu}
                color="inherit"
                className={classes.root}
            >
                <AccountCircle />
            </IconButton>
            <Menu
                id="menu-appbar"
                anchorEl={anchorEl}
                anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
                keepMounted
                transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
                open={open}
                onClose={handleClose}
            >
                <MenuItem onClick={handleClose} className={classes.secondaryText} component={renderLink} >Account Settings</MenuItem>
                <MenuItem onClick={logOut} className={classes.secondaryText}>Log out</MenuItem>
            </Menu>
        </Fragment>
    );
}
export default UserMenu;