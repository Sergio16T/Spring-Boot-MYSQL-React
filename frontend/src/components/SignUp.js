import React, { useState } from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import useStyles from './useStyles/UserFormStyles';
import accountService from '../services/accountService';

const SignUp = ({ history }) => {
    const [state, setState] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        confirmPassword: "",
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
        const { data } = await accountService.signUp(state);
        console.log('result', data)
        document.cookie = `jwt=${data.jwt};max-age=${data.maxAge}; Secure;`;
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
                    label="First name"
                    name="firstName"
                    value={state.firstName}
                    onChange={handleInputChange}
                />
                <TextField
                    label="Last name"
                    name="lastName"
                    value={state.lastName}
                    onChange={handleInputChange}
                />
                <TextField
                    label="Email"
                    name="email"
                    value={state.email}
                    onChange={handleInputChange}
                />
                <TextField
                    type="password"
                    label="Password"
                    name="password"
                    value={state.password}
                    onChange={handleInputChange}
                />
                <TextField
                    type="password"
                    label="Confirm Password"
                    name="confirmPassword"
                    value={state.confirmPassword}
                    onChange={handleInputChange}
                />
                </fieldset>
                <Button
                    type="submit"
                    variant ="contained"
                    color="primary"
                >
                    Signup
                </Button>
        </form>
    </div>
    );
};

export default SignUp;