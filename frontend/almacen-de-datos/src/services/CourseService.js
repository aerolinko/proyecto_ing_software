import axios from 'axios';

const API_URL = 'http://localhost:4000/api/course'; // Cambia el puerto si es necesario

class CourseService {
    getCourses() {
        return axios.get(API_URL);
    }

    getCourseById(id) {
        return axios.get(`${API_URL}/${id}`);
    }

    saveCourse(course) {
        return axios.post(API_URL, course);
    }

    updateCourse(id, course) {
        return axios.put(`${API_URL}/${id}`, course);
    }

    deleteCourse(id) {
        return axios.delete(`${API_URL}/${id}`);
    }
}

export default new CourseService();
