import React from "react";
import {Container} from "./styles";


type ButtonProps = {
    id?: string;
    text: string;
    onClick: () => void;
    className?: string;
}

const Button: React.FC<ButtonProps> = ({
                                           id,
                                           text,
                                           className,
                                           onClick = () => {
                                               //do nothing
                                           }
                                       }) => {
    const handleOnClick = (_: React.MouseEvent<HTMLButtonElement>) => {
        onClick();
    }

    return (
        <Container id={id} type="button" className={className} onClick={handleOnClick}>
            {text}
        </Container>
    )
}

export default Button;