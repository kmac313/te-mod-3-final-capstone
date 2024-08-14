import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

let token = localStorage.getItem('token')

export default {
    sendOrder(order) {
      console.log(token)
        return http.post('/invoice', 
          order,
          {
          headers: {
            'Authorization': `Bearer ${token}`
          }
    })
      },

    getOrders(userToken) {
      
      return http.get('/invoice', {
        headers: {
          'Authorization': `Bearer ${userToken ? userToken : token}`
        }
      })
    },
    getOrderByInvoiceId(id) {
      return http.get(`/invoice/${id}`, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      })
    },
    updateOrder(id, invoice) {
      console.log(id)
      return http.put(`/invoice/${id}`, 
        invoice, 
        {
          headers: {
          'Authorization': `Bearer ${token}`
        }
      })
    }
}