import React from "react";
import MultipleSelectOptionAtom from "../atoms/MultipleSelectOptionAtom";

const MultipleSelectInputMolecule = (props) => {
  const { label, options, onChange, name } = props;
  return (
    <div className="form-group">
      <label htmlFor="multipleSelectInputMolecule">{label}</label>
      <select
        onChange={onChange}
        multiple
        name={name}
        className="form-control"
        id="multipleSelectInputMolecule"
      >
        {options.map((engineer) => (
          <MultipleSelectOptionAtom key={engineer.id} option={engineer} />
        ))}
      </select>
    </div>
  );
};

export default MultipleSelectInputMolecule;
