
import MailIcon from '@mui/icons-material/Mail';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import AddRoundedIcon from '@mui/icons-material/AddRounded';
import { getEmail, getFolders, getProfileName, getProfilePicture, getProfileEmail } from '../../util/api';
import { Outlet, Link, useNavigate } from "react-router-dom";
import React, { useEffect, useState } from 'react';
import InboxIcon from '@mui/icons-material/MoveToInboxOutlined';
import OutboxIcon from '@mui/icons-material/OutboxOutlined';
import styles from './styles.module.sass';

export default function LeftPanel() {
    const [folderList, setFolderList] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getFolders().then((data) => {
            setFolderList(data);
        })
    }, []);

    const handleCompose = () => {
        navigate("/Compose");
    };

    return (
        <div className={styles.root}>

            <div className={styles.profileSection}>
                <img className={styles.profilePicture} src={getProfilePicture()}></img>
                <div>
                    <div className={styles.name}>{getProfileName()}</div>
                    <div className={styles.email}>{getProfileEmail()}</div>
                </div>
            </div>

            <Link to={`/Compose`}>
                <div className={styles.composeButton}>
                    <ListItemIcon>
                        <AddRoundedIcon className={styles.plusIcon}/>
                    </ListItemIcon>
                    <ListItemText primary={'Compose'} />
                </div>
            </Link>

            <div className={styles.folderList}>
                {folderList ?
                    <List className={styles.folder}>
                        {folderList.map((elem, index) => (
                            <ListItem disablePadding key={index} className={styles.folder} >
                                <Link to={`MailList/${elem.label}`}>
                                    <ListItemButton className={styles.folderButton}>
                                        <ListItemIcon>
                                            {elem.label == 'Inbox' ? <InboxIcon /> : <OutboxIcon />}
                                        </ListItemIcon>
                                        <ListItemText primary={elem.label} className={styles.folderName} />
                                    </ListItemButton> </Link>
                            </ListItem>

                        ))}

                    </List>

                    :

                    <div>Error fetching folders</div>
                }

            </div>
        </div>
    )
}
