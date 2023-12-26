import React from "react";
import styled from "styled-components";
import { MainContainer } from "../../styles/Container";
import { Icon } from "./Icon.component";

const Bar = ({ text }) => {
  return (
    <div>
      <MainContainer>
        <BarArea>
          <Icon type="back" />
          <Title>{text}</Title>
        </BarArea>
      </MainContainer>
    </div>
  );
};

export default Bar;

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
  margin-left: 13px;
`;
