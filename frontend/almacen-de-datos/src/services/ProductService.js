import axios from 'axios';

const API_URL = 'http://localhost:4000/api/product'; // Cambia el puerto si es necesario

class ProductService {
    getProducts() {
        return axios.get(API_URL);
    }

    getProductById(id) {
        return axios.get(`${API_URL}/${id}`);
    }

     getProductByAuthorId(author_id) {
            return axios.get(`${API_URL}/author/${author_id}`);
     }

    saveProduct(product) {
        return axios.post(API_URL, product);
    }

    updateProduct(id, product) {
        return axios.put(`${API_URL}/${id}`, product);
    }

    deleteProduct(id) {
        return axios.delete(`${API_URL}/${id}`);
    }
}

export default new ProductService();
