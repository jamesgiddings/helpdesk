import React from "react";
import ButtonAtom from "../atoms/ButtonAtom";
const ls = require("local-storage");

const AuthenticationMolecule = (props) => {
  const loggedInUser = ls.get("user");
  const { handleLogout } = props;
  return (
    <div className="text-light">
      Logged in as{" "}
      <span className="bolder">
        {loggedInUser.fullName}, {loggedInUser.userType}
      </span>
      &nbsp;
      <ButtonAtom label="Logout" handleLogout={handleLogout} />
    </div>
  );
};

export default AuthenticationMolecule;
