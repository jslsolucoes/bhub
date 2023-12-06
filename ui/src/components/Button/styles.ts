import styled from 'styled-components';

export const Container = styled.button`

  background-color: ${props => props.theme.primary.background.color};
  color: ${props => props.theme.primary.font.color};
  border: 1px solid ${props => props.theme.primary.background.color};
  border-radius: 4px;
  padding: 10px;
  cursor: pointer;
  font-size: 1.2rem;
`;


