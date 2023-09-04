import { useEffect, useState } from "react"
import { useSelector } from "react-redux";
import Navbar from "./Navbar";
import "./Home.css";

export const Home = ( {refreshData} ) => {
  const [userData, setUserData] = useState(null);
  const user = useSelector((state) => state.user.value);

  useEffect(() => {
      const fetchData = async () => {
          const signin = await fetch(`http://localhost:5000/account/signin?accNo=${user.id}&password=${user.pwd}`, {
            method:"POST"
          });
          const res = await signin.json();
          if (res) {
              const response = await fetch(`http://localhost:5000/account/getAccount?accNo=${user.id}`);
              if (response.ok) {
                  const data = await response.json();
                  setUserData(data);
                  console.log(data);
                  await sessionStorage.setItem('user', JSON.stringify(data));
                  console.log(sessionStorage.getItem('user'));
              }
          }
      };
      console.log(refreshData);

      fetchData();
  }, [user.id, user.pwd, refreshData]);

  let sessionJson = sessionStorage.getItem("user");
  let sessionValue = userData;
  try {
    sessionValue = JSON.parse(sessionJson);
  } catch (error) {
    console.error("Error parsing session data:", error);
  }

  return (
      <>
          <Navbar/>
          <hr className="m-0"></hr>
          <div className="card mb-sm-10 mt-5 ms-5 me-5 log-form home">
          {sessionValue && (
              <div>
                  <h1>Account No: {sessionValue.accNo}</h1>
                  <h1>Account Name: {sessionValue.accHolderName}</h1>
                  <h1>Account Type: {sessionValue.accType}</h1>
                  <h1>Account Balance: {sessionValue.balance}</h1>
              </div>
          )}
          </div>
      </>
  )
}
