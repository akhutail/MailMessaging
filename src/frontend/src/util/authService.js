import {CONFIG} from './config';



const authService = {
    
    isAuthenticated : () => {
        return localStorage.getItem("token") !== undefined && localStorage.getItem('token') !== null
            && new Date(localStorage.getItem("expiration")*1000) > Date.now() ;
    },


    //unused currently
    redirectToAuthorizationServer : () => {
        window.location.replace(`${CONFIG.AUTHORIZE_ENDPOINT}?client_id=${CONFIG.CLIENT_ID}` +
        `&response_type=${CONFIG.RESPONSE_TYPE}&scope=${CONFIG.SCOPE}` +
        `&redirect_uri=${CONFIG.REDIRECT_URI}`);
    },
}

export default authService;