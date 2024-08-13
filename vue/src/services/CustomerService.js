import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

const token = localStorage.getItem('token')

export default {
    getCustomer(id) {
        console.log(id)
        return http.get(`/customer/${id}`,
        {
            headers: {
              'Authorization': `Bearer ${token}`
            }
          })
    },
    addCustomer(customer) {
        return http.post('/customer', 
        customer, 
        {
          headers: {
          'Authorization': `Bearer ${token}`
        }
      })
    }
}