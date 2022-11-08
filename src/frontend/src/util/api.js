export default function getFolders(){
    const userId = "akhutail";
    const data = fetch(`http://localhost:8080/folders?userId=${userId}`)
        .then((response) => {
            if(response.ok){
                return response.json();
            }
            else {
                throw Error(data.status)
            }
        })
        .then((data) => {
            console.log(data);
            return data;
        });
    
    return data;
}

export default function getEmailsByUser(){
    const userId = "akhutail";
    const data = fetch(`http://localhost:8080/emails?userId=${userId}`)
        .then((response) => {
            if(response.ok){
                return response.json();
            }
            else {
                throw Error(data.status)
            }
        })
        .then((data) => {
            console.log(data);
            return data;
        });
    
    return data;
}