import React from "react";
import Header from "../components/common/layout/Header.component.jsx";
import { MainContainer } from "../styles/Container.jsx";
import Bar from "../components/common/Bar.component.jsx";
import styled from "styled-components";
import BlueBox from "../components/index/BlueBox.component.jsx";
import NewBlueBox from "../components/index/NewBlueBox.component.jsx";

const IndexPage = () => {
  return (
    <div>
      <Header />
      <MainContainer>
        <Bar text="정보 처리 산업 기사 실기"></Bar>
        <BlueBox></BlueBox>
        <NewBlueBox></NewBlueBox>
      </MainContainer>
    </div>
  );
};

export default IndexPage;
