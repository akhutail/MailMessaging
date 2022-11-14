
import Paper from '@mui/material/Paper';
import React, { useEffect, useState } from 'react';
import {getEmail} from '../util/api';

export default function ViewMail({mailId}) {
    console.log(mailId)
    const [mailData, setMailData] = useState();

    useEffect(() => {
        getEmail(mailId).then((data) => {
            setMailData(data);
            console.log(data)
        })
    }, []);

    return (
    <Paper sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        <b>{mailData == undefined ? null : mailData.body    }</b>
    </Paper>
    );
}
