window.onload = function() {
    let date = document.getElementById("date");

    var today = new Date();
    var year = today.getFullYear();
    var month = ('0' + (today.getMonth() +1)).slice(-2);
    var day = ('0' + today.getDate()).slice(-2);
    var hours = ('0' + today.getHours()).slice(-2); 
    var minutes = ('0' + today.getMinutes()).slice(-2); 

    var dateResult = year + "." + month + "." + day + "." + hours + ":" + minutes;
    console.log(date);
    date.innerText = dateResult;

    var tideDate = document.getElementsByClassName("tide_text");
    console.log(tideDate[0].firstChild.nextSibling);
    tideDate[0].firstChild.nextSibling.innerText = "dd";
}