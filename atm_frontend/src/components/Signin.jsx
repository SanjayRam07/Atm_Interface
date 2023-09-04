import { useState } from "react";
import { useDispatch } from "react-redux";
import { NavLink } from "react-router-dom"
import {signin} from '../redux/features/user'
import "./Signin.css"

export const Signin = () => {
    const [id, setId] = useState("");
    const [password, setPassword] = useState("");

    const dispatch = useDispatch();

    return (
        <div className="">
    <div className="d-inline-flex justify-content-center align-content-center log-form">
        <form className="container row justify-content-center align-items-center g-3" onSubmit="return false;" method="post">
            <div className="col-md-6">
                <label for="AccountNo" className="form-label">Account Number</label>
                <input type="text" className="form-control border border-secondary" id="AccountNo" onChange={(event) => {setId(event.target.value)}}/>
            </div>
            <span></span>
            <div className="col-md-6">
                <label for="Password" className="form-label">Password</label>
                <input type="password" className="form-control border border-secondary" id="password" onChange = {(event) => {setPassword(event.target.value)}}/>
            </div>
            <span></span>
            <span></span>
            <div class="row col-3 gx-2">
                <div class="col-1">    
                    <input className="form-check-input border border-secondary" type="checkbox" id="gridCheck"/> 
                </div>
                <div class="col">    
                    <label className="form-check-label terms " for="gridCheck"><a href="#/"> Accept terms & conditions</a> </label>
                </div>
            </div>
            <span></span>
            <div className="row g-3 col-3">
                <NavLink className="btn btn-primary" to='/home' onClick={() => dispatch(signin({id:id, pwd:password})) } type="submit">
Sign In 
                </NavLink>
                <NavLink to="/signup" type="submit" className="btn btn-secondary">Sign Up</NavLink>
            </div>
        </form>
    </div>
    </div>
    )
}
