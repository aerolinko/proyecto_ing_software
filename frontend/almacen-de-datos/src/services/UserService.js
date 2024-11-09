import axios from 'axios';

const API_URL = 'http://localhost:4000/api/user'; // Cambia el puerto si es necesario

class UserService {
    getUsers() {
        return axios.get(API_URL);
    }

    getUserById(id) {
        return axios.get(`${API_URL}/${id}`);
    }

    getUserByUsername(username,password) {
            return axios.get(`${API_URL}/${password}/${username}`);
        }

    saveUser(user) {
        return axios.post(API_URL, user);
    }

    updateUser(id, user) {
        return axios.put(`${API_URL}/${id}`, user);
    }

    deleteUser(id) {
        return axios.delete(`${API_URL}/${id}`);
    }
}

export default new UserService();
