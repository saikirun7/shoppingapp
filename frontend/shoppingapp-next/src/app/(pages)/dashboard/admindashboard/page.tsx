"use client"

import axios from 'axios';
import { useRouter } from 'next/navigation'
import React, { useEffect, useState } from 'react'

function AdminDashboard() {
  const route = useRouter();
  const [data, setData] = useState([]);

  const logout = () => {
    route.push("/auth/login")
  }

  const getData = async() => {
    try {
      const response = await axios.get("http://localhost:8081/products/getProducts");
      console.log(response.data.product)
      setData(response.data.products);
    } catch (error: any) {
      console.log(error);
    }
  }

  useEffect(()=>{
    getData();
  },[]);

  return (
    <div>
      <h1>Dashboard</h1>
      <button onClick={logout}>Logout</button>
    </div>
    
  )
}

export default AdminDashboard