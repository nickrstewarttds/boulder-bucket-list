function makeUsersTable() {
    axios.get("/BoulderBucketList/userApp/user")
        .then(response => {
            response.data.forEach(user => {
                let table = document.getElementById('names');
                let tr = document.createElement('tr');
                let td = document.createElement('td');
                tr.setAttribute('id',user.id);
                tr.setAttribute('class',"nameRows");
                tr.addEventListener('click', () => {
                    sessionStorage.setItem("userID",user.id);
                    window.location = "html/bucketList.html";
                });
                let name = user.name.toString();
                td.innerText = name.charAt(0).toUpperCase() + name.substring(1).toLowerCase();
                table.appendChild(tr);
                tr.appendChild(td);
            });
        }).catch(err => console.error(err));
}

function addUser() {
    let name = document.getElementById("userName");
    if ( name.value === "" || name.value.length > 20) {
        document.getElementById("invalidNameErrorMessage").setAttribute("style","");
    } else {
        let user = {
            "name": name.value,
            "boulders": []
        }
        axios.post("/BoulderBucketList/userApp/user", user);
        window.location = window.location;
    }
}

function resetAddModal() {
    let name = document.getElementById("userName");
    document.getElementById("invalidNameErrorMessage").setAttribute("style", "display: none");
    name.value = "";
}
