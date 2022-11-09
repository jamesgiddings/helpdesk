import React from "react";

const LabelAtom = (props) => {
  const { label } = props;
  return (
    <label htmlFor="name" className="form-label mx-3">
      {label}
    </label>
  );
};

export default LabelAtom;
