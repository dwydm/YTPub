
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
import Profile from './views/Profile';
import Sheet from './views/Sheet';
import NewTopic from './views/NewTopipc';



function App() {
  const authenticated = store.getState().loggedUser;
  return (
    <div class="App">
      <Router>
        <MainNavBar/>
        <Routes>
          <Route exact path="/" element={<Welcome/>} />
          <Route exact path="/home"  element={<Topics/>}/>
          <Route exact path="/register" element={<Register/>} />
          <Route exact path="/login" element={<Login/>} />
          <Route exact path="/users" element={<Users/>} />
          <Route exact path="/profile" element={<Profile/>} />
          <Route exact path="/sheet" element={<Sheet/>} />
          <Route exact path="/create" element={<NewTopic/>} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
