function createRow(boulder) {
    let table = document.getElementById('boulderTable');
    let row = document.createElement('tr');
    let name = document.createElement('td');
    let location = document.createElement('td');
    let grade = document.createElement('td');
    let status = document.createElement('td');
    let attemptDate = document.createElement('td');
    let completionDate = document.createElement('td');
    let div = document.createElement('div');
    let pencil = document.createElement('input');
    let bin = document.createElement('input');
    let redbin = document.createElement('input');

    let unformattedName = boulder.name.toString();
    name.innerText = unformattedName.charAt(0).toUpperCase() + unformattedName.substring(1).toLowerCase();

    let unformattedLocation = boulder.location.toString();
    location.innerText = unformattedLocation.charAt(0).toUpperCase() + unformattedLocation.substring(1).toLowerCase();

    let unformattedGrade = boulder.grade.toString();
    if (unformattedGrade.includes("P")) {
        grade.innerText = unformattedGrade.substring(1,3).toUpperCase() + "+";
    } else {
        grade.innerText = unformattedGrade.substring(1,3).toUpperCase();
    }

    let unformattedStatus = boulder.status.toString();
    status.innerText = unformattedStatus.charAt(0).toUpperCase() + unformattedStatus.substring(1).toLowerCase();

    if (unformattedStatus === "NOT ATTEMPTED") {
        status.setAttribute("id","notAttempted");
    } else if (unformattedStatus === "ATTEMPTED") {
        status.setAttribute("id","attempted");
    } else if (unformattedStatus === "COMPLETED") {
        status.setAttribute("id","completed");
    } else {
        status.setAttribute("id","flashed");
    }

    if (boulder.attemptDate === null) {
        attemptDate.innerText = "Not Attempted";
    } else {
        let unformattedAttemptDate = boulder.attemptDate.toString();
        attemptDate.innerText = unformattedAttemptDate.substring(8,10) + "/" + unformattedAttemptDate.substring(5,7) + "/" + unformattedAttemptDate.substring(0, 4);
    }

    if (boulder.completionDate === null) {
        completionDate.innerText = "Not Completed";
    } else {
        let unformattedCompletionDate = boulder.completionDate.toString();
        completionDate.innerText = unformattedCompletionDate.substring(8,10) + "/" + unformattedCompletionDate.substring(5, 7) + "/" + unformattedCompletionDate.substring(0,4);
    }


    table.appendChild(row);
    row.appendChild(name);
    row.appendChild(location);
    row.appendChild(grade);
    row.appendChild(status);
    row.appendChild(attemptDate);
    row.appendChild(completionDate);
    completionDate.appendChild(div);


    div.setAttribute('class',"updateDelete");

    pencil.setAttribute('type',"image");
    pencil.setAttribute("id","update");
    pencil.setAttribute('src',"../resources/pencil.png");

    bin.setAttribute('type',"image");
    bin.setAttribute("id","delete");
    bin.setAttribute('src',"../resources/bin.png");

    redbin.setAttribute('type',"image");
    redbin.setAttribute("id","deletered");
    redbin.setAttribute('src',"../resources/binred.png");

    div.appendChild(pencil);
    div.appendChild(redbin);
    div.appendChild(bin);

}


function createTable() {
    let userId = localStorage.getItem("userID");
    let url = "/userApp/user/" + userId;
    axios.get(url)
        .then(response => {
             // console.log(response)
            response.data.boulders.forEach(boulder => {
                createRow(boulder);
            })
        }).catch(err => console.error(err));
}