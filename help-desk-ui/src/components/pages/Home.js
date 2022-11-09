import React from "react";
import { Route } from "react-router-dom";
import UserInfoMolecule from "../molecules/UserInfoMolecule";
import Admin from "./Admin";
import Client from "./Client";
import Engineer from "./Engineer";

const Home = (props) => {
  const ls = require("local-storage");
  const loggedInUser = ls.get("user");

  return (
    <div className="container">
      {(() => {
        if (loggedInUser.userType === "Admin") {
          return (
            <React.Fragment>
              <UserInfoMolecule user={loggedInUser} />
              <Admin user={loggedInUser} />
            </React.Fragment>
          );
        } else if (loggedInUser.userType === "Engineer") {
          return (
            <React.Fragment>
              <UserInfoMolecule user={loggedInUser} />
              <Engineer user={loggedInUser} />
            </React.Fragment>
          );
        } else {
          console.log(loggedInUser);
          return (
            <React.Fragment>
              <UserInfoMolecule user={loggedInUser} />
              <Client user={loggedInUser} />
            </React.Fragment>
          );
        }
      })()}
    </div>
  );
};

export default Home;
