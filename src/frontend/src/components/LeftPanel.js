
import MailIcon from '@mui/icons-material/Mail';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import {getFolders} from '../util/api';
import { Outlet, Link, useNavigate } from "react-router-dom";
import React, { useEffect, useState } from 'react';
import { Button } from '@mui/material';

export default function LeftPanel() {
    const [mailList, setMailList] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getFolders().then((data) => {
            setMailList(data);
        })
    }, []);

    const handleCompose = () => {
        navigate("/Compose");
    };

    return (
    <div sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        <nav aria-label="main mailbox folders">
            {mailList ?
            <List>
                {mailList.map((elem, index) => (
                <ListItem disablePadding key={index}>
                    <Link to={`MailList/${elem.label}`}>
                <ListItemButton >
                    <ListItemIcon>
                    <MailIcon />
                    </ListItemIcon>
                    <ListItemText primary={elem.label} />
                </ListItemButton> </Link>
                </ListItem>
            
                ))} 
                
            </List>

        :
        
            <div>Error fetching folders</div>
        }
        <Button onClick={handleCompose}>
            Compose
        </Button>
        </nav>
    </div>
    )
}
