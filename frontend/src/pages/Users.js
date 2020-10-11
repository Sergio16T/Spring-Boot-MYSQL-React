import React from 'react';
import Page from '../components/Page';
import ListUsers from '../components/ListUsers'; 


function Users(props) {
    return (
        <Page text="Users">
            <ListUsers/>
        </Page>
    );
}

export default Users;