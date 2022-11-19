export const getFolders = () => {
    const userId = "akhutail";
    const data = fetch(`http://localhost:8080/folders`)
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
        });
    
    return data;
}

export const getEmailsByFolder = (label) => {

    const data = fetch(`http://localhost:8080/emailsByFolder?folderLabel=${label}`)
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
        });
    
    return data;
}

export const getEmail = (id) => {

    const data = fetch(`http://localhost:8080/email?mailId=${id}`)
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
        });
    
    return data;
}

export const postEmail = (toList, subject, body) => {
    const mail = {"to": ["sadf","asdf"], subject, body};
    const response = fetch(`http://localhost:8080/email`, {
        method: 'POST',
        body: JSON.stringify(mail)
        },
        )
        .then((response) => {
            if(response.ok){
                return response.json();
            }
            else {
                throw Error(response.status)
            }
        });
    
    return response;
}