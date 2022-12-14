import {CONFIG} from './config';



const authService = {
    
    isAuthenticated : () => {
        console.log(localStorage.getItem("authenticated"))
        return localStorage.getItem("authenticated") === "yes";
    },

    redirectToAuthorizationServer : () => {
        window.location.replace(`${CONFIG.AUTHORIZE_ENDPOINT}?client_id=${CONFIG.CLIENT_ID}` +
        `&response_type=${CONFIG.RESPONSE_TYPE}&scope=${CONFIG.SCOPE}` +
        `&redirect_uri=${CONFIG.REDIRECT_URI}`);
    },

    getToken : () => {
        fetch(CONFIG.TOKEN_ENDPOINT, {
            method: 'POST',
            body: 'grant_type=client_credentials&client_id='+ CONFIG.CLIENT_ID,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
            }).then(function (resp) {

                // Return the response as JSON
                return resp.json();

            }).then(function (data) {

                // Log the API data
                console.log('token', data);

            }).catch(function (err) {

                // Log any errors
                console.log('something went wrong', err);

            });
    },
}

export default authService;