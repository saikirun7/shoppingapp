"use client"

import { useRouter } from 'next/navigation'
import { useEffect } from 'react';

function Dashboard() {
  const route = useRouter();
  const role = localStorage.getItem("role");

  useEffect(() => {
    if (role === 'CUSTOMER') {
      route.push("/dashboard/customerdashboard");
    } else {
      route.push("/dashboard/admindashboard");
    }
  }, [route]);

  return (
    <div>
      Redirecting....
    </div>
  )
}

export default Dashboard