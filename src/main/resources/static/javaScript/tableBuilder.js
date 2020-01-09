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

    name.innerText = boulder.name.toString();
    location.innerText = boulder.location.toString();
    grade.innerText = boulder.grade.toString();
    status.innerText = boulder.status.toString();
    if (boulder.attemptDate == null) {
        attemptDate.innerText = "Not Attempted"
    } else {
        attemptDate.innerText = boulder.attemptDate.toString().substring(0,10);
    }
    if (boulder.completionDate == null) {
        completionDate.innerText = "Not Completed"
    } else {
        completionDate.innerText = boulder.completionDate.toString().substring(0,10);
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
    axios.get("/boulderApp/boulder")
        .then(response => {
            response.data.forEach(boulder => {
                createRow(boulder);
            })
        }).catch(err => console.error(err));
}