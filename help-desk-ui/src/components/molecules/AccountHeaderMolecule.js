import React from "react";
import IconAtom from "../atoms/IconAtom";
import LabelAtom from "../atoms/LabelAtom";

const AccountHeaderMolecule = (props) => {
  const { account, onClickDelete, onClickChevron } = props;
  return (
    <div className="card-header">
      <LabelAtom label={account.fullName} />
      <IconAtom icon="fa fa-chevron-down" onClick={onClickChevron} />
      <div className="float-end">
        <IconAtom icon="fa fa-trash" onClick={onClickDelete} />
      </div>
    </div>
  );
};

export default AccountHeaderMolecule;
