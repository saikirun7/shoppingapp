"use client";

import axios from "axios";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

type Product = {
  productId: number;
  name: string;
  description: string;
  price: number;
};

function Cart() {
  const route = useRouter();
  const [cartData, setCartData] = useState<Product[]>([]);
  const [customerId, setCustomerId] = useState<number | null>(null);
  const [hydrated, setHydrated] = useState(false);

  useEffect(() => {
    const storedId = localStorage.getItem("customerId");
    if (storedId) {
      setCustomerId(parseInt(storedId));
    } else {
      alert("Customer ID not found. Please login.");
      route.push("/auth/login");
    }
    setHydrated(true);
  }, []);

  const fetchCartData = async (custId: number) => {
    try {
      const response = await axios.get("http://localhost:8082/customer-order/cart-orders", {
        params: { customerId: custId },
      });
      setCartData(response.data.products);
    } catch (error) {
      console.error("Error fetching cart data:", error);
    }
  };

  useEffect(() => {
    if (customerId !== null) {
      fetchCartData(customerId);
    }
  }, [customerId]);

  const deleteFromCart = async (productId: number) => {
    if (customerId === null) return;
    try {
      await axios.delete("http://localhost:8082/customer-order/delete-item", {
        params: { customerId, productId },
      });
      // Refresh cart data after delete
      fetchCartData(customerId);
    } catch (error) {
      console.error("Error deleting cart item:", error);
    }
  };

  if (!hydrated || customerId === null) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h1>Cart</h1>
      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(auto-fill, minmax(250px, 1fr))",
          gap: "20px",
          marginTop: "30px",
        }}
      >
        {cartData.map((product) => (
          <div
            key={product.productId}
            style={{
              border: "1px solid #ccc",
              borderRadius: "10px",
              padding: "16px",
              boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
              textAlign: "center",
              backgroundColor: "#f9f9f9",
            }}
          >
            <h3>{product.name}</h3>
            <p>{product.description}</p>
            <p>
              <strong>₹{product.price}</strong>
            </p>
            <button onClick={() => deleteFromCart(product.productId)}>Delete from cart</button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Cart;
