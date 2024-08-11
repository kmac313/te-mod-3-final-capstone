import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});
const token = localStorage.getItem('token')

export default {
  getProducts() {
    return http.post('/menu', 
      {
        categories: []
      },
      {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      }
    );
  }
}