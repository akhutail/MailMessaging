import * as React from 'react';
import { useEffect, useState } from 'react';
import LeftPanel from '../../components/LeftPanel';
import jwt_decode from "jwt-decode";
import {
  Outlet, useNavigate, useParams
} from "react-router-dom";
import authService from '../../util/authService';
import styles from './index.module.sass';
import WebFont from 'webfontloader';

export default function Root() {
  const navigate = useNavigate();

  useEffect(() => {
    WebFont.load({
      google: {
        families: ['Roboto', 'Chilanka']
      }
    });

    try{
      if (!authService.isAuthenticated()) {
        //redirect to inbox mail list which is a child route
        console.log('adsfadf')
        navigate('/Login');
      }
      const decoded = jwt_decode(localStorage.getItem("token"));
      localStorage.setItem("SuperMailProfileName", decoded.name);
      localStorage.setItem("SuperMailProfilePicture", decoded.picture);
      localStorage.setItem("SuperMailProfileEmail", decoded.email);
    } catch (err) {
      
      if( err.name === 'InvalidTokenError') {
        localStorage.removeItem('token');
        navigate('/Login');
      }
      console.log(err.name);
    }
    
  }, []);

  return (
    <div className={styles.root}>
      <LeftPanel />
      <Outlet  />
    </div>
  );
}
