import React, { Component } from "react";
import AccountMolecule from "../molecules/AccountMolecule";
import axios from "axios";
import EmptyMolecule from "../molecules/EmptyMolecule";
class Accounts extends Component {
  state = {
    accounts: [],
  };

  componentDidMount() {
    axios
      .get("http://localhost:8081/gateway/users")
      .then((response) => this.setState({ accounts: response.data }));
  }

  render() {
    const { accounts } = this.state;
    return (
      <React.Fragment>
        {accounts.length > 0 ? ( // if there are accounts, show them
          <React.Fragment>
            {accounts.map((account) => (
              <div key={account.id} className="m-3">
                <AccountMolecule key={account.id} account={account} />
              </div>
            ))}
          </React.Fragment>
        ) : (
          <EmptyMolecule label="No accounts to show" /> // if there are no accounts, show this message
        )}
      </React.Fragment>
    );
  }
}

export default Accounts;
