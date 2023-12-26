import React from "react";
import Header from "../components/common/layout/Header.component.jsx";
import Bar from "../components/common/Bar.component.jsx";
import { MainContainer } from "../styles/Container.jsx";

const CardCreatePage = () => {
  return (
    <div>
      <Header />
      <Bar text="목차명 입력"></Bar>
      <MainContainer></MainContainer>
    </div>
  );
};

export default CardCreatePage;
