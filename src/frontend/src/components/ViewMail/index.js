import React, {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import {getEmail} from '../../util/api';
import { getTimeOrDate } from '../../util/commonFunctions';
import styles from './styles.module.sass';

export default function ViewMail() {
    const [mailData, setMailData] = useState([]);
    const { folderName, mailId } = useParams();

    useEffect(() => {
        getEmail(mailId, folderName).then((data) => {
            setMailData(data);
        })
    }, [mailId]);

    return (
        <div className={styles.root} >

            <div className={styles.time}>
                {mailData.from === undefined ? null : getTimeOrDate(mailData.id)}
            </div>

            <div className={styles.subject}>
                {mailData.from === undefined ? null : mailData.subject}
            </div>

            <div className={styles.from}>
                {mailData.to === undefined ? null : folderName === 'Sent' ? mailData.to : mailData.from}
            </div>

            <div className={styles.divider}/>

            <div className={styles.body}>
                {mailData.body === undefined ? null : mailData.body}
            </div>


        </div>
    );
}
