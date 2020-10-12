import axios from 'axios'; 

const basePath = "http://localhost:8080/api/v1/users"
class UserService {
    getUsers() {
        return axios.get(basePath);
    }
    addUser(user) {
        return axios.post(basePath, user);
    }
    getUserById(id) {
        return axios.get(basePath + "/" + id);
    }
    updateUser(id, userDetails) {
        return axios.put(basePath + "/" + id, userDetails);
    }
    deleteUser(id) {
        return axios.delete(basePath + "/" + id);
    }
}

//export an instance of Object so that we can call methods on the object
export default new UserService();