
import React, { useEffect, useState } from 'react';
import { postEmail } from '../../util/api';
import './WriteMail.css';
import { Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function WriteMail({handleAfterSent}) {
    const [mailData, setMailData] = useState([]);
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
        //throw new Error("error error");
        postEmail(toList, subject, text);
        navigate("/MailList/Sent")
    }
    return (
    <div className="container" >
        

        <div>
            
            To<input className='field' type="text" 
            onChange={(e)=>handleToListChange(e.target.value)} value={toList} /> 

            Subject
            <input className='field' type="text" 
            onChange={(e)=>handleSubjectChange(e.target.value)} value={subject} /> 

            
        </div>
        Body
        <input className='text' type="text" 
            onChange={(e)=>handleTextChange(e.target.value)} value={text} /> 
            

        <Button onClick={handleSend} value="send">Send</Button>
        
    </div>
    );
}
