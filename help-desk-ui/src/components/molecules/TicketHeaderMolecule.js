import React from "react";
import IconAtom from "../atoms/IconAtom";
import LabelAtom from "../atoms/LabelAtom";

const TicketHeaderMolecule = (props) => {
  const { dateCreated, ticket, onClickDelete, onClickChevron } = props;
  const cardColor = ticket.resolved
    ? "card-header bg-secondary"
    : "card-header";
  return (
    <div className={cardColor}>
      <LabelAtom label={"Issue Number: " + ticket.id} />
      <LabelAtom label={dateCreated} />
      <IconAtom icon="fa fa-chevron-down" onClick={onClickChevron} />
      <div className="float-end">
        <IconAtom icon="fa fa-trash" onClick={onClickDelete} />
      </div>
    </div>
  );
};

export default TicketHeaderMolecule;
