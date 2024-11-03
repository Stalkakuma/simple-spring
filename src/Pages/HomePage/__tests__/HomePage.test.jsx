import { render } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import { HomePage } from '../HomePage';

const renderWithRouter = (component) => {
  return {
    ...render(<BrowserRouter>{component}</BrowserRouter>),
  };
};

test('should render homepage', () => {
  renderWithRouter(<HomePage />);
  const { container } = renderWithRouter(<HomePage />);
  expect(container.innerHTML).toMatch('This is an example for a homepage. Good luck on the test!');
});
