import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import RootWithLeftPanel from './pages/Root';
import AuthCallback from './pages/AuthCallback'
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import MailList from './components/MailList';
import ViewMail from './components/ViewMail';
import WriteMail from './components/WriteMail/WriteMail';
import ErrorPage from "./pages/error-page";
import Login from "./pages/Login";
import reportWebVitals from './reportWebVitals';

const router = createBrowserRouter([  
  {
    path: "/",
    element: <RootWithLeftPanel />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: "MailList/:folderName",
        element: <MailList />,
        children : [
          {
            path: ":mailId",
            element: <ViewMail />,
          }
        ]
      },
      {
        path: "Compose",
        element: <WriteMail />,
      },
      
    ],
  },
  {
    path: "/login",
    element: <Login />,
    errorElement: <ErrorPage />,
  },
  {
    path: "/callback/google",
    element: <AuthCallback />,
    errorElement: <ErrorPage />,
  },
  
]);
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
