function resetModal() {
    let attemptDate = document.getElementById("attemptDate");
    let completionDate = document.getElementById("completionDate");
    $('select').val('');
    $('input').val('');
    attemptDate.setAttribute("style","display: none");
    completionDate.setAttribute("style","display: none");
}

function addForm() {
    document.getElementById("missingEntryErrorMessage").setAttribute("style","display: none");
    document.getElementById("missingAttemptDateErrorMessage").setAttribute("style","display: none");
    document.getElementById("missingDatesErrorMessage").setAttribute("style","display: none");
    document.getElementById("missingCompletionDateErrorMessage").setAttribute("style","display: none");
    document.getElementById("completionBeforeAttemptErrorMessage").setAttribute("style","display: none");
    let heading = document.getElementById("heading");
    heading.innerText = "Add a new boulder";
    resetModal();
}

function addBoulder() {
    let userId = sessionStorage.getItem("userID");
    let url = "http://3.11.159.169:8181/BoulderBucketListAdd/userApp/user/" + userId;
    let boulderName = document.getElementById("boulderName");
    let boulderLocation = document.getElementById("boulderLocation");
    let boulderGrade = document.getElementById("boulderGrade");
    let boulderStatus = document.getElementById("boulderStatus");
    let boulderAttemptDate = document.getElementById("boulderAttemptDate");
    let boulderCompletionDate= document.getElementById("boulderCompletionDate");
    if ( boulderName.value === "" || boulderLocation.value === "" || boulderGrade.value === "" || boulderStatus.value === "") {
        document.getElementById("missingEntryErrorMessage").setAttribute("style","");
    } else if ( boulderStatus.value === "1" && boulderAttemptDate.value === "" ) {
        document.getElementById("missingAttemptDateErrorMessage").setAttribute("style","");
    } else if ( boulderStatus.value === "2" && ( boulderAttemptDate.value === "" || boulderCompletionDate.value === "" )) {
        document.getElementById("missingDatesErrorMessage").setAttribute("style","");
    } else if ( boulderStatus.value === "2" && boulderAttemptDate.value > boulderCompletionDate.value ) {
        document.getElementById("completionBeforeAttemptErrorMessage").setAttribute("style","");
    } else if ( boulderStatus.value === "3" && boulderCompletionDate.value === "" ) {
        document.getElementById("missingCompletionDateErrorMessage").setAttribute("style","");
    } else {
            let newBoulder = {
                name: boulderName.value,
                location: boulderLocation.value,
                grade: boulderGrade.value,
                status: boulderStatus.value,
                attemptDate: boulderAttemptDate.value,
                completionDate: boulderCompletionDate.value
            };

            axios.get(url).then(response => {
                let newJSONString = JSON.stringify(response.data).split("]").join("," + JSON.stringify(newBoulder) + "]");
                axios.put(url, JSON.parse(newJSONString));
                window.location = window.location;
            }).catch(err => console.error(err));
        }
}


function showDates() {
    let boulderStatus = document.getElementById("boulderStatus").value;
    let attemptDate = document.getElementById("attemptDate");
    let completionDate = document.getElementById("completionDate");
    attemptDate.setAttribute("style","");
    if (boulderStatus === "0" || boulderStatus === "") {
        attemptDate.setAttribute("style","display: none");
        completionDate.setAttribute("style","display: none");
    } else if (boulderStatus === "1") {
        attemptDate.setAttribute("style","");
        completionDate.setAttribute("style","display: none");
    } else if (boulderStatus === "2") {
        attemptDate.setAttribute("style","");
        completionDate.setAttribute("style","");
    } else if (boulderStatus === "3") {
        attemptDate.setAttribute("style","display: none");
        completionDate.setAttribute("style","");
    }
}

function deleteBoulder(boulderId) {
    let url = "http://3.11.159.169:8181/BoulderBucketListAdd/boulderApp/boulder/" + boulderId;
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
    let url = "http://3.11.159.169:8181/BoulderBucketListAdd/userApp/user/" + userId;
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
