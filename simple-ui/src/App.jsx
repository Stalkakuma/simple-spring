import { Route, Routes } from 'react-router-dom';
import { HomePage } from './Pages/HomePage/HomePage';
import { Navbar } from './Components/Navbar/Navbar';

import { UserProvider } from './Auth/User/UserContext';
import { Login } from './Auth/Login/Login';

const App = () => {
  return (
    <UserProvider>
      <Navbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<Login />} />
      </Routes>
    </UserProvider>
  );
};

export default App;
