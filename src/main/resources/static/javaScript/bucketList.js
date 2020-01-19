let urlPre ="";

function createTable() {
    let userId = sessionStorage.getItem("userID");
    let url = urlPre + "/userApp/user/" + userId;
    axios.get(url)
        .then(response => {
            response.data.boulders.forEach(boulder => {
                createRow(boulder);
            })
        }).catch(err => console.error(err));
}

function addBoulder() {
    hideErrorMessages();
    let userId = sessionStorage.getItem("userID");
    let url = urlPre + "/userApp/user/" + userId;
    let newBoulder = submitBoulder();

    axios.get(url).then(response => {
        let JSONString = JSON.stringify(response.data);
        if (JSONString.includes("[]")) {
            JSONString = JSONString.split("[]").join("[" + JSON.stringify(newBoulder) + "]");
        } else {
            JSONString = JSONString.split("]").join("," + JSON.stringify(newBoulder) + "]");
        }
        axios.put(url, JSON.parse(JSONString));
        if (sessionStorage.getItem("success") === "true") {
            window.location = window.location;
        }
    }).catch(err => console.error(err));

}

function updateBoulder() {
    hideErrorMessages();
    let boulderID = sessionStorage.getItem("boulderID");
    let url = urlPre + "/boulderApp/boulder/" + boulderID;
    let updatedBoulder = submitBoulder();

    axios.put(url,updatedBoulder);
    if (sessionStorage.getItem("success") === "true") {
        window.location = window.location;
    }
}

function deleteBoulder() {
    let boulderId = sessionStorage.getItem("boulderID");
    let url = urlPre + "/boulderApp/boulder/" + boulderId;
    axios.delete(url).catch(err => console.error(err));
    window.location = window.location;
}

function deleteUser() {
    let userID = sessionStorage.getItem("userID");
    let url = urlPre + "/userApp/user/" + userID;
    axios.delete(url).catch(err => console.error(err));
    signOut();
}

function resetModal() {
    let attemptDate = document.getElementById("attemptDate");
    let completionDate = document.getElementById("completionDate");
    $('select').val('');
    $('input').val('');
    attemptDate.setAttribute("style","display: none");
    completionDate.setAttribute("style","display: none");
}

function addForm() {
    hideErrorMessages();
    resetModal();
    boulderAttemptDate.max = new Date().toISOString().split("T")[0];
    boulderCompletionDate.max = new Date().toISOString().split("T")[0];
    document.getElementById("submitButton").setAttribute("onclick","addBoulder();");
    let heading = document.getElementById("heading");
    heading.innerText = "Add a new boulder";
}



function updateForm() {
    document.getElementById("submitButton").setAttribute("onclick","updateBoulder();");
    hideErrorMessages();
    resetModal();
    let boulderID = sessionStorage.getItem("boulderID");
    let heading = document.getElementById("heading");
    let name = document.getElementById("name" + boulderID).innerText;
    heading.innerText = "Updating " + name;
    document.getElementById("boulderName").value = name;
    document.getElementById("boulderLocation").value = document.getElementById("location" + boulderID).innerText;
    document.getElementById("boulderGrade").value = gradeConverter(document.getElementById("grade" + boulderID).innerText);
    document.getElementById("boulderStatus").value = statusConverter(document.getElementById("status" + boulderID).innerText);
    document.getElementById("boulderAttemptDate").value = dateConverter(document.getElementById("attemptDate" + boulderID).innerText);
    document.getElementById("boulderCompletionDate").value = dateConverter(document.getElementById("completionDate" + boulderID).innerText);
    showDates();
}

function deleteForm() {
    let boulderID = sessionStorage.getItem("boulderID");
    let heading = document.getElementById("deleteHeading");
    let name = document.getElementById("name" + boulderID).innerText;
    heading.innerText = "Deleting " + name;
}


function hideErrorMessages() {
    document.getElementById("missingEntryErrorMessage").setAttribute("style","display: none");
    document.getElementById("missingAttemptDateErrorMessage").setAttribute("style","display: none");
    document.getElementById("missingDatesErrorMessage").setAttribute("style","display: none");
    document.getElementById("missingCompletionDateErrorMessage").setAttribute("style","display: none");
    document.getElementById("completionBeforeAttemptErrorMessage").setAttribute("style","display: none");
}


