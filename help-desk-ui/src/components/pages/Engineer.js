import React, { Component } from "react";
import axios from "axios";
import TicketMolecule from "../molecules/TicketMolecule";
import EmptyMolecule from "../molecules/EmptyMolecule";
import TaskDropDownHeaderMolecule from "../molecules/TaskDropDownHeaderMolecule";
const ls = require("local-storage");

class Engineer extends Component {
  state = {
    tickets: [],
    viewTickets: ls("viewTickets") === null ? false : ls("viewTickets"),
  };

  componentDidMount() {
    axios
      .get(
        "http://localhost:8081/gateway/tickets/engineer/" + this.props.user.id // get the tickets assigned to this engineer
      )
      .then((response) => this.setState({ tickets: response.data }));
  }

  deleteTicket = (id) => {
    axios.delete("http://localhost:8081/gateway/tickets/" + id);
  };

  onHandleClickViewTickets = () => {
    this.setState({ viewTickets: !this.state.viewTickets });
    ls.set("viewTickets", !this.state.viewTickets);
  };

  render() {
    const loggedInUser = ls.get("user");
    const { tickets, viewTickets } = this.state;
    return (
      <React.Fragment>
        <div className="card">
          <TaskDropDownHeaderMolecule
            label="View Your Tickets"
            onClickChevron={this.onHandleClickViewTickets}
          />
          {viewTickets ? (
            <React.Fragment>
              {tickets.length > 0 ? (
                <React.Fragment>
                  {tickets.map((ticket) => (
                    <div key={ticket.id} className="m-3">
                      <TicketMolecule
                        key={ticket.id}
                        ticket={ticket}
                        handleDeleteTicket={this.deleteTicket.bind(
                          this,
                          ticket.id
                        )}
                        user={loggedInUser}
                      />
                    </div>
                  ))}
                </React.Fragment>
              ) : (
                <EmptyMolecule label="Your currently have no tickets assigned" />
              )}
            </React.Fragment>
          ) : null}
        </div>
      </React.Fragment>
    );
  }
}
export default Engineer;
