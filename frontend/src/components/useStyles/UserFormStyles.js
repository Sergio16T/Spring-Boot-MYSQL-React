import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            display: "flex",
            flexDirection: "column",
            margin: theme.spacing(2),
        },
        padding: "1rem 0",
        margin: "0",
    },
    formContainer: {
        padding: '2rem 1rem',
        transform: 'translate(-50%, -50%)',
        top: '50%',
        left: '50%',
        position: 'relative',
    },
    form: {
        background: 'white',
        maxWidth: "50ch",
        margin: "0 auto",
        padding: "0rem 2rem 2rem 2rem",
        border: "1px solid rgb(217, 228, 236)",
        borderRadius: "4px",
        boxShadow: "rgba(51, 51, 51, 0.2) 1px 1px 4px 1px",
        '& > button' : {
            width: "calc(100% - 32px)",
            margin: "0 auto",
            padding: ".6rem",
        },
    },
    errorMessage: {
        margin: "2rem auto",
        maxWidth: "50ch",
    },
}));

export default useStyles;