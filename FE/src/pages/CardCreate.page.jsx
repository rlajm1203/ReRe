import React from "react";
import Header from "../components/common/layout/Header.component.jsx";
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
      <MainContainer>
        <div style={{ display: "flex" }}>
          <Input type="text" placeholder="목차명 입력" />
          <SaveButton>저장</SaveButton>
        </div>
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
        <div
          style={{
            display: "flex",
            flexWrap: "wrap",
            justifyContent: "space-between",
          }}
        >
          <AddedCard />
          <AddedCard />
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
  margin-top: 40px;
  margin-left: 1068px;
`;

const IconSpace = styled(StyledIcon)`
  color: #007af3;
`;

const Input = styled.input`
  width: 1145px;
  height: 35px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-top: 40px;
  padding-left: 12px;
  font-size: 18px;
  font-weight: 400;
  color: #333;
  outline: none;
`;

const SaveButton = styled(Button)`
  width: 80px;
  height: 39px;
  font-weight: 400;
  border-radius: 5px;
  background-color: grey;
  align-items: center;
`;