function submitBoulder() {
    sessionStorage.setItem("success","true");
    let boulderName = document.getElementById("boulderName");
    let boulderLocation = document.getElementById("boulderLocation");
    let boulderGrade = document.getElementById("boulderGrade");
    let boulderStatus = document.getElementById("boulderStatus");
    let boulderAttemptDate = document.getElementById("boulderAttemptDate");
    let boulderCompletionDate= document.getElementById("boulderCompletionDate");
    if ( boulderName.value === "" || boulderLocation.value === "" || boulderGrade.value === "" || boulderStatus.value === "") {
        document.getElementById("missingEntryErrorMessage").setAttribute("style","");
        sessionStorage.setItem("success","false");
    } else if ( boulderStatus.value === "1" && boulderAttemptDate.value === "" ) {
        document.getElementById("missingAttemptDateErrorMessage").setAttribute("style","");
        sessionStorage.setItem("success","false");
    } else if ( boulderStatus.value === "2" && ( boulderAttemptDate.value === "" || boulderCompletionDate.value === "" )) {
        document.getElementById("missingDatesErrorMessage").setAttribute("style","");
        sessionStorage.setItem("success","false");
    } else if ( boulderStatus.value === "2" && boulderAttemptDate.value > boulderCompletionDate.value ) {
        document.getElementById("completionBeforeAttemptErrorMessage").setAttribute("style","");
        sessionStorage.setItem("success","false");
    } else if ( boulderStatus.value === "3" && boulderCompletionDate.value === "" ) {
        document.getElementById("missingCompletionDateErrorMessage").setAttribute("style", "");
        sessionStorage.setItem("success", "false");
    } else {
        if (boulderStatus.value === "0") {
            return {
                name: boulderName.value,
                location: boulderLocation.value,
                grade: boulderGrade.value,
                status: boulderStatus.value,
                attemptDate: null,
                completionDate: null
            };
        } else if (boulderStatus.value === "1") {
            return {
                name: boulderName.value,
                location: boulderLocation.value,
                grade: boulderGrade.value,
                status: boulderStatus.value,
                attemptDate: boulderAttemptDate.value,
                completionDate: null
            };
        } else if (boulderStatus.value === "2") {
            return {
                name: boulderName.value,
                location: boulderLocation.value,
                grade: boulderGrade.value,
                status: boulderStatus.value,
                attemptDate: boulderAttemptDate.value,
                completionDate: boulderCompletionDate.value
            };
        } else if (boulderStatus.value === "3") {
            return {
                name: boulderName.value,
                location: boulderLocation.value,
                grade: boulderGrade.value,
                status: boulderStatus.value,
                attemptDate: boulderCompletionDate.value,
                completionDate: boulderCompletionDate.value
            };
        }
    }
}


function showDates() {
    let boulderStatus = document.getElementById("boulderStatus").value;
    let attemptDate = document.getElementById("attemptDate");
    let completionDate = document.getElementById("completionDate");
    attemptDate.setAttribute("style","");
    if (boulderStatus === "0" || boulderStatus === "") {
        attemptDate.setAttribute("style","display: none");
        attemptDate.value = "";
        completionDate.setAttribute("style","display: none");
        completionDate.value = "";
    } else if (boulderStatus === "1") {
        attemptDate.setAttribute("style","");
        completionDate.setAttribute("style","display: none");
        completionDate.value = "";
    } else if (boulderStatus === "2") {
        attemptDate.setAttribute("style","");
        completionDate.setAttribute("style","");
    } else if (boulderStatus === "3") {
        attemptDate.setAttribute("style","display: none");
        completionDate.setAttribute("style","");
    }
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
    name.setAttribute("id","name" + boulder.id.toString());
    location.setAttribute("id","location" + boulder.id.toString());
    grade.setAttribute("id","grade" + boulder.id.toString());
    status.setAttribute("id","status" + boulder.id.toString());
    attemptDate.setAttribute("id","attemptDate" + boulder.id.toString());
    completionDate.setAttribute("id","completionDate" + boulder.id.toString());


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
        status.setAttribute("style","background-color: #F0453B");
    } else if (status.innerText === "Attempted") {
        status.setAttribute("style"," background-color: #FB9B00");
    } else if (status.innerText === "Completed") {
        status.setAttribute("style","background-color: #6B9E3A");
    } else {
        status.setAttribute("style","background-color: #FDCECF");
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

    setAttributes(pencil,
        {"type": "image", "id": "update", "src": "../resources/pencil.png",
            "data-toggle": "modal", "data-target": "#boulderForm"});

    setAttributes(bin,
        {"type": "image", "id": "delete", "src": "../resources/bin.png",
            "data-toggle": "modal", "data-target": "#confirmDelete"});

    setAttributes(redbin,
        {"type": "image", "id": "deletered", "src": "../resources/bin.png",
            "data-toggle": "modal", "data-target": "#confirmDelete"});

    pencil.addEventListener("click",() => {
        sessionStorage.setItem("boulderID",boulder.id.toString());
        updateForm();
        boulderAttemptDate.max = new Date().toISOString().split("T")[0];
        boulderCompletionDate.max = new Date().toISOString().split("T")[0];
        });

    bin.addEventListener("click",() => {
        sessionStorage.setItem("boulderID",boulder.id.toString());
        deleteForm();
    });


    redbin.setAttribute('type',"image");
    redbin.setAttribute("id","deletered");
    redbin.setAttribute('src',"../resources/binred.png");

    div.appendChild(pencil);
    div.appendChild(redbin);
    div.appendChild(bin);
}

