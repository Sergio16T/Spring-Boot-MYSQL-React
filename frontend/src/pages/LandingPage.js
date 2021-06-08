import React from 'react';
import HomeLayout from '../components/HomeLayout';
import Map from './Map/Map';

const LandingPage = () => {
    return (
        <HomeLayout>
            {/* <div className="landing-page"> */}
            <Map/>
            {/* </div> */}
        </HomeLayout>
    );
};

export default LandingPage;