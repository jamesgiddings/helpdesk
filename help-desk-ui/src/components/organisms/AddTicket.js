import axios from "axios";
import React, { Component } from "react";
import TextAreaAtom from "../atoms/TextAreaAtom";
class AddTicket extends Component {
  state = {
    clientId: "",
    engineerId: "",
    title: "",
    errors: {},
    messages: [],
  };

  onHandleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  };

  onHandleSubmit = (e) => {
    e.preventDefault();
    console.log("entered onHandleSubmit");
    const { title } = this.state;
    const { user } = this.props;
    if (title === "") {
      this.setState({ errors: { title: "Title is required" } });
      return;
    }
    const newTicket = {
      clientId: user.id,
      messages: [],
      title,
      resolved: false,
    };
    axios
      .post("http://localhost:8081/gateway/tickets", newTicket)
      .then((res) => {
        this.setState({ messages: "", clientId: "" });
        window.location.reload(false);
      });
  };

  render() {
    const { title, errors } = this.state;
    return (
      <div>
        <div className="card">
          <div className="card-header">
            Would you like to create a new ticket?
            <div className="card-body">
              <form onSubmit={this.onHandleSubmit}>
                <TextAreaAtom
                  submitLabel="Create Ticket"
                  placeholder="Please describe the issue in a few words"
                  label="Issue"
                  name="title"
                  value={title}
                  errors={errors.title}
                  onChange={this.onHandleChange}
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default AddTicket;
