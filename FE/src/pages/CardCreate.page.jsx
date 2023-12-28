import React from "react";
import Header from "../components/common/layout/Header.component.jsx";
import Bar from "../components/common/Bar.component.jsx";
import { MainContainer } from "../styles/Container.jsx";
import Button from "../components/common/Button.component.jsx";
import CardInputWithGreyBar from "../components/cardcreate/CardInput.component.jsx";
import styled from "styled-components";
import { SelectedNum } from "../components/cardcreate/AddedCard.component.jsx";
import AddedCard from "../components/cardcreate/AddedCard.component.jsx";
import { Icon, StyledIcon } from "../components/common/Icon.component.jsx";

const CardCreatePage = () => {
  return (
    <div>
      <Header />
      <Bar text="목차명 입력"></Bar>
      <MainContainer>
        <div style={{ display: "flex" }}>
          <div style={{ marginRight: "25px" }}>
            <CardInputWithGreyBar greyBarText="문제 입력" />
          </div>
          <div style={{ marginLeft: "25px" }}>
            <CardInputWithGreyBar greyBarText="정답 입력" />
          </div>
        </div>
        <ButtonContainer>
          <Button>문제 추가</Button>
        </ButtonContainer>
        <SelectedNum>
          <IconSpace>
            <Icon type="trash" />
          </IconSpace>
        </SelectedNum>
        <div style={{ display: "flex" }}>
          <AddedCard />
        </div>
      </MainContainer>
    </div>
  );
};

export default CardCreatePage;

const ButtonContainer = styled.div`
  display: flex;
  width: 180px;
  justify-content: center;
  position: relative;
  margin-top: 80px;
  margin-left: 1068px;
`;

const IconSpace = styled(StyledIcon)`
  position: absolute;
  right: 9px;
  top: 70px;
`;
