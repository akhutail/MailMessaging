
import Paper from '@mui/material/Paper';
import React, { useEffect, useState } from 'react';
import {getEmailsByFolder} from '../util/api';

export default function ViewMail({mailId}) {

    const [mailId, setMailData] = useState(mailData);

    useEffect(() => {
        getEmail(mailId).then((data) => {
            setMailData(data);
        })
    }, []);

    return (
    <Paper sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        
    </Paper>
    );
}
