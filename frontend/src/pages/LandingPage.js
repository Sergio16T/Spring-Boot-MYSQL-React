import React from 'react';
import HomeLayout from '../components/HomeLayout';
import Map from './Map/Map';

const LandingPage = () => {
    return (
        <HomeLayout>
            <div className="page-container">
                <div id="info"></div>
                <Map/>
            </div>
        </HomeLayout>
    );
};

export default LandingPage;