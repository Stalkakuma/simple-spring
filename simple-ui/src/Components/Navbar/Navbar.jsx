import { Link } from 'react-router-dom';

export const Navbar = () => {
  return (
    <nav>
      <li>
        <Link to={'/'}>Home</Link>
      </li>
      <li>
        <Link to={'/login'}>Login Page</Link>
      </li>
    </nav>
  );
};
