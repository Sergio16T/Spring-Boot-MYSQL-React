import { useReducer, useEffect } from 'react';
import accountService from '../../services/accountService';

const initialState = {
    user: null,
    loadComplete: false,
    error: null,
};

const reducer = (state, action) => {
    switch(action.type) {
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
        default:
            throw new Error(`Unrecognized action: ${action.type}`);
    }
}

const useAuth = () => {
    const [state, dispatch] = useReducer(reducer, initialState);

    useEffect(() => {
        const getUser = async () => {
            try {
                const { data } = await accountService.authenticate();
                console.log(data);
                dispatch({
                    type: "SUCCESSFUL_AUTH",
                    data,
                });
            } catch (err) {
                console.log(err.response);
                dispatch({
                    type: "FAILED_AUTH",
                    error: err.response,
                });
            }
        }
        getUser();
    }, []);

    return {
        state,
        dispatch,
    };
};

export default useAuth;