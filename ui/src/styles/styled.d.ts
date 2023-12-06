import 'styled-components';

declare module 'styled-components' {
    export interface DefaultTheme {
        background: {
            color: string;
        },
        primary: {
            background: {
                color: string;
            },
            font: {
                color: string;
            }
        },
        secondary: {
            background: {
                color: string;
            },
            font: {
                color: string;
            }
        }
        font: {
            color: string;
        }
    }
}