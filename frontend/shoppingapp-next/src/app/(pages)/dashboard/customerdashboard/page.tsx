"use client";

import axios from 'axios';
import { useRouter } from 'next/navigation';
import React, { useEffect, useState } from 'react';
import ShoppingCartCheckoutIcon from '@mui/icons-material/ShoppingCartCheckout';
import '../Dashboard.css';
import { Button } from '@mui/material';

type Product = {
  productId: number;
  name: string;
  description: string;
  price: number;
};

type Order = {
  customerId: number;
  productId: number;
};

function CustomerDashboard() {
  const route = useRouter();
  const [data, setData] = useState<Product[]>([]);
  const [customerId, setCustomerId] = useState<number | null>(null);
  const [customerOrderCount, setCustomerOrderCount] = useState<number>();

  useEffect(() => {
    const storedId = localStorage.getItem("customerId");
    if (storedId) {
      setCustomerId(parseInt(storedId));
    } else {
      alert("Customer ID not found. Please login.");
      route.push("/auth/login");
    }
  }, [route]);

  useEffect(() => {
    if (customerId !== null) {
      getOrdersCountOfCustomer();
    }
  }, [customerId]);

  useEffect(() => {
    getData();
  }, []);

  const getOrdersCountOfCustomer = async () => {
    if (customerId === null) return;
    try {
      const response = await axios.get("http://localhost:8082/customer-order/cart-count", {
        params: { customerId }
      });
      setCustomerOrderCount(response.data.count);
    } catch (error) {
      console.error("Error fetching order count:", error);
    }
  };

  const addToCart = async (productId: number) => {
    if (customerId === null) return;

    const order: Order = { customerId, productId };
    try {
      await axios.post("http://localhost:8082/customer-order/add-order", order);
      await getOrdersCountOfCustomer();
      alert("Product added to cart!");
    } catch (error) {
      console.error("Error adding to cart:", error);
      alert("Failed to add product to cart.");
    }
  };

  const getData = async () => {
    try {
      const response = await axios.get("http://localhost:8081/products/getProducts");
      setData(response.data.products);
    } catch (error: any) {
      console.error("Error fetching products:", error);
    }
  };

  const goToCart = () => {
    route.push("/cart")
  }

  return (
    <div style={{ padding: '20px' }}>
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <h1>Customer Dashboard</h1>
        <div
          onClick={goToCart}
          style={{
            position: 'relative',
            display: 'inline-block',
            marginRight: '10px',
            cursor: 'pointer'
          }}>
          <ShoppingCartCheckoutIcon style={{ fontSize: '2rem' }} />
          {customerOrderCount !== undefined && (
            <span
              style={{
                position: 'absolute',
                top: '-8px',
                right: '-8px',
                background: 'red',
                color: 'white',
                borderRadius: '50%',
                padding: '2px 6px',
                fontSize: '0.75rem',
                fontWeight: 'bold',
              }}
            >
              {customerOrderCount}
            </span>
          )}
        </div>
      </div>

      <h2 style={{ marginTop: '20px' }}>Cart Items: {customerOrderCount ?? 0}</h2>

      {/* Product Cards Grid */}
      <div
        style={{
          display: 'grid',
          gridTemplateColumns: 'repeat(auto-fill, minmax(250px, 1fr))',
          gap: '20px',
          marginTop: '30px'
        }}>
        {data.map((product) => (
          <div
            key={product.productId}
            style={{
              border: '1px solid #ccc',
              borderRadius: '10px',
              padding: '16px',
              boxShadow: '0 2px 8px rgba(0,0,0,0.1)',
              textAlign: 'center',
              backgroundColor: '#f9f9f9'
            }}
          >
            <h3>{product.name}</h3>
            <p>{product.description}</p>
            <p><strong>₹{product.price}</strong></p>
            <Button variant="outlined" onClick={() => addToCart(product.productId)}>Add to Cart</Button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default CustomerDashboard;
