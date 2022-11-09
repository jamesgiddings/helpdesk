import React from "react";

const MessageBodyAtom = (props) => {
  const { body, dateCreated } = props;
  const date = dateCreated.substring(0, 10);
  const time = dateCreated.substring(11, 16);

  return (
    <div className="card m-3">
      <div className="card-body mx-3">{body}</div>
      <div className="card-footer">{date + " " + time}</div>
    </div>
  );
};

export default MessageBodyAtom;
