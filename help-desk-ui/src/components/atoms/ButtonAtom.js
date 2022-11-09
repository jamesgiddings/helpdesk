import React from "react";

const ButtonAtom = (props) => {
  const { label, handleLogout } = props;
  return (
    <button className="btn btn-outline-info" onClick={handleLogout}>
      {label}
    </button>
  );
};

export default ButtonAtom;
