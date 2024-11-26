import axios from 'axios';
import { useContext, useState } from 'react';
import { useAuth, UserContext } from '../User/UserContext';

export const Login = () => {
  const User = useAuth();
  const isLoggedIn = User.userIsAuthenticated();
  const [loginBody, setLoginBody] = useState({
    username: '',
    password: '',
  });

  const handleForm = (e) => {
    setLoginBody({
      ...loginBody,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post('http://localhost:8080/auth/authenticate', loginBody).then((response) => {
      const { accessToken } = response.data;
      const data = parseJwt(accessToken);
      const authenticatedUser = { data, accessToken };

      User.userLogin(authenticatedUser);
    });
  };

  return (
    <main>
      <h1>{isLoggedIn ? 'You are logged in' : 'Login Screen'}</h1>
      <form onSubmit={handleSubmit}>
        <label>Username: </label>
        <input type="text" name="username" onChange={handleForm} />
        <label>Password: </label>
        <input type="password" name="password" onChange={handleForm} />
        <button type="submit">Login</button>
      </form>

      {isLoggedIn && (
        <section>
          <div>Your email is:</div>
          <span> {User.user?.data.email}</span>
        </section>
      )}
    </main>
  );
};
const parseJwt = (token) => {
  if (!token) {
    return;
  }
  const base64Url = token.split('.')[1];
  const base64 = base64Url.replace('-', '+').replace('_', '/');
  return JSON.parse(window.atob(base64));
};