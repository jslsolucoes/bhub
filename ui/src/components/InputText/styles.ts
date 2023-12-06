import styled from 'styled-components';

export const Container = styled.input`

  border: 0;
  outline: none;
  padding: 10px;
  background-color: ${props => props.theme.secondary.background.color};
  color: ${props => props.theme.secondary.font.color};
  border-radius: 4px;
  font-weight: bold;
  line-height: 1.5;
  width: 100%;
  font-size: 1.2rem;
  
  
`;


