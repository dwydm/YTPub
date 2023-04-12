import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import { useDispatch } from 'react-redux';
import { clearToken } from '../store/token';
import { clearUser } from '../store/loggedUser';

function Logout() {
    let navigate = useNavigate();
    const dispatch = useDispatch();
    dispatch(clearToken());
    dispatch(clearUser());
    delete axios.defaults.headers.common["Authorization"];
    navigate("/welcome");
    };
  
  
  export default Logout;
