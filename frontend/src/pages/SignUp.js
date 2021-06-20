import React from 'react';
import SignUp from '../components/SignUp';
import HomeLayout from '../components/Layout/HomeLayout';

const SignUpPage = ({ history }) => {
    return (
        <HomeLayout>
            <SignUp history={history}/>
        </HomeLayout>
    );
};

export default SignUpPage;