import React from 'react';
import { GOOGLE_MAPS_API_KEY } from '../../config';
import GoogleMapReact from 'google-map-react';
import Marker from './Marker';


const GoogleMap = () => {
    return (
        <div className="map-container">
            <GoogleMapReact
                bootstrapURLKeys={{ key: GOOGLE_MAPS_API_KEY }}
                defaultCenter={{ lat: 30.2672,
                    lng: -97.7431,
                }}
                defaultZoom={14}
            >

                <Marker
                    lat={30.2672}
                    lng={-97.7431}
                    text="My Marker"
                    color=""
                />
            </GoogleMapReact>
        </div>

    );
};

export default GoogleMap;