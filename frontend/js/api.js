const USER_API = "http://localhost:8081/users"
const APPLICATION_API = "http://localhost:8080/applications"
const COMPANY_API = "http://localhost:8082/companies"

/* protect dashboard */

if(window.location.pathname.includes("dashboard.html")){

const userId = localStorage.getItem("userId")

if(!userId){
window.location.href="login.html"
}

}

/* LOGIN */

function login(){

const email = document.getElementById("email").value
const password = document.getElementById("password").value

fetch(USER_API + "/login",{
method:"POST",
headers:{ "Content-Type":"application/json"},
body: JSON.stringify({email,password})
})
.then(res => {

if(!res.ok){
throw new Error("Login failed")
}

return res.json()

})
.then(user => {

localStorage.setItem("userId",user.userId)

window.location.href="dashboard.html"

})
.catch(()=> alert("Invalid email or password"))

}

/* REGISTER */

function register(){

    const name = document.getElementById("name").value.trim()
    const email = document.getElementById("email").value.trim()
    const password = document.getElementById("password").value.trim()
    
    if(name === "" || email === "" || password === ""){
    alert("Please fill in all fields")
    return
    }
    
    fetch(USER_API,{
    method:"POST",
    headers:{ "Content-Type":"application/json"},
    body: JSON.stringify({name,email,password})
    })
    .then(res => {
    
    if(!res.ok){
    throw new Error("Registration failed")
    }
    
    return res.json()
    
    })
    .then(()=>{
    
    alert("Account created successfully")
    window.location.href="login.html"
    
    })
    .catch(()=>{
    alert("Error creating account")
    })
    
    }

/* LOAD APPLICATIONS */

function loadApplications(){

const userId = localStorage.getItem("userId")

fetch(APPLICATION_API + "/user/" + userId)
.then(res=>res.json())
.then(apps=>{

const table = document.getElementById("appsTable")

apps.forEach(app=>{

fetch(COMPANY_API + "/" + app.companyId)
.then(res=>res.json())
.then(company=>{

let row = table.insertRow()

row.insertCell(0).innerText = company.compName
row.insertCell(1).innerText = app.position
row.insertCell(2).innerText = app.status

let btn = document.createElement("button")
btn.innerText="Delete"

btn.onclick=()=>deleteApplication(app.appId)

row.insertCell(3).appendChild(btn)

})

})

})

}

/* DELETE APPLICATION */

function deleteApplication(id){

fetch(APPLICATION_API + "/" + id,{
method:"DELETE"
})
.then(()=>location.reload())

}

/* SEARCH */

function searchApplications(){

const input = document.getElementById("search").value.toLowerCase()

const rows = document.querySelectorAll("#appsTable tr")

rows.forEach((row,i)=>{

if(i===0) return

const text=row.innerText.toLowerCase()

row.style.display=text.includes(input)?"":"none"

})

}

/* CREATE APPLICATION */

function createApplication(){

const companyName=document.getElementById("companyName").value
const industry=document.getElementById("industry").value
const location=document.getElementById("location").value
const website=document.getElementById("website").value

const position=document.getElementById("position").value
const status=document.getElementById("status").value

fetch(COMPANY_API,{
method:"POST",
headers:{ "Content-Type":"application/json"},
body: JSON.stringify({
compName:companyName,
industry,
location,
website
})
})
.then(res=>res.json())
.then(company=>{

return fetch(APPLICATION_API,{
method:"POST",
headers:{ "Content-Type":"application/json"},
body: JSON.stringify({
userId:localStorage.getItem("userId"),
companyId:company.compId,
position,
status,
dateApplied:new Date().toISOString().split("T")[0]
})
})

})
.then(()=>window.location.href="dashboard.html")

}

/* LOGOUT */

function logout(){

localStorage.removeItem("userId")

window.location.href="login.html"

}