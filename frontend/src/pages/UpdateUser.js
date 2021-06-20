import React from 'react';
import Page from '../components/Layout/PageLayout';
import UpdateUser from '../components/UpdateUser';

const UpdateUserPage = props => {
    return (
        <Page
            text="Updating user"
            history={props.history}
        >
            <UpdateUser {...props}/>
        </Page>
    );
};

export default UpdateUserPage;