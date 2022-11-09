import React from "react";

const MultipleSelectOptionAtom = (props) => {
  const { option } = props;
  return (
    <option value={option.id}>
      {option.fullName}, {option.specialism}
    </option>
  );
};

export default MultipleSelectOptionAtom;
