import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {
    getProducts() {
        return http.get('/menu')
    }
}