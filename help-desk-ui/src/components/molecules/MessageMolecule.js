import React from "react";
import MessageBodyAtom from "../atoms/MessageBodyAtom";
import MessageHeaderMolecule from "../molecules/MessageHeaderMolecule";

const MessageMolecule = (props) => {
  const { author, body, dateCreated } = props;
  let messageAlignment = "d-flex flex-row";
  if (author.userType === "Engineer" || author.userType === "Admin") {
    messageAlignment = "d-flex flex-row-reverse";
  }
  return (
    <div class={messageAlignment}>
      <div className="w-75 p-2">
        <MessageHeaderMolecule author={author} />
        <MessageBodyAtom body={body} dateCreated={dateCreated} />
      </div>
    </div>
  );
};

export default MessageMolecule;
