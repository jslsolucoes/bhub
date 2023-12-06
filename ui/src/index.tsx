import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import GlobalStyle from './styles/global';
import reportWebVitals from './reportWebVitals';
import {ThemeProvider} from "styled-components";
import light from "./styles/theme/light";
import {BrowserRouter} from "react-router-dom";
import {Provider} from 'react-redux'
import {store} from "./state/store/store";

const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);
root.render(
    <React.StrictMode>
        <BrowserRouter>
            <ThemeProvider theme={light}>
                <GlobalStyle/>
                <Provider store={store}>
                    <App/>
                </Provider>
            </ThemeProvider>
        </BrowserRouter>
    </React.StrictMode>
);
reportWebVitals();
