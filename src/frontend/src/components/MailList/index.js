
import React, { useEffect, useState } from 'react';
import { useNavigate, useParams, Outlet } from 'react-router-dom';
import { getEmailsByFolder, deleteMails } from '../../util/api';
import IconButton from '@mui/material/IconButton';
import { getTimeOrDate } from '../../util/commonFunctions';
import DeleteRoundedIcon from '@mui/icons-material/DeleteRounded';import Checkbox from '@mui/material/Checkbox';
import styles from './styles.module.sass';

export default function MailList() {
    const [mails, setMails] = useState([]);
    const [checkedState, setCheckedState] = useState(new Map());
    const [selectedCount, setSelectedCount] = useState(0);
    const [numRead, setNumRead] = useState(0);
    const { folderName, mailId } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        getEmailsByFolder(folderName).then((data) => {
            //console.log(data);
            setMails(data.emails);
            setNumRead(data.numRead);

            const checkedStateMap = new Map();
            data.emails.forEach(email => checkedStateMap.set(email.id, false));
            setCheckedState(checkedStateMap);
        })
    }, [folderName]);

    const handleViewMail = (mailId) => {
        navigate(`/MailList/${folderName}/${mailId}`);
    };

    const handleCheckbox = (id) => {
        const newState = new Map(checkedState);
        newState.set(id, !checkedState.get(id));
        // a checkbox was checked
        if(newState.get(id)){
            setSelectedCount(selectedCount + 1);
        }
        else{
            setSelectedCount(selectedCount - 1);
        }
        setCheckedState(newState);
    }

    const handleDelete = () => {
        if(mailId !== undefined) {
            return;
        }
        const mailsToDelete = [];
        checkedState.forEach((isSelected, id) => {
            if(isSelected){
                mailsToDelete.push(id);
            }
        });
        deleteMails(mailsToDelete);
    }

    return (
        <div className={styles.root}>
            <div className={styles.rootList}>
                <div className={styles.topPanel}>
                    <div>
                    <div className={styles.folderTitle}>{folderName}</div>
                    <div className={styles.folderStats}>{mails.length} Messages :  {mails.length - numRead} Unread</div>
                    </div>
                    <IconButton onClick={handleDelete}>
                        <DeleteRoundedIcon  className={styles.deleteButton} style={ selectedCount === 0 || mailId !== undefined ? {color: 'grey'} : {color : 'red'} }/>
                    </IconButton>
                    
                </div>
                {mails ?
                    <div>

                        {mails.map((elem, index) => (
                            
                                mailId === undefined
                                ?

                            <div className={styles.checkboxRow}>
                                <Checkbox className={styles.checkbox} checked={checkedState.get(elem.id)} onChange={() => handleCheckbox(elem.id)} size='small'/>
                                <div className={styles.emailRow} onClick={() => handleViewMail(elem.id)} key={elem.id}>
                                    <div className={elem.isRead ? styles.sender : styles.senderUnread} > {elem.from} </div>
                                    <div className={elem.isRead ? styles.greyMailSubjectBody : styles.mailSubjectBody} > {elem.subject + (elem.prefix === undefined ? "" : " - " + elem.prefix + (elem.prefix.length >= 40 ? '...' : ''))} </div>
                                    <div className={elem.isRead ? styles.greyDate : styles.date} > {getTimeOrDate(elem.id)} </div>
                                </div>
                            </div>
                            :

                            <div className={styles.checkboxRow}>
                                <div className={styles.emailRow} onClick={() => handleViewMail(elem.id)} key={elem.id}>
                                    <div className={elem.isRead ? styles.sender : styles.senderUnread} > {elem.from} </div>
                                    <div className={elem.isRead ? styles.greyMailSubjectBody : styles.mailSubjectBody} > {elem.subject} </div>
                                    <div className={elem.isRead ? styles.greyDate : styles.date} > {getTimeOrDate(elem.id)} </div>
                                </div>
                            </div>
                            
                        ))}

                    </div> :
                    <div>Error fetching mails!</div>
                }

            </div>

            <div className={styles.outlet}>
                <Outlet />
            </div>
        </div>
    );
}


