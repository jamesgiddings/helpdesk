import axios from "axios";
import FormInputMolecule from "../molecules/FormInputMolecule";
import React, { Component } from "react";
import FormSubmitAtom from "../atoms/FormSubmitAtom";
import { USER_TYPES } from "../../constant";
class AddAccount extends Component {
  state = {
    userType: "",
    username: "",
    email: "",
    fullName: "",
    password: "",
    specialism: "",
    errors: {},
    messages: [],
  };

  onHandleChange = (e) => {
    console.log([e.target.name]);
    this.setState({ [e.target.name]: e.target.value });
  };

  onHandleSubmit = (e) => {
    e.preventDefault();
    console.log("entered onHandleSubmit");
    const { userType, username, email, fullName, password, specialism } =
      this.state;
    if (username === "") {
      this.setState({ errors: { username: "Username is required" } });
      return;
    }
    if (email === "") {
      this.setState({ errors: { email: "Email is required" } });
      return;
    }
    if (fullName === "") {
      this.setState({ errors: { fullName: "Fullname is required" } });
      return;
    }

    if (password === "") {
      this.setState({ errors: { password: "Password is required" } });
      return;
    }
    if (userType === "none") {
      this.setState({ errors: { specialism: "User Type is required" } });
      return;
    }

    if (userType === USER_TYPES.ENGINEER && specialism === "") {
      this.setState({
        errors: { specialism: "Specialism is required for Engineers" },
      });
      return;
    }
    console.log("passed validation");

    let newAccount = {};
    if (userType === USER_TYPES.ENGINEER) {
      newAccount = {
        username,
        email,
        fullName,
        password,
        userType,
        specialism,
      };
    } else {
      newAccount = {
        userType,
        username,
        email,
        fullName,
        password,
      };
    }
    if (userType === USER_TYPES.ADMIN) {
      axios
        .post("http://localhost:8081/gateway/users/admin", newAccount)
        .then((res) => {
          this.setState({
            messages: "",
            clientId: "",
            userType: "",
            username: "",
            email: "",
            fullName: "",
            password: "",
            specialism: "",
          });
          window.location.reload(true);
        });
    } else if (userType === USER_TYPES.CLIENT) {
      axios
        .post("http://localhost:8081/gateway/users/client", newAccount)
        .then((res) => {
          this.setState({
            messages: "",
            clientId: "",
            userType: "",
            username: "",
            email: "",
            fullName: "",
            password: "",
            specialism: "",
          });
          window.location.reload(true);
        });
    } else if (userType === USER_TYPES.ENGINEER) {
      axios
        .post("http://localhost:8081/gateway/users/engineer", newAccount)
        .then((res) => {
          this.setState({
            messages: "",
            clientId: "",
            userType: "",
            username: "",
            email: "",
            fullName: "",
            password: "",
            specialism: "",
          });
          window.location.reload(true);
        });
    } else {
      this.setState({
        messages: "",
        clientId: "",
        userType: "",
        username: "",
        email: "",
        fullName: "",
        password: "",
        specialism: "",
      });
      window.location.reload(false);
    }
  };

  render() {
    const {
      userType,
      username,
      email,
      fullName,
      password,
      specialism,
      errors,
    } = this.state;
    return (
      <div>
        <div className="card">
          <div className="card-header">
            <div className="card-body">
              <form onSubmit={this.onHandleSubmit}>
                <FormInputMolecule
                  label="Name"
                  type="fullName"
                  name="fullName"
                  value={fullName}
                  onChange={this.onHandleChange}
                  placeholder="Please type fullname"
                  errors={errors.fullName}
                />
                <FormInputMolecule
                  label="Username"
                  type="username"
                  name="username"
                  value={username}
                  onChange={this.onHandleChange}
                  placeholder="Please type username"
                  errors={errors.username}
                />
                <FormInputMolecule
                  label="Email"
                  type="email"
                  name="email"
                  value={email}
                  onChange={this.onHandleChange}
                  placeholder="Please type the email"
                  errors={errors.email}
                />
                <FormInputMolecule
                  label="Create Password"
                  type="password"
                  name="password"
                  value={password}
                  onChange={this.onHandleChange}
                  placeholder="Please type a strong password"
                  errors={errors.password}
                />

                <div className="form-group m-3">
                  <label
                    className="form-label mx-3"
                    htmlFor="multipleSelectInputMolecule"
                  >
                    User Type
                  </label>
                  <select
                    name="userType"
                    value={userType}
                    className="form-control"
                    onChange={this.onHandleChange}
                  >
                    <option value={USER_TYPES.ADMIN}>USER_TYPES.ADMIN</option>
                    <option value={USER_TYPES.CLIENT}>USER_TYPES.CLIENT</option>
                    <option value={USER_TYPES.ENGINEER}>
                      USER_TYPES.ENGINEER
                    </option>
                  </select>
                </div>

                {userType === USER_TYPES.ENGINEER ? (
                  <FormInputMolecule
                    label="Specialism"
                    type="text"
                    name="specialism"
                    value={specialism}
                    onChange={this.onHandleChange}
                    placeholder=""
                    errors={errors.specialism}
                  />
                ) : null}

                <FormSubmitAtom label="Add Account" />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default AddAccount;
