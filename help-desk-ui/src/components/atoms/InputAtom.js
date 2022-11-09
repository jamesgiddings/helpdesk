import React from "react";
import classnames from "classnames";

const InputAtom = (props) => {
  const { name, value, placeholder, type, onChange, errors } = props;
  return (
    <React.Fragment>
      <input
        type={type}
        value={value}
        name={name}
        onChange={onChange}
        placeholder={placeholder}
        className={classnames("form-control form-control-lg", {
          "is-invalid": errors,
        })}
        errors={errors}
      />
      {errors && (
        <div className="invalid-feedback alert alert-danger">{errors}</div>
      )}
    </React.Fragment>
  );
};

export default InputAtom;
