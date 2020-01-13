function makeUsersTable() {
    axios.get("/userApp/user")
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
