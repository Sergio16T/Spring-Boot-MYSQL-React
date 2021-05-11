import React from 'react';
import Page from '../components/Page';
import AddUser from '../components/AddUser';

const AddUserPage = ({ history }) => {
    return (
        <Page
            text="Add a user"
            history={history}
        >
            <AddUser history={history}/>
        </Page>
    );
};


export default AddUserPage;