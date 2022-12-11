
import Paper from '@mui/material/Paper';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getEmail } from '../../util/api';
import './ViewMail.css';

export default function ViewMail() {
    const [mailData, setMailData] = useState([]);
    const {mailId} = useParams();

    useEffect(() => {
        getEmail(mailId).then((data) => {
            setMailData(data);
            console.log(data)
        })
    }, []);
//{mailData === undefined ? null : mailData.body    }
    return (
    <div className="container" >
        
        
            <div>
                <div className='field'>
                    {mailData.from === undefined ? null : mailData.from    }
                </div>

                <div className='field'>
                    {mailData.to === undefined ? null : mailData.to    }  
                </div>

                <div className='field'>
                    {mailData.subject === undefined ? null : mailData.subject    }
                </div>
            </div>
            
            <div className='text'>
                {mailData.body === undefined ? null : mailData.body    }
            </div>
        
        
    </div>
    );
}
