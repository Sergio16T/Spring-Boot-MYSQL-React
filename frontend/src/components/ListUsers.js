import React, { Component } from 'react';
import userService from '../services/userService'; 
import Table from '@material-ui/core/Table'; 
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

class ListUsers extends Component {
    state = {
        users: []
    }

    componentDidMount() {
       this.getUsers();
    }

    getUsers = async () => {
        const { data } = await userService.getUsers(); 
        console.log(data);
        this.setState({
            users: data
        })
    }
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
                                <TableCell></TableCell>
                            </TableRow>
                            ): null}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        );
    }
}

export default ListUsers;