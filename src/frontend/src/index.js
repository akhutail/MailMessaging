import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Mail from './pages/Mail';

import reportWebVitals from './reportWebVitals';
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import ErrorPage from "./pages/error-page";
import Login from "./pages/Login";
import MailList from './components/MailList';
import ViewMail from './components/ViewMail/ViewMail';
import WriteMail from './components/WriteMail/WriteMail';

const router = createBrowserRouter([
  {
    path: "/",
    element: <Mail />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: "MailList/:folderName",
        element: <MailList />,
      },
      {
        path: "Mail/:mailId",
        element: <ViewMail />,
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
  }
  
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
