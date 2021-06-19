import React, { useState } from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import useStyles from './useStyles/UserFormStyles';
import accountService from '../API/accountService';
import Alert from '@material-ui/lab/Alert';

const SignIn = ({ history }) => {
    const [state, setState] = useState({
        email: "stapiafikes@gmail.com",
        password: "test123",
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
            const { data } = await accountService.signIn(state);
            console.log('result', data);
            document.cookie = `jwt=${data.jwt};max-age=${data.maxAge}; Secure;`;
            history.push('/users');
        } catch (err) {
            let { message } = err.response.data;
            setError(message);
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
                </fieldset>
                <Button
                    type="submit"
                    variant ="contained"
                    color="secondary"
                >
                    Sign In
                </Button>
            </form>
        </div>
    );
};

export default SignIn;