import React from "react";
import { Link } from "react-router-dom";

const NavLinkAtom = (props) => {
  const { href, text } = props;
  return (
    <Link className="nav-link text-light" aria-current="page" to={href}>
      {text}
    </Link>
  );
};

export default NavLinkAtom;
