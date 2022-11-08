import React from 'react';
import {
  Route,
  Routes
} from 'react-router-dom';
import './App.css';
import Mail from './pages/Mail';
import { useState } from 'react';

export default function App() {
  const [state, setState] = useState("authenticated");

  return (
    <Mail />
  );
}
