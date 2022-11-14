import MailIcon from '@mui/icons-material/Mail';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Paper from '@mui/material/Paper';
import React, { useEffect, useState } from 'react';
import {getEmailsByFolder} from '../util/api';

export default function MailList({folder, handleViewMail}) {
    const [mails, setMails] = useState([]);//
    useEffect(() => {
        getEmailsByFolder(folder).then((data) => {
            setMails(data);
        })
    }, [folder]);

    return (
    <Paper sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        <nav aria-label="main mailbox folders">
        <List>
            {mails.map((elem, index) => (
            <ListItem disablePadding key={index}>
            <ListItemButton onClick={() => handleViewMail(elem.id)}>
                <ListItemIcon>
                <MailIcon />
                </ListItemIcon>
                <ListItemText primary={elem.from} />
                <ListItemText primary={elem.subject} />
            </ListItemButton>
            </ListItem>
            ))}
        </List>
        </nav>
    </Paper>
    );
}
