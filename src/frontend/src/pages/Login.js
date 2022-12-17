import { Button } from "@mui/material";
import authService from "../util/authService"
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import jwt_decode from "jwt-decode";

export default function Login() {
    const navigate = useNavigate();

    const initiateLogin = () => {
        authService.redirectToAuthorizationServer();
    }

    function handleCredentialResponse(response) {
        //console.log("Encoded JWT ID token: " + response.credential);
        const decoded = jwt_decode(response.credential);
        const exp = decoded.exp;
        localStorage.setItem("token", response.credential);
        localStorage.setItem("expiration", exp);

        navigate("/MailList/Inbox");
    }

    useEffect(() => {
        if (authService.isAuthenticated()){
            
            navigate("/MailList/Inbox");
        }
        else{
            /* global google */
            google.accounts.id.initialize({
                client_id: "841655652099-bq7mbqbdoaq6tqso2bi75lr22mvjjlu3.apps.googleusercontent.com",
                callback: handleCredentialResponse
              });
            google.accounts.id.renderButton(
                document.getElementById("googleButtonDiv"),
                { theme: "outline", size: "large" }  // customization attributes
              );
            google.accounts.id.prompt(); // also display the One Tap dialog
        }
    },[]);

    return (
    <div id="error-page">
        <div id="googleButtonDiv"></div> 
    </div>
    );
}

/*
<div id="g_id_onload"
        data-client_id="841655652099-bq7mbqbdoaq6tqso2bi75lr22mvjjlu3.apps.googleusercontent.com"
        data-context="signin"
        data-ux_mode="popup"
        data-login_uri="http://localhost:8080/callback/google"
        data-nonce=""
        data-auto_prompt="false">
   </div>
   /*
   <div class="g_id_signin"
        data-type="standard"
        data-shape="pill"
        data-theme="filled_black"
        data-text="continue_with"
        data-size="large"
        data-locale="en-US"
        data-logo_alignment="left"
        data-width="250">
   </div>*/