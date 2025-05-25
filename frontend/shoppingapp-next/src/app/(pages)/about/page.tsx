import axios from "axios"
import Link from 'next/link';

type ResponseData = {
  message: string;
}

async function getAboutMessage (): Promise<ResponseData> {
  const response = await axios.get("http://localhost:8083/api/about");
  return response.data;
}

export default async function About(){
  const data = await getAboutMessage();
  return(
    <div>
      <h1>{data.message}</h1> <br />
      <Link href="/auth/login">
      <button>Go to Login</button>
      </Link>
    </div>
  )
}