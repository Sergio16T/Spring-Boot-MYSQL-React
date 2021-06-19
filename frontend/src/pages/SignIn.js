import React from 'react';
import SignIn from '../components/SignIn';
import HomeLayout from '../components/HomeLayout';

const SignInPage = ({ history }) => {
    return (
        <HomeLayout>
            <SignIn history={history}/>
        </HomeLayout>
    );
};

export default SignInPage;