function setAttributes(el, attrs) {
    for (let key in attrs) {
        el.setAttribute(key, attrs[key]);
    }
}

function sortTable(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("bucketList");
    switching = true;
    dir = "asc";
    while (switching) {
        switching = false;
        rows = table.rows;
        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("td")[n];
            y = rows[i+1].getElementsByTagName("td")[n];
            if ( n === 0 || n === 1 || n === 2 ) {
                if (dir === "asc") {
                    if (x.innerText > y.innerText) {
                        shouldSwitch = true;
                        break;
                    }
                } else if (dir === "desc") {
                    if (x.innerText < y.innerText) {
                        shouldSwitch = true;
                        break;
                    }
                }
            } else if ( n ===  3 ) {
                if (dir === "asc") {
                    if (statusConverter(x.innerText) > statusConverter(y.innerText)) {
                        shouldSwitch = true;
                        break;
                    }
                } else if (dir === "desc") {
                    if (statusConverter(x.innerText) < statusConverter(y.innerText)) {
                        shouldSwitch = true;
                        break;
                    }
                }
            } else if ( n === 4 || n === 5 ) {
                if (dir === "asc") {
                    if (dateConverter(x.innerText) > dateConverter(y.innerText)) {
                        shouldSwitch = true;
                        break;
                    }
                } else if (dir === "desc") {
                    if (dateConverter(x.innerText) < dateConverter(y.innerText)) {
                        shouldSwitch = true;
                        break;
                    }
                }
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            switchcount ++;
            for ( i = 0; i < 6; i++) {
                document.getElementsByClassName("sortUp")[i].innerHTML = "&#9653;";
                document.getElementsByClassName("sortDown")[i].innerHTML = "&#9663;";
            }
            if ( dir === "asc") {
                document.getElementsByClassName("sortUp")[n].innerHTML = "&#9652;";
            } else if ( dir === "desc") {
                document.getElementsByClassName("sortDown")[n].innerHTML = "&#9662;";
            }
        } else {
            if (switchcount === 0 && dir === "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}

function showUserInfo() {
    let userId = sessionStorage.getItem("userID");
    let url = urlPre + "/userApp/user/" + userId;
    axios.get(url).then(response => {
        document.getElementById("userInfo").innerText = capitaliseString(response.data.name);
        document.getElementById("deleteUserHeading").innerText = "Deleting user " + capitaliseString(response.data.name);
        })
}

function signOut() {
    sessionStorage.setItem("userID","");
    window.location = "../index.html";
}


function gradeConverter(grade) {
    switch (grade) {
        case "5A":
            return 0;
        case "5A+":
            return 1;
        case "5B":
            return 2;
        case "5B+":
            return 3;
        case "5C":
            return 4;
        case "5C+":
            return 5;
        case "6A":
            return 6;
        case "6A+":
            return 7;
        case "6B":
            return 8;
        case "6B+":
            return 9;
        case "6C":
            return 10;
        case "6C+":
            return 11;
        case "7A":
            return 12;
        case "7A+":
            return 13;
        case "7B":
            return 14;
        case "7B+":
            return 15;
        case "7C":
            return 16;
        case "7C+":
            return 17;
        case "8A":
            return 18;
        case "8A+":
            return 19;
        case "8B":
            return 20;
        case "8B+":
            return 21;
        case "8C":
            return 22;
        case "8C+":
            return 23;

    }
}

function statusConverter(status) {
    switch (status) {
        case "Not Attempted":
            return 0;
        case "Attempted":
            return 1;
        case "Completed":
            return 2;
        case "Flashed":
            return 3;
    }
}

function dateConverter(date) {
    let splitDate = date.split("/");
    return splitDate[2] + "-" + splitDate[1] + "-" + splitDate[0];
}
