import axios from "axios";
import React, { Component } from "react";
import LabelAtom from "../atoms/LabelAtom";
import TextAreaAtom from "../atoms/TextAreaAtom";

class AddCommentMolecule extends Component {
  state = { body: "", errors: {} };

  onHandleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  };

  onHandleSubmit = (e) => {
    e.preventDefault();
    const { body } = this.state;
    if (body === "") {
      this.setState({ errors: { clientId: "Please enter some text" } });
      return;
    }
    const newMessage = {
      body,
      user: this.props.user,
    };
    axios
      .put(
        "http://localhost:8081/gateway/tickets/addMessage/" +
          this.props.ticketId,
        newMessage
      )
      .then((res) => {
        this.setState({ body: "", clientId: "" });
        window.location.reload(false);
      });
  };

  render() {
    const { fullName } = this.props.user;
    return (
      <div className="container">
        <form onSubmit={this.onHandleSubmit}>
          <LabelAtom label={fullName} />
          <TextAreaAtom
            onChange={this.onHandleChange}
            placeholder="Please enter your reply here..."
            submitLabel="Add Reply"
            name="body"
            value={this.state.body}
          />
        </form>
      </div>
    );
  }
}

export default AddCommentMolecule;
