import React from "react";
import styled from "styled-components";
import { useState } from "react";
import { Icon, StyledIcon } from "../common/Icon.component";

const AddedCard = () => {
  const [value, setValue] = useState();
  const textLimit = () => {
    if (value.length > 100) {
      alert("100자 이내로 입력해주세요.");
    }
  };

  return (
    <div>
      <Container>
        <SelectBox>
          <IconSpace>
            <Icon type="checkbox" size={30} />
          </IconSpace>
          <button>수정 </button>
          <div style={{ marginTop: 2, marginRight: 10, marginLeft: 10 }}>|</div>
          <button>삭제</button>
        </SelectBox>
        <ProblemBox
          value={value}
          onChange={(e) => {
            setValue(e.target.value);
            textLimit();
          }}
        />
        <AnswerBox />
      </Container>
    </div>
  );
};

export default AddedCard;

export const SelectedNum = ({ children }) => {
  return (
    <div>
      <SelectedBar>
        <div>
          <div className="selectedCard">2개 카드 선택 </div>
          <div>총 6개</div>
        </div>
        <div>{children}</div>
      </SelectedBar>
    </div>
  );
};

const SelectedBar = styled.div`
  display: flex;
  padding-left: 20px;
  align-items: center;
  height: 50px;
  background-color: #f9f9f9;
  border: 1px solid #bbb;
  margin-top: 100px;
  margin-bottom: 20px;
  justify-content: space-between;
  & div:first-child {
    display: flex;
  }
  & div:last-child {
    margin-right: 20px;
  }
  & .selectedCard::after {
    content: "/";
  }
`;

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

const ProblemBox = styled.textarea`
  display: flex;
  position: relative;
  top: 20px;
  width: 580px;
  height: 130px;
  border: 1px solid #bbb;

  resize: none;
  overflow: hidden;

  padding-left: 15px;
  font-size: 16px;
  color: #666;
  outline: none;
  &::placeholder {
    color: #999;
  }
`;

const AnswerBox = styled.textarea`
  display: flex;
  position: relative;
  width: 580px;
  height: 130px;

  resize: none;
  overflow: hidden;

  border: 1px solid #bbb;
  margin-bottom: 30px;
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

const IconSpace = styled(StyledIcon)`
  position: absolute;
  left: 10px;
  font-size: 30px;
  height: 30px;
  color: #007af3;
`;
