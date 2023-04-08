
import React, { useEffect, useState } from 'react';
import { postEmail } from '../../util/api';
import styles from './styles.module.sass';
import { Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function WriteMail() {
    const [toList, setToList] = useState('');
    const [subject, setSubject] = useState('');
    const [text, setText] = useState('');
    const navigate = useNavigate();

    const handleToListChange = (value) => {
        setToList(value);
    };

    const handleSubjectChange = (value) => {
        setSubject(value);
    };

    const handleTextChange = (value) => {
        setText(value);
    };

    const handleSend = () => {
        //basic checks
        if (subject.length === 0) {
            window.alert("You forgot to add subject");
        }
        else if (toList.length === 0) {
            window.alert('You forgot to add the receiver');
        } else {
            postEmail(toListStringToArray(toList), subject, text).then( () =>
                navigate("/MailList/Sent")
            );
        }
    }
    return (
        <div className={styles.root} >


            <div className={styles.fieldContainer}>
                <div className={styles.fieldName}>To:</div>
                <input className={styles.field} type="text"
                    onChange={(e) => handleToListChange(e.target.value)} value={toList} />
            </div>
            <div className={styles.fieldContainer}>
                <div className={styles.fieldName}>Subject:</div>
                <input className={styles.field} type="text"
                    onChange={(e) => handleSubjectChange(e.target.value)} value={subject} />
            </div>

            <textarea className={styles.text} type="text"
                onChange={(e) => handleTextChange(e.target.value)} value={text} />

            <button className={styles.sendButton} onClick={handleSend}>Send</button>

        </div>
    );
}


const toListStringToArray = (toList) => {
    return toList.split(';');
}