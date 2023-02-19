export const getFolders = () => {
    const userId = "akhutail";
    console.log(localStorage.getItem('token'));
    const data = fetch(`http://localhost:8080/folders`, {
        headers:{
            'Authorization': `Bearer ${localStorage.getItem('token')}`,
            //'Content-Type': 'application/json'
        },
    })
        .then((response) => {
            
            if(response.ok){
                return response.json();
            }
            else {
                throw Error(data.status)
            }
        })
        .then((data) => {
            //console.log(data);
            return data;
        }).catch(err => {
            console.log("error api: "+ err);
        });
    
    return data;
}

export const getEmailsByFolder = (label) => {

    const data = fetch(`http://localhost:8080/emailsByFolder?folderLabel=${label}`,{
        headers:{
            'Authorization': `Bearer ${localStorage.getItem('token')}`,
            //'Content-Type': 'application/json'
            },
        })
        .then((response) => {
            if(response.ok){
                return response.json();
            }
            else {
                throw Error(data.status)
            }
        })
        .then((data) => {
            //console.log(data);
            return data;
        }).catch(err => {
            console.log("error api: "+ err);
        });
    
    return data;
}

export const getEmail = (id) => {

    const data = fetch(`http://localhost:8080/email?mailId=${id}`, {
        headers:{
            'Authorization': `Bearer ${localStorage.getItem('token')}`,
            //'Content-Type': 'application/json'
        },
    })
        .then((response) => {
            if(response.ok){
                return response.json();
            }
            else {
                throw Error(data.status)
            }
        })
        .then((data) => {
            //console.log(data);
            return data;
        }).catch(err => {
            console.log("error api: "+ err);
        });
    
    return data;
}

export const postEmail = (toList, subject, body) => {
    console.log(toList);
    //todo implement actual list of Tos
    const mail = {"to": ["receiver1","receiver2"], subject, body};
    const response = fetch(`http://localhost:8080/email`, {
            method: 'POST',
            body: JSON.stringify(mail),
            headers:{
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
                //'Content-Type': 'application/json'
            },
        },
        )
        .then((response) => {
            if(response.ok){
                return response.json();
            }
            else {
                throw Error(response.status)
            }
        }).catch(err => {
            console.log("error api: "+ err);
        });
    
    return response;
}