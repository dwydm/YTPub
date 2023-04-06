import React, { useEffect, useState } from 'react'
import axiosInstance from '../axiosInstance';
import whytipi from '../layout/whytipi.png';

export default function Topics() {
    const [topics, setTopics]=useState([])

    useEffect(()=> {
        loadTopics();

    },[])

    const loadTopics=async()=> {
        const result=await axiosInstance.get("http://localhost:8080/api/topics/list")
        setTopics(result.data);
    }

  return (
    <div className='container'>
        <div className='topics-logo'>
            <img src={whytipi}></img>
        </div>
        <div className='py-4'>
            <table className="table border shadow">
                <thead>
                    <tr>
                        <th scope="col">Topic</th>
                        <th scope="col">Title</th>
                        <th scope="col">Description</th>
                        <th scope="col">Created</th>
                        <th scope="col">Author</th>
                        <th scope="col">CoAuthored</th>
                        <th scope="col">Status</th>
                        <th scope="col">Actions</th>
                        
                    </tr>
                </thead>
                <tbody>
                    {
                    topics.map((topic)=>(
                        <tr>
                            <td>

                                {topic.topicSerialNumber}
                                </td>
                            <td>{topic.title}</td>
                            <td>{topic.description}</td>
                            <td>{topic.creationDate}</td>
                            <td>{topic.mainAuthor.name}</td>
                            <td>{topic.coauthorStatus}</td>
                            <td>{topic.publicationStatus}</td>
                            <td>
                                <button className='btn btn-outline-dark'>Details</button>

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
