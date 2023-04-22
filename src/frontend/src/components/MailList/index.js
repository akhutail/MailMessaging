import MailIcon from '@mui/icons-material/Mail';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import {getEmailsByFolder} from '../../util/api';
import styles from './styles.module.sass';

export default function MailList() {
    const [mails, setMails] = useState([]);
    const [numRead, setNumRead] = useState(0);
    const {folderName} = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        getEmailsByFolder(folderName).then((data) => {
            console.log(data);
            setMails(data.emails);
            setNumRead(data.numRead);
        })
    }, [folderName]);

    const handleViewMail = (mailId) => {
        navigate(`/Mail/${folderName}/${mailId}`);
    };

    return (
    <div className={styles.root}>
        <div>
            <div className={styles.folderTitle}>{folderName}</div>
            <div className={styles.folderStats}>{mails.length} Messages :  {numRead} Unread</div>
        </div>
        {mails ?
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
            <div>Error fetching mails!</div>
        }
        
    </div>
    );
}
