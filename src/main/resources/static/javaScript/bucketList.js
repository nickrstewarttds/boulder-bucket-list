function updateForm() {
    let data = document.getElementById("boulderStatus");
    let boulderStatus = data.options[data.selectedIndex].value;
    let attemptDate = document.getElementById("attemptDate");
    let completionDate = document.getElementById("completionDate");
    attemptDate.setAttribute("style","");
    if (boulderStatus === "1") {
        attemptDate.setAttribute("style","display: none");
        completionDate.setAttribute("style","display: none");
    } else if (boulderStatus === "2") {
        attemptDate.setAttribute("style","");
        completionDate.setAttribute("style","display: none");
    } else if (boulderStatus === "3") {
        attemptDate.setAttribute("style","");
        completionDate.setAttribute("style","");
    } else if (boulderStatus === "4") {
        attemptDate.setAttribute("style","display: none");
        completionDate.setAttribute("style","");
    }
}

function deleteBoulder(boulderId) {
    let url = "/boulderApp/boulder/" + boulderId;
    axios.delete(url).catch(err => console.error(err));
}

function capitaliseString(str) {
    return str.replace(/\w\S*/g, function(word) { return word.charAt(0).toUpperCase() + word.substring(1).toLowerCase() });
}

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

    name.innerText = capitaliseString(boulder.name.toString());
    location.innerText = capitaliseString(boulder.location.toString());
    status.innerText = capitaliseString(boulder.status.toString().split("_").join(" "));

    let unformattedGrade = boulder.grade.toString();
    if (unformattedGrade.includes("P")) {
        grade.innerText = unformattedGrade.substring(1,3).toUpperCase() + "+";
    } else {
        grade.innerText = unformattedGrade.substring(1,3).toUpperCase();
    }

    if (status.innerText === "Not Attempted") {
        status.setAttribute("id","notAttempted");
    } else if (status.innerText === "Attempted") {
        status.setAttribute("id","attempted");
    } else if (status.innerText === "Completed") {
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
    bin.addEventListener("click", () => { deleteBoulder(boulder.id.toString());
    window.location = window.location;
    });

    redbin.setAttribute('type',"image");
    redbin.setAttribute("id","deletered");
    redbin.setAttribute('src',"../resources/binred.png");

    div.appendChild(pencil);
    div.appendChild(redbin);
    div.appendChild(bin);

}


function createTable() {
    let userId = sessionStorage.getItem("userID");
    let url = "/userApp/user/" + userId;
    axios.get(url)
        .then(response => {
             // console.log(response)
            response.data.boulders.forEach(boulder => {
                createRow(boulder);
            })
        }).catch(err => console.error(err));
}

function signOut() {
    sessionStorage.setItem("userID","");
    window.location = "../index.html";
}
