import React, { Component } from "react";
import PropTypes from "prop-types";
import TicketHeaderMolecule from "./TicketHeaderMolecule";
import TicketBodyMolecule from "./TicketBodyMolecule";
const ls = require("local-storage");

class TicketMolecule extends Component {
  state = {
    showInfo:
      ls("isOpen" + this.props.ticket.id) === null
        ? false
        : ls("isOpen" + this.props.ticket.id),
  };

  onHandleClick = () => {
    this.setState({ showInfo: !this.state.showInfo });
    ls.set("isOpen" + this.props.ticket.id, !this.state.showInfo);
  };

  render() {
    const { showInfo } = this.state;
    const { id, title, dateCreated, messages } = this.props.ticket;
    const { handleDeleteTicket } = this.props;
    const date = dateCreated.substring(0, 10);
    const time = dateCreated.substring(11, 16);
    return (
      <div className="card mb-1">
        <TicketHeaderMolecule
          dateCreated={date + " " + time}
          ticket={this.props.ticket}
          onClickDelete={handleDeleteTicket}
          onClickChevron={this.onHandleClick}
        />
        {showInfo ? (
          <TicketBodyMolecule
            id={id}
            title={title}
            ticket={this.props.ticket}
            messages={messages}
            user={this.props.user}
          />
        ) : null}
      </div>
    );
  }
}

TicketMolecule.defaultProps = {
  name: "Not Defined",
  username: "Not Defined",
  email: "Not Defined",
  phone: "0000000000",
};

TicketMolecule.propTypes = {
  name: PropTypes.string.isRequired,
  username: PropTypes.string.isRequired,
  email: PropTypes.string.isRequired,
  phone: PropTypes.string.isRequired,
};

export default TicketMolecule;
