import axios from 'axios';
import store from './store';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080',
});


axiosInstance.interceptors.request.use(
    config => {
      //const token = 'YWRtaW5AeXRwLmFwcDphZG1pbg==';
      const token = store.getState().token;
      if(token) {
        config.headers['Authorization'] = `Basic ${token}`;
      }
      return config;
    },
    error => {
      return Promise.reject(error);
    }
  );

  export default axiosInstance;