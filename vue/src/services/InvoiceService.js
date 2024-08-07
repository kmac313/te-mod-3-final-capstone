import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {
    sendOrder(order) {
        return http.post('/invoices', order)
      },
}