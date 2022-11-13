
import MailIcon from '@mui/icons-material/Mail';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import {getFolders} from '../util/api';

import React, { useEffect, useState } from 'react';

export default function LeftPanel({onClickFolderName}) {
  const [mailListState, setMailListState] = useState([]);//
    useEffect(() => {
        getFolders().then((data) => {
            setMailListState(data);
        })
    }, []);
    return (
    <div sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        <nav aria-label="main mailbox folders">
        <List>
            {mailListState.map((elem, index) => (
            <ListItem disablePadding key={index}>
            <ListItemButton onClick={() => onClickFolderName(elem.label)}>
                <ListItemIcon>
                <MailIcon />
                </ListItemIcon>
                <ListItemText primary={elem.label} />
            </ListItemButton>
            </ListItem>
            ))}
        </List>
        </nav>
    </div>
    )
}
