import React from "react";
import Header from "../components/common/layout/Header.component.jsx";
import { MainContainer } from "../styles/Container.jsx";
import Bar from "../components/common/Bar.component.jsx";
import styled from "styled-components";

const IndexPage = () => {
  return (
    <div>
      <Header />
      <MainContainer>
        <Bar text="정보 처리 산업 기사 실기"></Bar>
        <BlueBox>
          <Img src="macos-original-icon.png"></Img>
          <Img src="1.png"></Img>
          <Img src="2.png"></Img>
          <Img src="3.png"></Img>
          <Img src="4.png"></Img>
        </BlueBox>
      </MainContainer>
    </div>
  );
};

export default IndexPage;

const BlueBox = styled.div`
  width: 100%;
  height: 230px;
  border-radius: 10px;
  border: 1px solid #007af3;
`;

const Img = styled.img`
  width: 90px;
`;
