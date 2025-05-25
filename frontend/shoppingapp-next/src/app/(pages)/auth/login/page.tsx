"use client"

import { Button, TextField } from '@mui/material';
import axios from 'axios';
import { useRouter } from 'next/navigation';
import { useState } from 'react'
import '../auth.css'

function Login() {
  const route = useRouter();
  const [email, setEmail] = useState<string>("");
  const [password, setpassword] = useState<string>("");

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      const response = await axios.post<{ message: string, role: string, customerId: string }>("http://localhost:8080/auth/user/login", { email, password });
      console.log(response.data);
      localStorage.setItem("role", response.data.role);
      localStorage.setItem("customerId", response.data.customerId);
      if (response.status === 200) {
        route.push("/dashboard");
      }
    } catch (error: any) {
      console.log(error);
    }
  };

  return (
    <div>
      <div className='login-form'>
        <h1 className='login-head'>Login</h1>
        <form onSubmit={handleSubmit} className='form'>
          <div>
            <TextField
              className='TextField'
              type='text'
              name='email'
              label='Email'
              placeholder='Enter Email'
              required
              onChange={e => setEmail(e.target.value)}
              value={email}
            />
          </div>
          <br />
          <div>
            <TextField
              className='TextField'
              type='password'
              name='password'
              label='Password'
              placeholder='Enter Password'
              required
              onChange={e => setpassword(e.target.value)}
              value={password}
            />
          </div>
          <br />
          <div className='login-btn'>
            <Button type='submit'>Login</Button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Login