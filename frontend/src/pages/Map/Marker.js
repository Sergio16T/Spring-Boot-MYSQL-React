import React from 'react';

const Marker = (props) => {
    const { color, name } = props;
    return (
        <div className="marker"
            aria-label="test"
            style={{ backgroundColor: color, cursor: 'pointer' }}
            title={name}
        >
            $150
        </div>
        // <div>
        //     <div
        //         className="pin bounce"
        //         aria-label="Marker"
        //         style={{ backgroundColor: color, cursor: 'pointer' }}
        //         title={name}
        //     />
        //     <div className="pulse" />
        // </div>
    );
};

export default Marker;
