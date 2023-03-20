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
        localStorage.removeItem("token");
        localStorage.removeItem("SuperMailProfileName");
        localStorage.removeItem("SuperMailProfilePicture");
        localStorage.removeItem("SuperMailProfileEmail");
        navigate('/Login');
      }
      
    } catch (err) {
      console.log(err);
      
    }
      
    
  }, []);

  return (
    <div className={styles.root}>
      <LeftPanel />
      <div className={styles.outlet}>
        <Outlet />
      </div>
    </div>
  );
}
