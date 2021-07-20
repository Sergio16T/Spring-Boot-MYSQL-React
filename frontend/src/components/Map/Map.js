import React, { useState, useRef } from 'react';
import { GOOGLE_MAPS_API_KEY } from '../../config';
import GoogleMapReact from 'google-map-react';
import Marker from './Marker';


const GoogleMap = () => {
    const mapRef = useRef(null);
    const [state, setState] = useState({});

    const handleMarkerClick = (event) => {
        event.stopPropagation();
        const { id } = event.currentTarget;

        if (id in state) {
            setState({});
        } else {
            setState({
                [id]: event.currentTarget,
            });
        }
    };

    const handleMapClick = ({ event }) => {
        setState({});
    };

    const handleApiLoaded = (map, maps) => {
        console.log(map);
        console.log(maps);
        mapRef.current = map;
    }

    return (
        <div className="map-container">
            <GoogleMapReact
                bootstrapURLKeys={{ key: GOOGLE_MAPS_API_KEY }}
                defaultCenter={{ lat: 30.2672,
                    lng: -97.7431,
                }}
                defaultZoom={14}
                yesIWantToUseGoogleMapApiInternals
                onGoogleApiLoaded={({ map, maps }) => handleApiLoaded(map, maps)}
                // onChildMouseEnter={onChildMouseEnter}
                // onChildMouseLeave={onChildMouseLeave}
                onClick={handleMapClick}
            >

                <Marker
                    id={1}
                    handleMarkerClick={handleMarkerClick}
                    state={state}
                    lat={30.2672}
                    lng={-97.7431}
                    text="My Marker"
                />
                <Marker
                    id={2}
                    handleMarkerClick={handleMarkerClick}
                    state={state}
                    lat={30.2672}
                    lng={-97.7631}
                    text="My Marker"
                />
            </GoogleMapReact>
        </div>
    );
};

export default GoogleMap;


// const onChildMouseEnter = (key /*, childProps */) => {
//     console.log('key', key);
//     // this.props.onHoverKeyChange(key);
// }

// const onChildMouseLeave = (key/* key, childProps */) => {
//     // console.log('leave', key);
//     // this.props.onHoverKeyChange(null);
// }