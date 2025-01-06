import axios from 'axios';

const API_URL = 'http://localhost:4000/api/inscription'; // Cambia el puerto si es necesario

class InscriptionService {
    getInscriptions() {
        return axios.get(API_URL);
    }

    getInscriptionById(id) {
        return axios.get(`${API_URL}/${id}`);
    }

    getInscriptionByUserId(id) {
            return axios.get(`${API_URL}/owner/${id}`);
    }

    saveInscription(inscription) {
        return axios.post(API_URL, inscription);
    }

    updateInscription(id, inscription) {
        return axios.put(`${API_URL}/${id}`, inscription);
    }

    deleteInscription(id) {
        return axios.delete(`${API_URL}/${id}`);
    }

    deleteAllInscriptions(id) {
        return axios.delete(`${API_URL}/all/${id}`);
    }

}

export default new InscriptionService();
