import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import React, { useState, useEffect } from "react";
import Header from "./components/Header";
import Admin from "./components/pages/Admin";
import PageNotFound from "./components/pages/PageNotFound";
import Tickets from "./components/organisms/Tickets";
import AddTicket from "./components/organisms/AddTicket";
import Engineer from "./components/pages/Engineer";
import Accounts from "./components/pages/Accounts";
import Client from "./components/pages/Client";
import AddAccounts from "./components/organisms/AddAccounts";
import axios from "axios";
import Home from "./components/pages/Home";
import LoginFormMolecule from "./components/molecules/LoginFormMolecule";
import { USER_TYPES } from "./constant";

const App = () => {
  const ls = require("local-storage");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [user, setUser] = useState();

  useEffect(() => {
    const loggedInUser = localStorage.getItem("user");

    if (loggedInUser) {
      const foundUser = ls.get("user");
      setUser(foundUser);
    }
  }, [ls]);

  const handleLogout = () => {
    setUser({});
    setUsername("");
    setPassword("");
    ls.clear();
    window.location.reload(false);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const user = { username, password };
    const response = await axios.get(
      "http://localhost:8081/gateway/users/authenticate/" +
        user.username +
        "/" +
        user.password
    );
    setUser(response.data);
    ls.set("user", response.data);
  };

  if (user) {
    return (
      <div>
        <Router>
          <div className="container">
            <Header handleLogout={handleLogout} />
            <Routes>
              <Route index element={<Home user={user} />} />
              <Route path="*" element={<PageNotFound />} />
              {user.userType === USER_TYPES.ADMIN ? (
                <React.Fragment>
                  <Route path="/tickets" element={<Tickets />} />
                  <Route path="/admin" element={<Admin />} />
                  <Route path="/admin/accounts" element={<Accounts />} />
                  <Route path="/admin/newaccounts" element={<AddAccounts />} />
                </React.Fragment>
              ) : null}
              {user.userType === USER_TYPES.ENGINEER ? (
                <React.Fragment>
                  <Route path="/engineer" element={<Engineer />} />
                </React.Fragment>
              ) : null}
              {user.userType === USER_TYPES.CLIENT ? (
                <React.Fragment>
                  <Route path="/client" element={<Client />} />
                  <Route path="/client/NewTicket" element={<AddTicket />} />
                </React.Fragment>
              ) : null}
            </Routes>
          </div>
        </Router>
      </div>
    );
  }

  return (
    <LoginFormMolecule
      handleSubmit={handleSubmit}
      setUsername={setUsername}
      setPassword={setPassword}
      password={password}
      username={username}
    />
  );
};
export default App;
