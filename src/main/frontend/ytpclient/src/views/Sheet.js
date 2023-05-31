import React from 'react'

export default function Sheet() {
  const

  const [users,setUsers]=useState([])

    useEffect(()=> {
        loadUsers();
    },[])

    const loadUsers=async()=> {
        const result = await axiosInstance.get('http://localhost:8080/api/topics/{id}')
        setUsers(result.data);
    }
    
  return (
    <div>Sheet
            


    </div>
  )
}
