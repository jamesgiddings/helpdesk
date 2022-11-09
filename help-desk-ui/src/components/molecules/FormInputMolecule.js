import React from "react";
import LabelAtom from "../atoms/LabelAtom";
import InputAtom from "../atoms/InputAtom";

const FormInputMolecule = (props) => {
  const { name, value, placeholder, type, onChange, errors, label } = props;
  return (
    <div className="m-3">
      <LabelAtom label={label} />
      <InputAtom
        type={type}
        name={name}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
        errors={errors}
      />
    </div>
  );
};

export default FormInputMolecule;
