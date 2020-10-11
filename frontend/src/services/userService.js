import axios from 'axios'; 


class UserService {
    getUsers() {
        return axios.get("http://localhost:8080/api/v1/users");
    }
    addUser(user) {
        return axios.post("http://localhost:8080/api/v1/users", user);
    }
}

//export an instance of Object so that we can call methods on the object
export default new UserService();