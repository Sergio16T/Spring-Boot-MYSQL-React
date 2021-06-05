import { useEffect, useContext } from 'react';
import authService from '../../API/authService';
import { Context } from '../../App';

const useAuth = () => {
    const { state, dispatch } = useContext(Context);

    useEffect(() => {
        const getUser = async () => {
            try {
                const { data } = await authService.authenticate();
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
    }, [dispatch]);

    return {
        state,
        dispatch,
    };
};

export default useAuth;