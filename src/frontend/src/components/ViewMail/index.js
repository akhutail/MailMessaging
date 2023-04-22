import React, {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import {getEmail} from '../../util/api';
import styles from './styles.module.sass';

export default function ViewMail() {
    const [mailData, setMailData] = useState([]);
    const { folderName, mailId } = useParams();

    useEffect(() => {
        getEmail(mailId, folderName).then((data) => {
            setMailData(data);
            console.log(data)
        })
    }, []);

    return (
        <div className={styles.top} >

            <div className='field'>
                {mailData.from === undefined ? null : mailData.from}
            </div>

            <div className='field'>
                {mailData.to === undefined ? null : mailData.to}
            </div>

            <div className='field'>
                {mailData.subject === undefined ? null : mailData.subject}
            </div>


            <div className='text'>
                {mailData.body === undefined ? null : mailData.body}
            </div>


        </div>
    );
}
