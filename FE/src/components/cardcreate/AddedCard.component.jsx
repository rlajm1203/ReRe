import React from "react";
import styled from "styled-components";
import { useState } from "react";
import { Icon, StyledIcon } from "../common/Icon.component";

const AddedCard = ({
  problem,
  answer,
  onEdit,
  onDelete,
  setCheckedCardCount,
}) => {
  const [isChecked, setIsChecked] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [newProblem, setNewProblem] = useState(problem);
  const [newAnswer, setNewAnswer] = useState(answer);

  const handleEditClick = () => {
    if (isEditing) {
      onEdit(newProblem, newAnswer);
    }
    setIsEditing(!isEditing);
    setNewProblem(problem);
    setNewAnswer(answer);
  };

  return (
    <div>
      <Container>
        <SelectBox>
          <IconSpace
            onClick={() => {
              setIsChecked(!isChecked);
              setCheckedCardCount((prev) => {
                return isChecked ? prev - 1 : prev + 1;
              });
            }}
            color={isChecked ? "#007af3" : "grey"}
          >
            <Icon type="checkbox" size={30} />
          </IconSpace>
          <button onClick={handleEditClick}>
            {isEditing ? "완료" : "수정"}
          </button>
          <div style={{ marginTop: 2, marginRight: 10, marginLeft: 10 }}>|</div>
          <button onClick={onDelete}>삭제</button>
        </SelectBox>
        {isEditing ? (
          <>
            <ProblemBox
              value={newProblem}
              onChange={(e) => setNewProblem(e.target.value)}
            />
            <AnswerBox
              value={newAnswer}
              onChange={(e) => setNewAnswer(e.target.value)}
            />
          </>
        ) : (
          <>
            <ProblemBox value={problem} disabled={true} />
            <AnswerBox value={answer} disabled={true} />
          </>
        )}
      </Container>
    </div>
  );
};

export default AddedCard;

export const SelectedNum = ({ children, cardCount, checkedCardCount }) => {
  return (
    <div>
      <SelectedBar>
        <div>
          <div className="selectedCard">{checkedCardCount}개 카드 선택 </div>
          <div>총 {cardCount}개</div>
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
    margin-right: 5px;
    margin-left: 5px;
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
  width: 584px;
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
  width: 584px;
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

const SelectBox = styled.div`
  display: flex;
  position: relative;
  top: 20px;
  width: 571px;
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
  color: ${({ color }) => color || "#007af3"};
`;
