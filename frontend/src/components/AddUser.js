import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button'; 
import userService from '../services/userService';

const useStyles = makeStyles((theme) => ({
    root: {
      '& > *': {
        display: "flex", 
        flexDirection: "column",
        margin: theme.spacing(2),
      },
      padding: "1rem 0",
      margin: "0"
    },
    formContainer: {
        padding: '2rem 1rem',
    }, 
    form: {
        width: "50ch",
        margin: "0 auto",
        padding: "0rem 2rem 2rem 2rem", 
        border: "1px solid rgb(217, 228, 236)",
        borderRadius: "4px",
        boxShadow: "rgba(51, 51, 51, 0.2) 1px 1px 4px 1px",
        '& > button' : {
            width: "calc(100% - 32px)",
            margin: "0 auto",
            padding: ".6rem"
        }
    }
  }));

  
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
        <div className={classes.formContainer}>
             <form 
             className={classes.form} 
             noValidate autoComplete="off"
             onSubmit={submitForm}
             >
                <fieldset className={classes.root}>
                    <TextField 
                    id="standard-basic" 
                    label="First name"
                    name="firstName"
                    value={state.firstName}
                    onChange={handleInputChange}
                    />
                    <TextField 
                    id="filled-basic" 
                    label="Last name"
                    name="lastName"
                    value={state.lastName}
                    onChange={handleInputChange}
                    />
                    <TextField 
                    id="outlined-basic" 
                    label="Email"
                    name="email"
                    value={state.email}
                    onChange={handleInputChange}
                    />
                </fieldset>
                <Button 
                type="submit"
                variant ="contained" 
                color="primary"
                >Save User</Button>
            </form>
        </div>
    );
};

AddUser.propTypes = {
    
};

export default AddUser;