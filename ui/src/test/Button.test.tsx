import React from 'react';
import {render, screen} from '@testing-library/react';
import Button from '../components/Button';
import {withThemeProvider} from "./theme";


test('renders a button', () => {
    const onClick = jest.fn();
    render(withThemeProvider(<Button text="button2" onClick={onClick}/>));
    const buttonElement = screen.getByText("button2")
    expect(buttonElement).toBeInTheDocument();
    expect(buttonElement).not.toBeDisabled();
    expect(onClick).not.toHaveBeenCalled();
});