import React from "react";
import IconAtom from "../atoms/IconAtom";

const MessageHeaderMolecule = (props) => {
  const { author } = props;
  return (
    <React.Fragment>
      <div>
        {author.fullName}&nbsp;
        {author.userType === "Engineer" ? (
          <IconAtom icon="fa fa-wrench" />
        ) : null}
      </div>
    </React.Fragment>
  );
};

export default MessageHeaderMolecule;
