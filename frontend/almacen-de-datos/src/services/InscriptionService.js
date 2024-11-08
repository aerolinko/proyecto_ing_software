import axios from 'axios';

const API_URL = 'http://localhost:4000/api/inscription'; // Cambia el puerto si es necesario

class InscriptionService {
    getInscriptions() {
        return axios.get(API_URL);
    }

    getInscriptionById(id) {
        return axios.get(`${API_URL}/${id}`);
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
}

export default new InscriptionService();
