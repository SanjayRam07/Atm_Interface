import { useState } from "react"
import { NavLink } from "react-router-dom";

export const Signup = () => {
    const [accName, setAccName] = useState("")
    const [password, setPassword] = useState("");
    const [accType, setAccType] = useState("");

        const signup = async () => {
            await fetch("http://localhost:5000/account/create",{
                method: "POST",
                body: JSON.stringify({"accHolderName": accName,"accType": accType,"balance": 0,"password": password}),
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {console.log(response)})
            .catch(error => {console.log(error)});
        }

    return (
        <div class="d-inline-flex justify-content-center align-content-center log-form">
        <form class="container row justify-content-center align-items-center g-3">
            <div class="col-md-6">
                <label for="AccHolderName" class="form-label">Account Holder Name</label>
                <input type="text" class="form-control" id="AccHolderName" onChange={(event) => {setAccName(event.target.value)}}/>
            </div>
            <span></span>
            <div class="col-md-6">
                <label for="Password" class="form-label">Password</label>
                <input type="password" class="form-control" id="Password" onChange={(event) => {setPassword(event.target.value)}}/>
            </div>
            <span></span>
            <div class=" row-2 col-2 justify-content-center align-content-center">
                <label class="form-label" type="text">Account Type</label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" value="savings" id="savings" name="accType" checked={accType==='savings'} onChange={(e)=>{setAccType(e.target.value)}}/>
                    <label class="form-check-label" for="savings">
                        Savings
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" value="current" id="current" name="accType" checked={accType==='current'} onChange={(e)=>{setAccType(e.target.value)}}/>
                    <label class="form-check-label" for="current">
                        Current
                    </label>
                </div>
            </div>
            <span></span>
            <div class="row col-3 gx-2">
                <div class="col-1">    
                    <input class="form-check-input" type="checkbox" id="gridCheck"/> 
                </div>
                <div class="col">    
                    <label class="form-check-label " for="gridCheck"><a href="#/"> Accept terms & conditions</a> </label>
                </div>
            </div>
            <span></span>
            <div className="row g-3 col-3">
                <NavLink className="btn btn-primary" to='/signin' onClick={() => signup() } type="submit">
Sign Up 
                </NavLink>
                <NavLink to="/signin" type="submit" className="btn btn-secondary">Sign In</NavLink>
            </div>
        </form>
    </div>
    )
}