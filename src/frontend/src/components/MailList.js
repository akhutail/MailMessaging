import MailIcon from '@mui/icons-material/Mail';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Paper from '@mui/material/Paper';
import React, { useEffect, useState } from 'react';
import getEmailsByUser from '../util/api';

export default function MailList() {
    const [mailListState, setMailListState] = useState([]);//
    useEffect(() => {
        getEmailsByUser.then((data) => {
            setMailListState(data);
        })
    }, []);
    return (
    <Paper sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        <nav aria-label="main mailbox folders">
        <List>
            {mailListState.map((elem, index) => (
            <ListItem disablePadding key={index}>
            <ListItemButton>
                <ListItemIcon>
                <MailIcon />
                </ListItemIcon>
                <ListItemText primary={elem.label} />
            </ListItemButton>
            </ListItem>
            ))}
        </List>
        </nav>
    </Paper>
    );
}
