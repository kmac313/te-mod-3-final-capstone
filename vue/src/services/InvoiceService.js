import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

const token = localStorage.getItem('token')

export default {
    sendOrder(order) {
        return http.post('/invoices', order)
      },

    getOrders() {
      
      return http.get('/invoices', {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      })
    },
    getOrderByInvoiceId(id) {
      return http.get(`/invoices/${id}`, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      })
    },
}