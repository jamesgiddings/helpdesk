import React from "react";
import IconAtom from "../atoms/IconAtom";
import LabelAtom from "../atoms/LabelAtom";

const AccountBodyMolecule = (props) => {
  const { account } = props;
  return (
    <div className="card-body">
      <ul className="list-group">
        <li className="list-group-item">
          <IconAtom icon="fa fa-id-card" /> &nbsp;
          <LabelAtom label="Full Name: " />
          <LabelAtom label={account.fullName} />
        </li>
        <li className="list-group-item">
          <IconAtom icon="fa fa-user" /> &nbsp;
          <LabelAtom label="Username: " />
          <LabelAtom label={account.username} />
        </li>
        <li className="list-group-item">
          <IconAtom icon="fa fa-envelope" /> &nbsp;
          <LabelAtom label="Email: " />
          <LabelAtom label={account.email} />
        </li>
      </ul>
    </div>
  );
};

export default AccountBodyMolecule;
