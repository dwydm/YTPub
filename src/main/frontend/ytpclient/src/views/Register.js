import axios from 'axios'
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'

export default function Register() {
    let navigate = useNavigate();
    const [user,setUser] = useState({
      email:"",
      password:""
    })

    const {email,password}=user

    const onInputChange=(event)=>{
      setUser({...user, [event.target.name]:event.target.value})
    }

    const onSubmit=async (e) => {
      e.preventDefault();
      await axios.post("http://localhost:8080/api/auth/register", user)
      navigate("/");
    }

  return <div class="container">
      <div class="reg-form">
        <form onSubmit={(e)=>onSubmit(e)}>
        <div class="form-text">
          <p>Every account has to be activated by an ADMIN
            <p>thank you for your understanding</p>
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
            <div id="emailHelp" class="form-text">Never use your Bank or Gov passwords</div>
          </div>
          <button type="submit" class="btn btn-dark">Register</button>
        </form>

      </div>
    </div>
}
