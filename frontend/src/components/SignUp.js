import React, { useState } from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import useStyles from './useStyles/UserFormStyles';
import accountService from '../API/accountService';
import Alert from '@material-ui/lab/Alert';

const SignUp = ({ history }) => {
    const [state, setState] = useState({
        firstName: "ang",
        lastName: "air",
        email: "ang@gmail.com",
        password: "test",
        confirmPassword: "test",
    });
    const [error, setError] = useState();

    const classes = useStyles();

    const handleInputChange  = (e) => {
        const { name, value } = e.target;
        setState({
            ...state,
            [name]: value,
        });
    }
    const submitForm = async (e) => {
        e.preventDefault();
        try {
            const { data } = await accountService.signUp(state);
            console.log('result', data)
            document.cookie = `jwt=${data.jwt};max-age=${data.maxAge}; Secure;`;
            history.push('/users');
        } catch(err) {
            if (err.response) {
                console.log(err.response);
                let { message } = err.response.data;
                setError(message);
            } else {
                setError(err.message);
            }
        }
    }
    return (
        <div className={classes.formContainer}>
            {error &&
                <Alert severity="error" variant="filled" className={classes.errorMessage}>{error}</Alert>
            }
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
                    color="secondary"
                >
                    Signup
                </Button>
            </form>
        </div>
    );
};

export default SignUp;