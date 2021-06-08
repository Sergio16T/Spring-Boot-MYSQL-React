import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
    root: {
        // zIndex: 100,
        boxShadow: "rgba(51, 51, 51, 0.2) 1px 1px 4px 1px",

    },
    menuButton: {
        marginRight: theme.spacing(2),
    },
}));

export default useStyles;