import React from "react";
import { Link } from "react-router-dom";
import AuthenticationMolecule from "./molecules/AuthenticationMolecule";

const Header = (props) => {
  const { handleLogout } = props;
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark mb-1">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/">
          Helpdesk
        </Link>
        <AuthenticationMolecule handleLogout={handleLogout} />
      </div>
    </nav>
  );
};

export default Header;
