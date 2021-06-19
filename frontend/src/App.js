import React, { useReducer } from 'react';
import './styles/index.scss';
import './App.scss';
import {
    createMuiTheme,
    ThemeProvider as MuiThemeProvider,
} from '@material-ui/core/styles';
import {
    BrowserRouter as Router,
    Switch,
    Route,
} from "react-router-dom";
import Users from './pages/Users';
import AddUser from './pages/AddUser';
import UpdateUser from './pages/UpdateUser';
import SignUp from './pages/SignUp';
import SignIn from './pages/SignIn';
import Drawer from './components/Drawer';
import LandingPage from './pages/LandingPage';
import Settings from './pages/Settings';

const muiTheme = createMuiTheme({
    palette: {
        primary: {
            // main: "#222222",
            main:  "#FFFFFF",
        },
        secondary: {
            main: "#FF385C",
        },
    },
});

const initialState = {
    user: null,
    loadComplete: false,
    error: null,
    isOpen: false,
};

const reducer = (state, action) => {
    switch (action.type) {
        case "SUCCESSFUL_AUTH": {
            return {
                user: action.data,
                loadComplete: true,
                error: null,
            }
        }
        case "FAILED_AUTH": {
            return {
                ...state,
                error: action.error,
                loadComplete: true,
            }
        }
        case 'RESET_INITIAL_STATE': {
            return initialState;
        }
        case "TOGGLE_DRAWER": {
            return {
                ...state,
                isOpen: !state.isOpen,
            }
        }
        default:
            throw new Error(`Unrecognized action: ${action.type}`);
    }
}


const Context = React.createContext();

function App() {
    const [state, dispatch] = useReducer(reducer, initialState);

    const toggleDrawer = (event) => {
        if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
            return;
        }

        dispatch({ type: "TOGGLE_DRAWER" });
    };

    const context = {
        state,
        dispatch,
        toggleDrawer,
    };

    return (
        <Router>
            <MuiThemeProvider theme={muiTheme}>
                <Context.Provider value={context}>
                    <div className="App">
                        {state.user &&
                            <Drawer
                                isOpen={state.isOpen}
                                toggleDrawer={toggleDrawer}
                            />
                        }
                        <Switch>
                            <Route exact path="/"  component={LandingPage}/>
                            <Route exact path ="/signup" component={SignUp}/>
                            <Route exact path ="/signin" component={SignIn}/>
                            <Route exact path="/users" component={Users}/>
                            <Route path="/adduser" component={AddUser}/>
                            <Route path="/update-user/:id" component={UpdateUser}/>
                            <Route exact path="/settings" component={Settings}/>
                        </Switch>
                    </div>
                </Context.Provider>
            </MuiThemeProvider>
        </Router>
    );
}

export default App;
export { Context };