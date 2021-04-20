import axios from 'axios';

const basePath = "http://localhost:8080/api/v1/account";

export default {
    signUp: (data) => {
        return axios.post(basePath + "/signup", data);
    },
    signIn: (data) => {
        return axios.post(basePath + "/signin", data, { withCredentials: true });
    },
};
