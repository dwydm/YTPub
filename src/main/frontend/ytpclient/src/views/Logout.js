import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import store from '../store';

function Logout() {
    let navigate = useNavigate();
    store.dispatch({ type: 'SET_TOKEN', payload: null});
    store.dispatch({ type: 'SET_USER', payload: null});
    delete axios.defaults.headers.common["Authorization"];
    navigate("/");
    };
  
  
  export default Logout;
