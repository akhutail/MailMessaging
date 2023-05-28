
import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import {getEmailsByFolder} from '../../util/api';
import uuidTime from 'uuid-time'
import styles from './styles.module.sass';

export default function MailList() {
    const [mails, setMails] = useState([]);
    const [numRead, setNumRead] = useState(0);
    const {folderName} = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        getEmailsByFolder(folderName).then((data) => {
            //console.log(data);
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
            <div className={styles.folderStats}>{mails.length} Messages :  {mails.length-numRead} Unread</div>
        </div>
        {mails ?
            <div>

                {mails.map((elem, index) => (
                <div className={styles.emailRow} onClick={() => handleViewMail(elem.id)} key={index}>
                    <div className={elem.isRead ? styles.sender : styles.senderUnread} > {elem.from} </div>
                    <div className={elem.isRead ? styles.greyMailSubjectBody : styles.mailSubjectBody} > {elem.subject  + (elem.prefix===undefined ? "" : " - " + elem.prefix + (elem.prefix.length>=40 ? '...' : ''))} </div>
                    <div className={elem.isRead ? styles.greyDate : styles.date} > {getTimeOrDate(elem.id)} </div>
                </div>
                ))}

            </div> :
            <div>Error fetching mails!</div>
        }
        
    </div>
    );
}

const getTimeOrDate = (timeUUID) => {
    const date = new Date(0);
    date.setUTCMilliseconds(uuidTime.v1(timeUUID));
    const today = new Date();
    if (
        today.getFullYear() === date.getFullYear() &&
        today.getMonth() === date.getMonth() &&
        today.getDate() === date.getDate()
      ) {
        const options = {hour: '2-digit', minute:'2-digit'};
        
        return date.toLocaleTimeString(undefined, options);
        
    }
    else{
        const options = { day: 'numeric', month: 'short', year: '2-digit' };
        return date.toLocaleDateString([], options);
    }
}
