import React from 'react';
import clsx from 'clsx';
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import InboxIcon from '@material-ui/icons/MoveToInbox';
import MailIcon from '@material-ui/icons/Mail';
import { Link } from 'react-router-dom';
import PersonAddIcon from '@material-ui/icons/PersonAdd';
import GroupIcon from '@material-ui/icons/Group';
import useStyles from './useStyles/DrawerStyles';

function ListItemLink(props) {
    const { icon, primary, to } = props;

    const renderLink = React.useMemo(() => React.forwardRef((itemProps, ref) => <Link to={to} ref={ref} {...itemProps} />),[to]);

    return (
        <li>
            <ListItem button component={renderLink}>
                {icon ? <ListItemIcon>{icon}</ListItemIcon> : null}
                <ListItemText primary={primary} />
            </ListItem>
        </li>
    );
  }

export default function SideDrawer({ isOpen, toggleDrawer }) {
    const classes = useStyles();

    const list = (anchor) => (
        <div
            className={clsx(classes.list, { [classes.fullList]: anchor === 'top' || anchor === 'bottom' })}
            role="presentation"
            onClick={toggleDrawer}
            onKeyDown={toggleDrawer}
        >
        <List>
                <ListItemLink to="/" primary="Users" icon={<GroupIcon/>} button/>
                <ListItemLink to="/adduser" primary="Add user" icon={<PersonAddIcon/>} button/>
        </List>
        <Divider />
        <List>
            {['All mail', 'Trash', 'Spam'].map((text, index) => (
            <ListItem button key={text}>
                <ListItemIcon>{index % 2 === 0 ? <InboxIcon /> : <MailIcon />}</ListItemIcon>
                <ListItemText primary={text} />
            </ListItem>
            ))}
        </List>
        </div>
    );

    return (
        <Drawer
          anchor='left'
          open={isOpen}
          onClose={toggleDrawer}
        >
            {list('left')}
        </Drawer>
    );
}
