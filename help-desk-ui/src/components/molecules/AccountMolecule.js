import axios from "axios";
import PropTypes from "prop-types";
import React, { Component } from "react";
import AccountBodyMolecule from "./AccountBodyMolecule";
import AccountHeaderMolecule from "./AccountHeaderMolecule";
const ls = require("local-storage");

class AccountMolecule extends Component {
  state = {
    showInfo: ls("showInfo") === null ? false : ls("showInfo"),
  };

  onHandleClick = () => {
    this.setState({ showInfo: !this.state.showInfo });
    ls.set("showInfo", !this.state.showInfo);
  };

  deleteAccount = (id) => {
    axios
      .delete("http://localhost:8081/gateway/users/" + id)
      .then((response) => {
        window.location.reload(true);
      });
  };

  render() {
    const { showInfo } = this.state;
    return (
      <div className="card mb-1">
        <AccountHeaderMolecule
          account={this.props.account}
          onClickDelete={this.deleteAccount.bind(this, this.props.account.id)}
          onClickChevron={this.onHandleClick}
        />
        {showInfo ? <AccountBodyMolecule account={this.props.account} /> : null}
      </div>
    );
  }
}

AccountMolecule.defaultProps = {
  name: "Not Defined",
  username: "Not Defined",
  email: "Not Defined",
  phone: "0000000000",
};

AccountMolecule.propTypes = {
  name: PropTypes.string.isRequired,
  username: PropTypes.string.isRequired,
  email: PropTypes.string.isRequired,
  phone: PropTypes.string.isRequired,
};

export default AccountMolecule;
