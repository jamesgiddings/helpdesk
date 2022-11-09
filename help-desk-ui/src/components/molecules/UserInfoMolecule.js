import React from "react";

const UserInfoMolecule = (props) => {
  const { user } = props;
  return (
    <div className="card my-3">
      <div className="card-header">Name: {user.fullName}</div>
      <ul className="list-group list-group-flush">
        <li className="list-group-item">User Type: {user.userType}</li>
        {user.userType === "Engineer" ? (
          <li className="list-group-item">Specialism: {user.specialism}</li>
        ) : null}
      </ul>
    </div>
  );
};

export default UserInfoMolecule;
