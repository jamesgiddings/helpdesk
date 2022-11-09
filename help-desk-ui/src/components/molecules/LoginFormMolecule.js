import React from "react";

const LoginFormMolecule = (props) => {
  const { handleSubmit, username, setUsername, password, setPassword } = props;
  return (
    <form className="login-form p-3" onSubmit={handleSubmit}>
      <div className="h1">
        <h1>Login</h1>
      </div>
      <label className="login-label-username my-2" htmlFor="username">
        Username:{" "}
      </label>
      <input
        className="loginInput"
        type="text"
        value={username}
        placeholder="Enter Username"
        onChange={({ target }) => setUsername(target.value)}
      />

      <label className="login-label-password my-2" htmlFor="password">
        Password:{" "}
      </label>
      <input
        className="loginInput"
        type="password"
        value={password}
        placeholder="Enter Password"
        onChange={({ target }) => setPassword(target.value)}
      />

      <button className="logoutButton my-3" type="submit">
        Login
      </button>
    </form>
  );
};

export default LoginFormMolecule;
