import React from "react";
import LabelAtom from "../atoms/LabelAtom";

const EmptyMolecule = (props) => {
  const { label } = props;
  return (
    <div className="card">
      <div className="card-body">
        <LabelAtom label={label} />
      </div>
    </div>
  );
};

export default EmptyMolecule;
