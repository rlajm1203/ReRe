import React from "react";
import Header from "../components/common/layout/Header.component.jsx";
import styled from "styled-components";
import Bar from "../components/common/Bar.component.jsx";

const CardPage = () => {
  return (
    <div>
      <Header />
      <Bar text="운영체제"></Bar>
      <Card>카드</Card>
    </div>
  );
};

export default CardPage;

const Card = styled.button`
  box-sizing: border-box;

  position: relative;
  width: 300px;
  height: 420px;
  top: 115px;
  left: calc(50% - 150px);
  background: #ffffff;
  border: 0.01px solid #dddddd;
  box-shadow: 0px 4px 64px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
`;
