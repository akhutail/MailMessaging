import authService from "../util/authService"
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import jwt_decode from "jwt-decode";


export default function Login() {
    const navigate = useNavigate();
    

    function handleCredentialResponse(response) {
        //console.log("Encoded JWT ID token: " + response.credential);
        const decoded = jwt_decode(response.credential);
        const exp = decoded.exp;
        localStorage.setItem("token", response.credential);
        localStorage.setItem("expiration", exp);
        localStorage.setItem("SuperMailProfileName", decoded.name);
        localStorage.setItem("SuperMailProfilePicture", decoded.picture);
        localStorage.setItem("SuperMailProfileEmail", decoded.email);
        navigate("/MailList/Inbox");
    }

    useEffect(() => {
        if (authService.isAuthenticated()) {

            navigate("/MailList/Inbox");
        }
        else {
            /* global google */
            window.onload = () => {
                console.log(google)
                window.google.accounts.id.initialize({
                    client_id: "841655652099-bq7mbqbdoaq6tqso2bi75lr22mvjjlu3.apps.googleusercontent.com",
                    callback: handleCredentialResponse
                });
                window.google.accounts.id.renderButton(
                    document.getElementById("googleButtonDiv"),
                    { theme: "outline", size: "large" }  // customization attributes
                );
                window.google.accounts.id.prompt(); // also display the One Tap dialog
            }
        }
    }, []);

    return (
        <div id="error-page">
            <div id="googleButtonDiv"></div>
        </div>
    );
}