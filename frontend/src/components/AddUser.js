import React, { useState } from 'react';
import userService from '../services/userService';
import UserForm from './UserForm'; 
import useStyles from './useStyles/UserFormStyles'; 

const AddUser = ({ history }) => {
    const [state, setState] = useState({
        firstName: "",
        lastName: "",
        email: ""
    });
    const classes = useStyles();

    const handleInputChange  = (e) => {
        const { name , value } = e.target; 
        setState({
            ...state, 
            [name]: value
        });
    }
    const submitForm = async (e) => {
        e.preventDefault();
        const result = await userService.addUser(state); 
        console.log(result)
        history.push('/');
    }
    return (
        <UserForm
        classes={classes}
        state={state}
        handleInputChange={handleInputChange}
        submitForm={submitForm}
        text="Add User"
        />
    );
};


export default AddUser;