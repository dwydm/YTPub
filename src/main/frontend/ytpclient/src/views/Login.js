import axios from 'axios'
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import AlertError from '../layout/AlertError';
import { useDispatch, useSelector } from 'react-redux';
import { setToken } from '../store/token';



export default function Login() {
    let navigate = useNavigate();
    const dispatch = useDispatch();
    const [user,setUser] = useState({
      email:'',
      password:''
    })

    const {email,password}=user;

    const onInputChange=(event)=>{
      setUser({...user, [event.target.name]: event.target.value})
    }

    const onSubmit=async (e) => {
      e.preventDefault();
      await axios.post("http://localhost:8080/api/auth/login", user)
      .then((response)=> {

        let reqInstance = axios.create({
          headers: {
            Authorization : 'Basic ' + response.data.token 
            }
          }
        )
          dispatch(setToken(response.data.token));
          dispatch(setUser(response.data));
        navigate("/");
      }, (error) => {
        <AlertError message={error.message} />
      }
      )
    }

  return <div class="container">
      <div class="reg-form">
        <form onSubmit={(e)=>onSubmit(e)}>

        <div class="form-text">
          <p>Login to your YTP Aaccount
          </p>
        </div>
        <br></br>
          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Email address</label>
            <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name='email' value={email} onChange={(event)=>onInputChange(event)} placeholder='e-mail'/>
          </div>
          <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" name='password' value={password} onChange={(event)=>onInputChange(event)} placeholder='password'/>
          </div>
          <button type="submit" class="btn btn-dark">Login</button>
        </form>
      </div>
    </div>
}
