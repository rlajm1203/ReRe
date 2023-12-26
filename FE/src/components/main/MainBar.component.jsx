import React from "react";
import styled from "styled-components";
import { MainContainer } from "../../styles/Container";

const MainBar = ({ text }) => {
  return (
    <div>
      <MainContainer>
        <BarArea>
          <Title>· {text}</Title>
        </BarArea>
      </MainContainer>
    </div>
  );
};

export default MainBar;

const BarArea = styled.div`
  display: flex;
  align-items: center;
  position: relative;
  top: 50px;
  left: 0px;
`;

const Title = styled.h2`
  text-align: center;
  font-weight: 400;
  font-size: 24px;
  margin-top: 3px;
`;
