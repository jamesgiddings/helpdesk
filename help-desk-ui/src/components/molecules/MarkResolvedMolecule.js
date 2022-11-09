import React from "react";
import FormSubmitAtom from "../atoms/FormSubmitAtom";
import axios from "axios";

const MarkResolvedMolecule = (props) => {
  function handleSubmit() {
    axios
      .get(
        "http://localhost:8081/gateway/tickets/resolve/" + props.ticketId // get the tickets assigned to this engineer
      )
      .then(() => {
        window.location.reload(true);
      });
  }
  return (
    <form onSubmit={handleSubmit}>
      <FormSubmitAtom label="Close Ticket" />
    </form>
  );
};

export default MarkResolvedMolecule;
