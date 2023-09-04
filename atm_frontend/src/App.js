import { Signin } from './components/Signin';
import './App.css';
import { Signup } from './components/Signup';
import {
  BrowserRouter,
  Route,
  Router,
  Routes,
} from "react-router-dom";
import { Home } from './components/Home';
import Withdraw from './components/Withdraw';
import Transfer from './components/Transfer';
import Deposit from './components/Deposit';
import { useState } from 'react';

function App() {
  const [refreshData, setRefreshData] = useState(false);
  const handleRefreshData = (newRefreshData) => {
    setRefreshData(newRefreshData);
  }

  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Signin/>}/>
          <Route path='/signin' element={<Signin/>}/>
          <Route path='/signup' element={<Signup/>}/>
          <Route path='/home' element={<Home refreshData={refreshData}/>}/>
          <Route path='/withdraw' element={<Withdraw refreshData={refreshData} handleRefreshData={handleRefreshData} />}/>
          <Route path='/deposit' element={<Deposit refreshData={refreshData} handleRefreshData={handleRefreshData} />}/>
          <Route path='/transfer' element={<Transfer refreshData={refreshData} handleRefreshData={handleRefreshData} />}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
