import authService from "../util/authService"
import { useNavigate, useParams, useSearchParams } from "react-router-dom";
import { useEffect } from "react";

export default function Login() {
    const navigate = useNavigate();
    const path = useSearchParams();

    
    useEffect(() => {
        console.log(path)
        //use code to get token and save to local storage todo use a safer method to store token
        //authService.storeToken(path.code);
        if (authService.isAuthenticated()){
            //navigate("/MailList/Inbox");
        }
    },[]);

    return (
    <div>
        <h1>Logging in</h1>
    </div>
    );
}