import React, { useRef, forwardRef } from 'react';
import { Link } from 'react-router-dom';
import { Popper } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import StarIcon from '@material-ui/icons/Star';

const useStyles = makeStyles((theme) => ({
    paper: {
        position:"relative",
        display: "flex",
        flexDirection: "column",
        margin: '.5rem 0',
        maxWidth: "260px",
        minWidth: "260px",
        boxSizing: "border-box",
        borderRadius: "6px",
        boxShadow: "rgba(51, 51, 51, 0.2) 1px 1px 4px 1px",
        backgroundColor: theme.palette.background.paper,
        overflow: "hidden",
        '& .display-img': {
            height: "180px",
            backgroundSize: "cover",
            width: "100%",
            backgroundImage: "url(https://photos.zillowstatic.com/fp/520acbe5b33769a5cab27b9b04744b28-cc_ft_576.jpg)",
        },
        '& .description': {
            boxSizing: "border-box",
            textAlign: "left",
            width: "100%",
            padding: ".5rem",
        },
        '& #price': {
            fontWeight: "600",
        },
        '& p': {
            margin: ".4rem",
        },
        '& .rating': {
            margin: ".4rem",
            fontSize: "1rem",
            display: "flex",
            alignItems: "center",
            '& *': {

            },
            '& .number-of-ratings': {
                color: "rgba(0, 0, 0, 0.38)",
            },
        },
    },
}));

const Marker = forwardRef((props, ref) => {
    const {
        color,
        name,
        id: markerId,
        handleMarkerClick,
        state,
    } = props;
    const markerRef = useRef();
    const open = Boolean(markerId in state);
    const id = open ? 'simple-popper' : undefined;


    const classes = useStyles();
    const linkStyles = {
        color: "inherit",
        display: "block",
        position: "absolute",
        zIndex: 9,
        width: "100%",
        height: "100%",
    };
    const svgIconStyles = {
        fontSize: "1rem",
        color: "#FF385C",
        marginRight: ".4rem",
    };

    return (
        <div className="marker"
            id={markerId}
            aria-label="test"
            style={{ backgroundColor: color, cursor: 'pointer' }}
            title={name}
            onClick={handleMarkerClick}
            ref={markerRef}
        >
            <span id="marker-label">$150</span>
            <Popper
                id={id}
                open={open}
                anchorEl={state[markerId]}
                onClick={(e) => e.stopPropagation()}
                disablePortal={false}
                modifiers={{
                    flip: {
                        enabled: true,
                    },
                    preventOverflow: {
                        enabled: true,
                        boundariesElement: 'scrollParent',
                    },
                }}
            >

                <div className={classes.paper}>
                    <Link to={`home/${markerId}`} style={linkStyles}> </Link>
                    <div className="display-img"></div>
                    <div className="description">
                        <div className="rating"><StarIcon style={svgIconStyles}/><span>4.99</span><span className="number-of-ratings">(143)</span></div>
                        <p>Entire House - Austin</p>
                        <p>Month-To-Month</p>
                        <p>Furnished</p>
                        <p><span id="price">$150</span> / night</p>
                    </div>
                </div>

            </Popper>
        </div>
    );
});

export default Marker;




// <div>
//     <div
//         className="pin bounce"
//         aria-label="Marker"
//         style={{ backgroundColor: color, cursor: 'pointer' }}
//         title={name}
//     />
//     <div className="pulse" />
// </div>