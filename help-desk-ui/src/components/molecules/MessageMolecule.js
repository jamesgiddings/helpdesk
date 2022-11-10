import React from "react";
import MessageBodyAtom from "../atoms/MessageBodyAtom";
import MessageHeaderMolecule from "../molecules/MessageHeaderMolecule";
import { USER_TYPES } from "../../constant";

const MessageMolecule = (props) => {
  const { author, body, dateCreated } = props;
  let messageAlignment = "d-flex flex-row";
  if (
    author.userType === USER_TYPES.ENGINEER ||
    author.userType === USER_TYPES.ADMIN
  ) {
    messageAlignment = "d-flex flex-row-reverse";
  }
  return (
    <div className={messageAlignment}>
      <div className="w-75 p-2">
        <MessageHeaderMolecule author={author} />
        <MessageBodyAtom body={body} dateCreated={dateCreated} />
      </div>
    </div>
  );
};

export default MessageMolecule;
