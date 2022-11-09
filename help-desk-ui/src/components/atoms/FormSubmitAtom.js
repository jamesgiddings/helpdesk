import React from "react";

const FormSubmitAtom = (props) => {
  const { label } = props;
  return (
    <div>
      <button type="submit" className="btn btn-primary m-3 float-end">
        {label}
      </button>
    </div>
  );
};

export default FormSubmitAtom;
