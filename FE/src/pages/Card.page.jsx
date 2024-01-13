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
  display: flex;
  padding: 1.25rem;
  position: relative;
  width: 18.75rem;
  height: 26.25rem;
  top: 1.5625rem;
  left: calc(50% - 9.375rem);
  background: #ffffff;
  border: 0.01px solid #dddddd;
  box-shadow: 0px 4px 64px rgba(0, 0, 0, 0.1);
  border-radius: 0.625rem;
`;
