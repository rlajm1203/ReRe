import React from "react";
import styled from "styled-components";

const AddedCard = () => {
  return (
    <div>
      <Container>
        <SelectBox>
          <button>수정 </button>
          <div style={{ marginRight: 10, marginLeft: 10 }}> | </div>
          <button>삭제</button>
        </SelectBox>
        <ProblemBox />
        <AnswerBox />
      </Container>
    </div>
  );
};

export default AddedCard;

export const SelectedNum = () => {
  return (
    <div>
      <SelectedBar>
        <div style={{ marginRight: 8 }}>2개 카드 선택 </div>
        <div> / 총 6개</div>
      </SelectedBar>
    </div>
  );
};

const SelectedBar = styled.div`
  display: flex;
  padding-left: 20px;
  align-items: center;
  width: 1230px;
  height: 50px;
  background-color: #f9f9f9;
  border: 1px solid #bbb;
  margin-top: 120px;
  margin-bottom: 20px;
`;

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

const ProblemBox = styled.input`
  display: flex;
  position: relative;
  top: 20px;
  width: 580px;
  height: 130px;
  border: 1px solid #bbb;

  padding-left: 15px;
  font-size: 16px;
  color: #666;
  outline: none;
  &::placeholder {
    color: #999;
  }
`;

const AnswerBox = styled.input`
  display: flex;
  position: relative;
  width: 580px;
  height: 130px;
  border: 1px solid #bbb;
  margin-bottom: 100px;
  padding-left: 15px;
  font-size: 16px;
  color: #666;
  background-color: #e1f0ff;
  outline: none;
  &::placeholder {
    color: #999;
  }
`;

const SelectBox = styled.label`
  display: flex;
  position: relative;
  top: 20px;
  width: 567px;
  height: 40px;
  justify-content: flex-end;
  align-items: center;
  padding-right: 15px;
  padding-left: 15px;
  border: 1px solid #bbb;
  font-size: 16px;
  color: #666;
  outline: none;
  background-color: #fff;
`;
