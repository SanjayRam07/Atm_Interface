import React, { useState } from 'react'
import { NavLink } from "react-router-dom"
import Navbar from './Navbar';

export default function Transfer( { refreshData, handleRefreshData } ) {

  const [amt, setAmt] = useState("");
  const [AccTo, setAccTo] = useState("");
  const [password, setPassword] = useState("");

  const sessionJson = sessionStorage.getItem('user');
  const sessionValue = JSON.parse(sessionJson);  

  const transact = async () => {
    console.log(sessionValue.accNo);
    const res = await fetch("http://localhost:5000/transaction/transfer",{
        method:"POST",
        body: JSON.stringify({"accFrom":sessionValue.accNo, "accTo":AccTo, "amt":amt, "password":password}),
        headers: {
            "Content-Type":"application/json"
        }
    });
    if (res.ok) {
        const newBalance = sessionValue.balance - parseFloat(amt);
        const updatedUserData = {
          ...sessionValue,
          balance: newBalance,
        };
        sessionStorage.setItem('user', JSON.stringify(updatedUserData));
        handleRefreshData(!refreshData);
      }
  }

  return (
    <>
        <Navbar/>
        <hr className="m-0"></hr>
        <div className="d-inline-flex justify-content-center align-content-center log-form">
            <form className="container row justify-content-center align-items-center g-3" onSubmit="return false;" method="post">
                <div className="col-md-6">
                    <label for="AccTo" className="form-label">Account Number</label>
                    <input type="text" className="form-control border border-secondary" id="AccTo" onChange={(event) => {setAccTo(event.target.value)}}/>
                </div>
                <span></span>
                <div className="col-md-6">
                    <label for="Amount" className="form-label">Amount</label>
                    <input type="text" className="form-control border border-secondary" id="Amount" onChange={(event) => {setAmt(event.target.value)}}/>
                </div>
                <span></span>
                <div className="col-md-6">
                    <label for="Password" className="form-label">Password</label>
                    <input type="password" className="form-control border border-secondary" id="password" onChange = {(event) => {setPassword(event.target.value)}}/>
                </div>
                <span></span>
                <div className="row col-3 gx-2">
                    <div className="col-1">    
                        <input className="form-check-input border border-secondary" type="checkbox" id="gridCheck"/> 
                    </div>
                    <div className="col">    
                        <label className="form-check-label" for="gridCheck">not a robot</label>
                    </div>
                </div>
                <span></span>
                <div className="row col-3 g-3">
                    <NavLink to='/home' onClick={() => transact()} type="submit" className="btn btn-primary">Transfer</NavLink>
                </div>
            </form>
        </div>
    </>
  )
}
