import React from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button'; 

const UserForm = ({ classes, state, submitForm, handleInputChange, text}) => {
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
            >{text}</Button>
       </form>
   </div>
    );
};

export default UserForm;