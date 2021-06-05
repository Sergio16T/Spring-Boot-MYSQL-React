import axios from 'axios';

const basePath = "http://localhost:8080/api/v1/account";

export default {
    signUp: (data) => {
        return axios.post(basePath + "/signup", data);
    },

    signIn: (data) => {
        return axios.post(basePath + "/signin", data, { withCredentials: true });
    },

    signOut: () => {
        return axios.post(basePath + "/signout", { withCredentials: false });
    },

    getAccountList: () => {
        return axios.get(basePath, { withCredentials: true });
    },

    addAccount: (account) => {
        return axios.post(basePath, account);
    },

    getAccountById: (id) => {
        return axios.get(basePath + "/" + id, { withCredentials: true });
    },

    updateAccount: (id, accountDetails) => {
        return axios.put(basePath + "/" + id, accountDetails, { withCredentials: true });
    },

    deleteAccount: (id) => {
        return axios.delete(basePath + "/" + id, { withCredentials: true });
    },
};
