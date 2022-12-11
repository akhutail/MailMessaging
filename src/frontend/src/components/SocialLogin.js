import React, {useState, useEffect} from 'react';
import Box from '@mui/material/Box';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Divider from '@mui/material/Divider';
import InboxIcon from '@mui/icons-material/Inbox';
import DraftsIcon from '@mui/icons-material/Drafts';
import Paper from '@mui/material/Paper';
import MailIcon from '@mui/icons-material/Mail'

export default function SocialLogin() {
    const [mailListState, setMailListState] = useState([]);//
    useEffect(() => {
        
    }, []);

    if(this.props.authenticated) {
        return <Redirect
            to={{
            pathname: "/",
            state: { from: this.props.location }
        }}/>;            
    }


    return (
        <Paper sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
            <div className="social-login">
                <a className="btn btn-block social-btn google" href={GOOGLE_AUTH_URL}>
                    <img src={googleLogo} alt="Google" /> Log in with Google</a>
            </div>
    </Paper>
    );
}
