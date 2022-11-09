import React, { Component } from "react";
import axios from "axios";
import TicketMolecule from "../molecules/TicketMolecule";
const ls = require("local-storage");

class Tickets extends Component {
  state = {
    tickets: [],
  };

  componentDidMount() {
    axios
      .get("http://localhost:8081/gateway/tickets")
      .then((response) => this.setState({ tickets: response.data }));
  }

  deleteTicket = (id) => {
    axios.delete("http://localhost:8081/gateway/tickets/" + id).then((res) => {
      window.location.reload(true);
    });
  };

  render() {
    const { tickets } = this.state;
    const loggedInUser = ls.get("user");
    return (
      <React.Fragment>
        {tickets.map((ticket) => (
          <TicketMolecule
            key={ticket.id}
            ticket={ticket}
            handleDeleteTicket={this.deleteTicket.bind(this, ticket.id)}
            user={loggedInUser}
          />
        ))}
      </React.Fragment>
    );
  }
}
export default Tickets;
