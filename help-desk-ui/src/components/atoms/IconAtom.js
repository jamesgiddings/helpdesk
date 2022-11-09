import React from "react";

const IconAtom = (props) => {
  const { className, icon, onClick } = props;
  return (
    <i
      className={icon + " " + className}
      aria-hidden="true"
      onClick={onClick}
    ></i>
  );
};

export default IconAtom;
