function selectCast(){
    let number= document.getElementById("select-cast").value;
    let text=document.getElementById("cast").value;
    if (text===""){
        document.getElementById("cast").value= `${number}`;
    }
    else {
        document.getElementById("cast").value= `${text},${number}`;
    }
}

function selectDirector(){
    let number= document.getElementById("select-director").value;
    let text=document.getElementById("director").value;
    if (text===""){
        document.getElementById("director").value= `${number}`;
    }
    else {
        document.getElementById("director").value= `${text},${number}`;
    }
}

function selectGenre(){
    let number= document.getElementById("select-genre").value;
    let text=document.getElementById("genre").value;
    if (text===""){
        document.getElementById("genre").value= `${number}`;
    }
    else {
        document.getElementById("genre").value= `${text},${number}`;
    }
}

function selectCountry(){
    let number= document.getElementById("select-country").value;
    let text=document.getElementById("country").value;
    if (text===""){
        document.getElementById("country").value= `${number}`;
    }
    else {
        document.getElementById("country").value= `${text},${number}`;
    }
}
