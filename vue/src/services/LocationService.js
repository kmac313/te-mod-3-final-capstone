import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

const token = localStorage.getItem('token')

export default {

    validateAddress(address) {
      return http.post(`/validate`, 
        address
      ,
      {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      })
    }
    
}