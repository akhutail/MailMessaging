import MailIcon from '@mui/icons-material/Mail';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Paper from '@mui/material/Paper';
import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import {getEmailsByFolder} from '../util/api';

export default function MailList() {
    const [mails, setMails] = useState([]);
    const {folderName} = useParams();
    const navigate = useNavigate();
    //console.log(folderName)

    useEffect(() => {
        console.log()
        getEmailsByFolder(folderName).then((data) => {
            setMails(data);
        })
    }, [folderName]);

    const handleViewMail = (mailId) => {
        navigate(`/Mail/${mailId}`);
    };

    return (
    <Paper sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        <nav aria-label="main mailbox folders">
        {mails.length ?
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
            </List> :
            <div>Emtpy!</div>
        }
        </nav>
    </Paper>
    );
}
