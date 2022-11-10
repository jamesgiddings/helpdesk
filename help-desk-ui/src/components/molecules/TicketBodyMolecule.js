import React from "react";
import IconAtom from "../atoms/IconAtom";
import LabelAtom from "../atoms/LabelAtom";
import AddCommentMolecule from "./AddCommentMolecule";
import MarkResolvedMolecule from "./MarkResolvedMolecule";
import MessageMolecule from "./MessageMolecule";
const ls = require("local-storage");

const TicketBodyMolecule = (props) => {
  const { title, ticket, id, messages, user } = props;
  const loggedInUser = ls.get("user");
  return (
    <div className="card-body">
      <ul className="list-group">
        <li className="list-group-item">
          <IconAtom icon="fa fa-archive" /> &nbsp;
          <LabelAtom label="ID" />
          <LabelAtom label={id} />
        </li>
        <li className="list-group-item">
          <IconAtom icon="fa fa-exclamation-triangle" /> &nbsp;
          <LabelAtom label="Description of issue: " />
          <LabelAtom label={title} />
        </li>
        <li className="list-group-item">
          <IconAtom icon="fa fa-tasks" /> &nbsp;
          <LabelAtom label="Status" />
          <LabelAtom label={ticket.resolved ? "Resolved" : "Unresolved"} />
        </li>
      </ul>
      <div className="m-3">
        {messages.map((message) => (
          <MessageMolecule
            key={message.id}
            author={message.user}
            body={message.body}
            message={message}
            id={message.id}
            dateCreated={message.dateCreated}
          />
        ))}
      </div>
      {ticket.resolved ? null : (
        <div className="grid-container">
          <div className="m-3">
            <div className="grid-item">
              <AddCommentMolecule user={user} ticketId={id} />
            </div>
            <div>
              {loggedInUser.userType === "Client" ||
              loggedInUser.userType === "Admin" ? (
                <MarkResolvedMolecule ticketId={id} />
              ) : null}
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default TicketBodyMolecule;
