import React from "react";
import MessageBodyAtom from "../atoms/MessageBodyAtom";

const MessageMolecule = (props) => {
  const { author, body, dateCreated } = props;
  return (
    <React.Fragment>
      <div>{author}</div>
      <MessageBodyAtom body={body} dateCreated={dateCreated} />
    </React.Fragment>
  );
};

export default MessageMolecule;
