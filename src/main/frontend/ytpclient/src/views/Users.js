import React, { useEffect, useState } from 'react'
import axiosInstance from '../axiosInstance';
import axios from 'axios';
import store from '../store';



export default function Users() {
    const [users,setUsers]=useState([])

    useEffect(()=> {
        loadUsers();
    },[])

    const loadUsers=async()=> {
        const result = await axiosInstance.get('http://localhost:8080/api/users/list')
        setUsers(result.data);
    }
    function handleUserClick(userId) {
        const response = axiosInstance.get(`http://localhost:8080/api/users/update/active/${userId}`)
          .then(() => {
            loadUsers();
          })
          .catch((error) => {
            console.error('Error updating user data:', error);
          });
      }
      


  return (
    <div className='container'>
        <div class='user-table-admin'>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">E-mail</th>
                        <th scope="col">Status</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                        {
                        users.filter((user) => user.id != store.getState().loggedUser.id)
                        .map((user)=>(
                            
                            <tr>
                                <td>{user.id}</td>
                                <td>{user.name}</td>
                                <td>{user.email}</td>
                                <td>{user.userActive ? 'active' : 'inactive'}</td>
                                <td>
                                <button onClick={() => handleUserClick(user.id)}>Activity</button>
                                </td>
                            </tr>
                        ))
                        }
                </tbody>
            </table>
        </div>
    </div>
  )
}
