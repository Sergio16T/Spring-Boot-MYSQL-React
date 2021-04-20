import React from 'react';
import Page from '../components/Page';
import ListUsers from '../components/ListUsers';


function Users({ history }) {
    return (
        <Page text="Users" history={history}>
            <ListUsers history={history}/>
        </Page>
    );
}

export default Users;