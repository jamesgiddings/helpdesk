import React from "react";
import IconAtom from "../atoms/IconAtom";
import * as Constants from "../constant";

const MessageHeaderMolecule = (props) => {
  const { author } = props;
  return (
    <React.Fragment>
      <div>
        {author.fullName}&nbsp;
        {author.userType === "Engineer" ? (
          <IconAtom icon={Constants.ENGINEER_ICON} />
        ) : null}
        {author.userType === "Admin" ? (
          <IconAtom icon={Constants.ADMIN_ICON} />
        ) : null}
      </div>
    </React.Fragment>
  );
};

export default MessageHeaderMolecule;
