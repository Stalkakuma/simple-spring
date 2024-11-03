import { Route, Routes } from 'react-router-dom';
import { HomePage } from './Pages/HomePage/HomePage';
import { Navbar } from './Components/Navbar/Navbar';
//TODO Rename or Delete during test
import { TempPage } from './Pages/TempPage/TempPage';

const App = () => {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/temp" element={<TempPage />} />
      </Routes>
    </>
  );
};

export default App;
