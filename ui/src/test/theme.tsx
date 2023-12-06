import {ThemeProvider} from "styled-components";
import light from "../styles/theme/light";

export function withThemeProvider(children: React.ReactNode) {
    return (
        <ThemeProvider theme={light}>
            {children}
        </ThemeProvider>
    )
}