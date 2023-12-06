import {createGlobalStyle} from 'styled-components';

export default createGlobalStyle`

  *,
  *::before,
  *::after {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
  }

  body {
    font-family: 'Roboto', Helvetica, Arial, sans-serif;
    font-size: 1.2rem;
    font-weight: 400;
    line-height: 1.5;
    color: ${props => props.theme.font.color}
  }

  html, body, #root {
    height: 100vh;
    width: 100vw;
    background-color: ${props => props.theme.background.color};
  }

  //form styles
  .form {
    display: flex;
    flex-direction: column;
    margin: auto;
    padding: 10px;
  }

  .form .horizontal {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-around;
  }

  .form .horizontal .form-field {
    flex: 1;
    margin-right: 0.5rem;
    margin-left: 0.5rem;
  }

  .form-field {
    margin-bottom: 1rem;
  }

  .form-field label {
    display: block;
    margin-bottom: 0.5rem;
  }


  //sizes styles
  .w-100 {
    width: 100%;
  }

`;