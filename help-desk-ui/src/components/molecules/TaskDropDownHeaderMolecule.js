import React from "react";
import IconAtom from "../atoms/IconAtom";
import LabelAtom from "../atoms/LabelAtom";

const TaskDropDownHeaderMolecule = (props) => {
  const { label, onClickChevron } = props;
  return (
    <div className="card-header">
      <LabelAtom label={label} />
      <IconAtom icon="fa fa-chevron-down" onClick={onClickChevron} />
    </div>
  );
};

export default TaskDropDownHeaderMolecule;
