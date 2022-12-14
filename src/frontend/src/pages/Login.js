import { Button } from "@mui/material";
import authService from "../util/authService"
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";

export default function Login() {
    const navigate = useNavigate();

    const initiateLogin = () => {
        authService.redirectToAuthorizationServer();
    }

    useEffect(() => {
        if (authService.isAuthenticated()){
            navigate("/MailList/Inbox");
        }
    },[]);

    return (
    <div id="error-page">
        <h1>Login with google</h1>
        <Button onClick={initiateLogin}>Login</Button>
    </div>
    );
}