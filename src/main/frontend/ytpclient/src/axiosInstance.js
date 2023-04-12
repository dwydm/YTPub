import axios from 'axios';
import { useSelector } from 'react-redux';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080',
});


axiosInstance.interceptors.request.use(
    config => {
      //const token = 'YWRtaW5AeXRwLmFwcDphZG1pbg==';
      const token = useSelector((state) => state.token.token);
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