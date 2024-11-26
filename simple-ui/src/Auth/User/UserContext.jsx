import { createContext, useContext, useEffect, useState } from 'react';

export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem('user'));
    setUser(storedUser);
  }, []);

  const getUser = () => {
    return JSON.parse(localStorage.getItem('user'));
  };

  const userIsAuthenticated = () => {
    let storedUser = localStorage.getItem('user');
    if (!storedUser) {
      return false;
    }
    storedUser = JSON.parse(storedUser);
    if (Date.now() > storedUser.data.exp * 1000) {
      userLogout();
      return false;
    }
    return true;
  };

  const userLogin = (user) => {
    localStorage.setItem('user', JSON.stringify(user));
    setUser(user);
  };

  const userLogout = () => {
    localStorage.removeItem('user');
    setUser(null);
  };

  const contextValue = {
    user,
    getUser,
    userIsAuthenticated,
    userLogin,
    userLogout,
  };

  return <UserContext.Provider value={contextValue}>{children}</UserContext.Provider>;
};
export const useAuth = () => {
  return useContext(UserContext);
};
