import React from "react";
import {Container} from "./styles";


type InputTextProps = {
    id: string;
    name: string;
    placeholder?: string;
    onKeyUp?: (name: string, value: string, key: string) => void;
}

const InputText: React.FC<InputTextProps> = ({
                                                 id,
                                                 name,
                                                 placeholder,
                                                 onKeyUp = () => {
                                                     // do nothing
                                                 }
                                             }) => {

    const handleOnKeyUp = (event: React.KeyboardEvent<HTMLInputElement>) => {
        const {key, target} = event;
        const {name, value} = target as HTMLInputElement;
        onKeyUp(name, value, key);
    }

    return (
        <Container id={id} name={name} placeholder={placeholder}
                   onKeyUp={handleOnKeyUp}>

        </Container>
    )
}

export default InputText;