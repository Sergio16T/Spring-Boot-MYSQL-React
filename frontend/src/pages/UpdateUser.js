import React from 'react';
import Page from '../components/Page'; 
import UpdateUser from '../components/UpdateUser'; 

const UpdateUserPage = props => {
    return (
        <Page text="Updating user">
            <UpdateUser {...props}/>
        </Page>
    );
};

export default UpdateUserPage;