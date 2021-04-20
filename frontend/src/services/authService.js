import axios from 'axios';

const basePath = "http://localhost:8080/api/v1";

export default {
    authenticate: () => {
        return axios.get(basePath + "/auth",  { withCredentials: true });
    },
    logout: () => {
        return axios.post(basePath + "/signout", { withCredentials: false });
    }
};
