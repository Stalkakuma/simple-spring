import axios from 'axios';
import { useEffect } from 'react';
import { useState } from 'react';

export const HomePage = () => {
  const [greeting, setGreeting] = useState('');

  useEffect(() => {
    axios
      .get('http://localhost:8080/greetings')
      .then((response) => setGreeting(response.data))
      .catch((error) => console.log(error));
  }, []);

  return (
    <main>
      <h1>This is an example for a homepage. Good luck on the test!</h1>
      <h2>{greeting}</h2>
    </main>
  );
};
