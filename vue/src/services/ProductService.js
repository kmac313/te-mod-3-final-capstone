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
  },

  getAllToppings() {
    return http.post('/menu', {
      categories: ["Regular Topping", "Premium Topping"]
    },
    {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })
  },

  getAllDrinks() {
    return http.post('/menu', {
      categories: ["Drink"]
    },
    {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })
  },

  getAllDesserts() {
    return http.post('/menu', {
      categories: ["Dessert"]
    },
    {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })
  },
  getAllAppetizers() {
    return http.post('/menu', {
      categories: ["Appetizer"]
    },
    {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })
  },
  getAllSalads() {
    return http.post('/menu', {
      categories: ["Salad"]
    },
    {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })
  },
  getAllSpecialtyPizzas() {
    return http.post('/menu', {
      categories: ["Specialty Pizza"]
    },
    {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })
  },
  updateProduct(product) {
    console.log(product)
    return http.put(`/menu/${product.product_id}`, 
      product
    ,
    {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })
  },
  createProduct(product) {
    return http.post(`/product`, 
      product
    ,
    {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })
  },
  getProductById(id) {
    console.log(id)
    return http.get(`/menu/${id}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })
  }
}