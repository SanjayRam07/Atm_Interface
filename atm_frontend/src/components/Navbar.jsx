import React from 'react'

export default function Navbar() {

  return (
<nav className="navbar navbar-expand-lg navbar-light bg-grey">
  <div className="container-fluid">
    <a className="navbar-brand" href="/home">ATM</a>
    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span className="navbar-toggler-icon"></span>
    </button>
    <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div className="navbar-nav">
        <a className="nav-link" aria-current="page" href="/deposit">Deposit</a>
        <a className="nav-link" href="/withdraw">Withdraw</a>
        <a className="nav-link" href="/transfer">Transfer</a>
      </div>
    </div>
    <a className="nav-link" href="/">Logout</a>
  </div>
</nav>
  )
}
