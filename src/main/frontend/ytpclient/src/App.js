
import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Topics from './views/Topics';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import store from './store';
import Register from './views/Register';
import Welcome from './views/Welcome';
import MainNavBar from './layout/MainNavBar';
import Login from './views/Login';
import Users from './views/Users';


function App() {
  const authenticated = store.getState().loggedUser;
  return (
    <div class="App">
      <Router>
        <MainNavBar/>
        <Routes>
          
          <Route exact path="/" 
            element={authenticated ? <Topics/> : <Welcome replace to={"/welcome"} />} 
            />
          <Route exact path="/register" element={<Register/>} />
          <Route exact path="/login" element={<Login/>} />
          <Route exact path="/users" element={<Users/>} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
