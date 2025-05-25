"use client"

import { Button, TextField } from '@mui/material';
import axios from 'axios';
import { useState } from 'react'
import '../auth.css'
import { useRouter } from 'next/navigation';

function Register() {
  const route = useRouter();
  const [name, setName] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [mobile, setMobile] = useState<string>("");
  const [password, setpassword] = useState<string>("");
  const [role] = useState<string>("CUSTOMER");

  const handleSubmit = async(e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try{
      const response = await axios.post<{message: string}>("http://localhost:8080/auth/user/register", {name, email, mobile, password, role});
      console.log(response.data);
      route.push("/auth/login");
    } catch(error){
      console.log(error);
    }
  };

  return (
    <div>
      <div className='login-form'>
        <h1 className='login-head'>Register</h1>
        <form onSubmit={handleSubmit} className='form'>
          <div>
            <TextField
              className='TextField'
              type='text'
              name='name'
              label='Name'
              placeholder='Enter Name'
              required
              onChange={e => setName(e.target.value)}
              value={name}
            />
          </div>
          <br />
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
              type='text'
              name='mobile'
              label='Mobile'
              placeholder='Enter Mobile'
              required
              onChange={e => setMobile(e.target.value)}
              value={mobile}
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
            <Button type='submit'>Register</Button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Register