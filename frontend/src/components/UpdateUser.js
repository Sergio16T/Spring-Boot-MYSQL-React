import React, { useState, useEffect } from 'react';
import userService from '../services/userService';
import UserForm from './UserForm'; 
import useStyles from './useStyles/UpdateUserStyles';
  
const UpdateUser = ({ history, match }) => {
    const [state, setState] = useState({
        firstName: "",
        lastName: "",
        email: ""
    });
    const classes = useStyles();

    useEffect(() => {
        const getUserData = async () => {
            const { data } = await userService.getUserById(match.params.id); 
            setState(data);
        }
        getUserData();
    }, [match.params.id]); 

    const handleInputChange  = (e) => {
        const { name , value } = e.target; 
        setState({
            ...state, 
            [name]: value
        });
    }
    const submitForm = async (e) => {
        e.preventDefault();
        await userService.updateUser(match.params.id, state); 
        history.push('/');
    }
    return (
        <UserForm
        classes={classes}
        state={state}
        handleInputChange={handleInputChange}
        submitForm={submitForm}
        text="Save Updates"
        />
    );
};

export default UpdateUser;