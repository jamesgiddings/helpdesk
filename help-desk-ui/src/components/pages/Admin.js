import React, { Component } from "react";

import axios from "axios";
import UnassignedTicketMolecule from "../molecules/UnassignedTicketMolecule";
import MultipleSelectInputMolecule from "../molecules/MultipleSelectInputMolecule";
import FormSubmitAtom from "../atoms/FormSubmitAtom";
import TaskDropDownHeaderMolecule from "../molecules/TaskDropDownHeaderMolecule";
import Accounts from "./Accounts";
import AddAccount from "../organisms/AddAccounts";
import EmptyMolecule from "../molecules/EmptyMolecule";
const ls = require("local-storage");

class Admin extends Component {
  state = {
    tickets: [],
    users: [],
    user_id: [""],
    selectedTicket: {},
    showUnassignedTickets:
      ls("showUnassignedTickets") === null
        ? false
        : ls("showUnassignedTickets"),
    showUserAccounts:
      ls("showUserAccounts") === null ? false : ls("showUserAccounts"),
    showNewUserAccount:
      ls("showNewUserAccount") === null ? false : ls("showNewUserAccount"),
  };

  componentDidMount() {
    axios
      .get("http://localhost:8081/gateway/tickets/unassigned")
      .then((response) => this.setState({ tickets: response.data }));
    axios
      .get("http://localhost:8081/gateway/users/engineers")
      .then((response) => this.setState({ users: response.data }));
  }

  onHandleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  };

  onHandleClickUnassigned = () => {
    this.setState({ showUnassignedTickets: !this.state.showUnassignedTickets });
    ls.set("showUnassignedTickets", !this.state.showUnassignedTickets);
  };

  onHandleClickUserAccounts = () => {
    this.setState({ showUserAccounts: !this.state.showUserAccounts });
    ls.set("showUserAccounts", !this.state.showUserAccounts);
  };

  onHandleClickNewUserAccount = () => {
    this.setState({ showNewUserAccount: !this.state.showNewUserAccount });
    ls.set("showNewUserAccount", !this.state.showNewUserAccount);
  };

  onHandleClick = (ticket) => {
    this.setState({ selectedTicket: ticket }); // this is the ticket that was clicked
  };

  onHandleSubmit = (e, id) => {
    e.preventDefault(); // don't behave like a form, don't refresh yourself
    const { user_id, selectedTicket } = this.state; // get the current values of each field from the state
    if (user_id === "") {
      this.setState({ errors: { username: "Engineer ID is required" } });
      return;
    }

    axios.get(
      "http://localhost:8081/gateway/tickets/engineer/" +
        user_id +
        "/addto/" +
        selectedTicket.id
    );
    window.location.reload(false);
  };

  render() {
    const {
      tickets,
      users,
      user_id,
      showUnassignedTickets,
      showUserAccounts,
      showNewUserAccount,
    } = this.state;

    return (
      <React.Fragment>
        <div className="card">
          <TaskDropDownHeaderMolecule
            label="View Unassigned Tickets"
            onClickChevron={this.onHandleClickUnassigned}
          />
          {showUnassignedTickets ? (
            <React.Fragment>
              {tickets.length > 0 ? (
                <React.Fragment>
                  {tickets.map((ticket) => (
                    <div
                      key={ticket.id}
                      className="card m-3 p-3"
                      onClick={this.onHandleClick.bind(this, ticket)}
                    >
                      <UnassignedTicketMolecule
                        key={ticket.id}
                        ticket={ticket}
                        user={this.props.user}
                      />
                      <form onSubmit={this.onHandleSubmit}>
                        <MultipleSelectInputMolecule
                          label="Engineer"
                          type="text"
                          name="user_id"
                          ticket={ticket}
                          value={user_id}
                          onChange={this.onHandleChange}
                          options={users}
                        />
                        <FormSubmitAtom
                          label="Assign Engineer"
                          onClick={this.onHandleClick.bind(this, ticket)}
                        />
                      </form>
                    </div>
                  ))}
                </React.Fragment>
              ) : (
                <EmptyMolecule label="No unassigned tickets" />
              )}
            </React.Fragment>
          ) : null}

          <TaskDropDownHeaderMolecule
            label="View User Accounts"
            onClickChevron={this.onHandleClickUserAccounts}
          />
          {showUserAccounts ? <Accounts /> : null}
          <TaskDropDownHeaderMolecule
            label="Create New User Account"
            onClickChevron={this.onHandleClickNewUserAccount}
          />
          {showNewUserAccount ? <AddAccount /> : null}
        </div>
      </React.Fragment>
    );
  }
}
export default Admin;
