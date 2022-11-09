import axios from "axios";
import React, { Component } from "react";
import TicketMolecule from "./TicketMolecule";

export default class UnassignedTicketMolecule extends Component {
  deleteTicket = (id) => {
    axios.delete("http://localhost:8081/gateway/users/" + id).then((res) => {
      window.location.reload(true);
    });
  };

  render() {
    const { user, ticket } = this.props;
    return (
      <TicketMolecule
        ticket={ticket}
        user={user}
        handleDeleteTicket={this.deleteTicket.bind(this, ticket.id)}
      />
    );
  }
}
