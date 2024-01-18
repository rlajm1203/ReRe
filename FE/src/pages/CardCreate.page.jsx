import React from "react";
import { useState } from "react";
import Header from "../components/common/layout/Header.component.jsx";
import { MainContainer } from "../styles/Container.jsx";
import Button from "../components/common/Button.component.jsx";
import CardInputWithGreyBar from "../components/cardcreate/CardInput.component.jsx";
import styled from "styled-components";
import { SelectedNum } from "../components/cardcreate/AddedCard.component.jsx";
import AddedCard from "../components/cardcreate/AddedCard.component.jsx";
import { Icon, StyledIcon } from "../components/common/Icon.component.jsx";

const CardCreatePage = () => {
  const [title, setTitle] = useState("");
  const [isEditable, setIsEditable] = useState(true);
  const [cardCount, setCardCount] = useState(0);
  const [checkedCardCount, setCheckedCardCount] = useState(0);
  console.log(checkedCardCount);
  const handleInputChange = (e) => {
    setTitle(e.target.value);
  };

  const handleButtonClick = () => {
    setIsEditable(!isEditable);
  };

  const handleEditCard = (index, newProblem, newAnswer) => {
    setCards(
      cards.map((card, i) =>
        i === index ? { problem: newProblem, answer: newAnswer } : card
      )
    );
  };

  const handleDeleteCard = (index) => {
    setCards(cards.filter((_, i) => i !== index));
    setCardCount(cardCount - 1);
  };

  const [problem, setProblem] = useState("");
  const [answer, setAnswer] = useState("");
  const [cards, setCards] = useState([]);

  const handleAddCard = () => {
    setCards([...cards, { problem, answer }]);
    setCardCount(cardCount + 1);
  };

  return (
    <div>
      <Header />
      <MainContainer>
        <div style={{ display: "flex" }}>
          <Input
            type="text"
            placeholder="목차명 입력"
            value={title}
            onChange={handleInputChange}
            disabled={!isEditable}
          />
        </div>
        <div style={{ display: "flex" }}>
          <div style={{ marginRight: "2rem" }}>
            <CardInputWithGreyBar
              greyBarText="문제 입력"
              value={problem}
              onChange={(e) => setProblem(e.target.value)}
            />
          </div>
          <div style={{ marginLeft: "1.1rem" }}>
            <CardInputWithGreyBar
              greyBarText="정답 입력"
              value={answer}
              onChange={(e) => setAnswer(e.target.value)}
            />
          </div>
        </div>
        <ButtonContainer>
          <Button onClick={handleAddCard}>문제 추가</Button>
        </ButtonContainer>

        <SaveButton onClick={handleAddCard}>전체 저장</SaveButton>

        <SelectedNum cardCount={cardCount} checkedCardCount={checkedCardCount}>
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
          {cards.map((card, index) => (
            <AddedCard
              key={index}
              problem={card.problem}
              answer={card.answer}
              onEdit={(newProblem, newAnswer) =>
                handleEditCard(index, newProblem, newAnswer)
              }
              onDelete={() => handleDeleteCard(index)}
              setCheckedCardCount={setCheckedCardCount}
            />
          ))}
        </div>
      </MainContainer>
    </div>
  );
};

export default CardCreatePage;

const ButtonContainer = styled.div`
  display: flex;
  width: 10.6rem;
  justify-content: center;
  position: relative;
  margin-top: 2.5rem;
  margin-left: 67.9rem;
`;

const IconSpace = styled(StyledIcon)`
  color: #007af3;
`;

const Input = styled.input`
  width: 77.4rem;
  height: 2.1875rem;
  border: 0.0625rem solid #ccc;
  border-radius: 0.3125rem;
  margin-top: 2.5rem;
  padding-left: 0.75rem;
  font-size: 1.125rem;
  font-weight: 400;
  color: #333;
  outline: none;
`;

const SaveButton = styled(Button)`
  width: 10.6rem;
  height: 3.2375rem;
  font-weight: 600;
  border-radius: 0.3125rem;
  background-color: grey;
  align-items: center;
  display: flex;
  justify-content: center;
  position: relative;
  margin-top: -3.3rem;
  margin-left: 56.2rem;
`;
