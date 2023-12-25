import React from "react";
import Header from "../components/common/layout/Header.component.jsx";
import styled from "styled-components";

const CardPage = () => {
  return (
    <div>
      <Header />
      <Card>카드</Card>
    </div>
  );
};

export default CardPage;

const Card = styled.button`
  box-sizing: border-box;

  position: relative;
  width: 333px;
  height: 465px;
  top: 165px;
  left: calc(50% - 166.5px);
  background: #ffffff;
  border: 0.01px solid #dddddd;
  box-shadow: 0px 4px 64px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
`;
