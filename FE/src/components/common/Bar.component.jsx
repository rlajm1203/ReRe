import React from "react";
import styled from "styled-components";
import { MainContainer } from "../../styles/Container";
import { Icon } from "./Icon.component";
import { Link } from "react-router-dom";

const Bar = ({ text }) => {
  return (
    <div>
      <MainContainer>
        <BarArea>
          <Link to="../">
            <Icon type="back" />
          </Link>
          <Title>{text}</Title>
        </BarArea>
      </MainContainer>
    </div>
  );
};

export default Bar;

const BarArea = styled.div`
  align-items: center;
  position: relative;
  top: 50px;
  left: 0px;
  margin-bottom: 100px;
`;

const Title = styled.h2`
  text-align: center;
  font-weight: 400;
  font-size: 24px;
  margin-top: -20px;
  /* margin-left: 13px; */
`;
