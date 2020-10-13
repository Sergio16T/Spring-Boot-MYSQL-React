import React, { Component, useState, Fragment } from 'react';
import userService from '../services/userService'; 
import Table from '@material-ui/core/Table'; 
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';

class ListUsers extends Component {
    state = {
        users: []
    }

    componentDidMount() {
       this.getUsers();
    }

    getUsers = async () => {
        const { data } = await userService.getUsers(); 
        // console.log(data);
        this.setState({
            users: data
        })
    }
    deleteUser = async (id) => {
        await userService.deleteUser(id); 
        const users = this.state.users.filter(user => user.id !== id); 
        this.setState({ users });
    }

    handleUpdateRedirect = (path) => {
        this.props.history.push(path); 
    }; 
    render() {
        const { users } = this.state; 
        return (
            <div className="tableWrapper">
                <TableContainer component={Paper}>
                    <Table aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell>First Name</TableCell>
                                <TableCell>Last Name</TableCell>
                                <TableCell>Email</TableCell>
                                <TableCell>Action</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {users.length ?  users.map((user, index) => 
                            <TableRow key={user.id}>
                                <TableCell>{user.firstName}</TableCell>
                                <TableCell>{user.lastName}</TableCell>
                                <TableCell>{user.email}</TableCell>
                                <TableCell>
                                    <ActionMenu>
                                            <MenuItem
                                            onClick={() => this.handleUpdateRedirect(`/update-user/${user.id}`)}
                                            key='item1'
                                            >
                                                Update User
                                            </MenuItem>
                                            <MenuItem
                                            onClick={() => this.deleteUser(user.id)}
                                            key='item2'
                                            >
                                                Delete User
                                            </MenuItem>
                                    </ActionMenu>
                                </TableCell>
                            </TableRow>
                            ): null}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        );
    }
}

const ActionMenu = ({ children })=> {
    const [anchorEl, setAnchorEl] = useState(null);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null); 
    }
    return (
        <Fragment>
            <Button 
            aria-controls="simple-menu" 
            aria-haspopup="true" 
            onClick={handleClick}
            variant="outlined"
            >
            Action
            </Button>
            <Menu
            id="simple-menu"
            anchorEl={anchorEl}
            keepMounted
            open={Boolean(anchorEl)}
            onClose={handleClose}
            >
              {children}
            </Menu>
        </Fragment>
    )
}

export default ListUsers;