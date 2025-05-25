"use client";

import React, { useState, useEffect } from 'react';
import { AppBar, Box, Button, Snackbar, Toolbar, Typography } from '@mui/material';
import Link from 'next/link';
import { useRouter, usePathname } from 'next/navigation';
import './Navbar.css';

function Navbar() {
  const router = useRouter();
  const pathname = usePathname();

  const [mounted, setMounted] = useState(false);
  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');

  useEffect(() => {
    setMounted(true);
  }, []);

  const logout = () => {
    localStorage.removeItem('role');
    localStorage.removeItem('customerId');
    setSnackbarMessage('User logged out successfully.');
    setOpenSnackbar(true);
    router.push('/auth/login');
  };

  const closeSnackbar = () => {
    setOpenSnackbar(false);
  };

  const renderButtons = () => {
    if (!mounted) return null;

    if (pathname === '/auth/register') {
      return (
        <Link href="/auth/login">
          <Button type="button" className="login-button">
            Login
          </Button>
        </Link>
      );
    }

    if (pathname === '/auth/login') {
      return (
        <Link href="/auth/register">
          <Button type="button" className="register-button">
            Register
          </Button>
        </Link>
      );
    }

    if (pathname === '/dashboard') {
      return (
        <Button type="button" className="mx-1" onClick={logout}>
          Logout
        </Button>
      );
    }

    return (
      <>
        <Button type="button" className="mx-1" onClick={() => router.push('/dashboard')}>
          Dashboard
        </Button>
        <Button type="button" className="mx-1" onClick={logout}>
          Logout
        </Button>
      </>
    );
  };

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" sx={{ background: '#fff' }}>
        <Toolbar className="Toolbar">
          <Typography variant="h6" component="div" sx={{ flexGrow: 1, color: '#000' }}>
            Shopping App...
          </Typography>
          <div className="nav-buttons">{renderButtons()}</div>
        </Toolbar>
      </AppBar>

      <Snackbar
        open={openSnackbar}
        autoHideDuration={6000}
        onClose={closeSnackbar}
        message={snackbarMessage}
      />
    </Box>
  );
}

export default Navbar;
