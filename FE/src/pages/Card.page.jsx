import React, { useState } from "react";
import Header from "../components/common/layout/Header.component.jsx";
import styled from "styled-components";
import Bar from "../components/common/Bar.component.jsx";
import "swiper/css";
import "swiper/css/effect-cards";
import "../styles/CardStyle.css";
import { cardTest } from "../components/card/CardTest.js";
import { Link } from "react-router-dom";

const CardPage = () => {
  const [flipped, setFlipped] = useState(false); // 카드의 상태를 저장하는 변수
  const [cardIndex, setCardIndex] = useState(0); // 현재 보여주고 있는 카드의 인덱스를 저장하는 변수
  const [datas, setDatas] = useState(cardTest.data.cards); // datas를 상태 변수로 변경

  // 카드를 클릭할 때마다 flipped 상태를 토글하는 함수
  const handleFlip = () => {
    setFlipped(!flipped);
  };

  // 오른쪽 영역 클릭 이벤트 핸들러
  const handleRightAreaClick = () => {
    // if (cardIndex < datas.length - 1) {
    setCardIndex((prevIndex) => (prevIndex + 1) % datas.length); // 카드 인덱스를 1 증가
    setFlipped(false); // 카드를 뒤집어 content를 보여줌
    // } else {
    //   alert("모든 학습이 끝났습니다."); // 경고 메시지 표시
    //   <Link to="/main"></Link>;
    // }
  };

  // 왼쪽 영역 클릭 이벤트 핸들러
  const handleLeftAreaClick = () => {
    const newDatas = datas.filter((_, index) => index !== cardIndex); // 현재 카드를 제외한 데이터만 남김
    setDatas(newDatas); // datas를 업데이트
    if (newDatas.length > 0) {
      if (cardIndex >= newDatas.length) {
        setCardIndex(0); // cardIndex가 datas.length보다 크거나 같은 경우 cardIndex를 0으로 설정
      }
    } else {
      alert("모든 학습이 끝났습니다."); // 모든 카드를 삭제했다면 경고 메시지 표시
      window.location.href = "/";
    }
    setFlipped(false); // 카드를 뒤집어 content를 보여줌
  };

  return (
    <div>
      <Header />
      <Bar text="운영체제"></Bar>
      <CardContainer>
        <Card
          style={{ marginTop: 20, marginLeft: 65, width: "90%", height: "90%" }}
        ></Card>
        <Card
          style={{ marginTop: 15, marginLeft: 45, width: "93%", height: "93%" }}
        ></Card>
        <Card
          style={{ marginTop: 10, marginLeft: 25, width: "96%", height: "96%" }}
        ></Card>
        <Card onClick={handleFlip} flipped={flipped}>
          <CardFace className={flipped ? "card-back" : "card-front"}>
            {flipped ? datas[cardIndex].answer : datas[cardIndex].content}
          </CardFace>
        </Card>
      </CardContainer>
      <RightArea onClick={handleRightAreaClick} />
      <LeftArea onClick={handleLeftAreaClick} />
    </div>
  );
};

export default CardPage;

const CardContainer = styled.div`
  position: relative;
  width: 18.75rem;
  height: 26.25rem;
  margin: 0 auto;
`;

const RightArea = styled.div`
  position: fixed;
  top: 20%;
  right: 0;
  width: 38%; // 화면의 절반
  height: 100%;
  z-index: 1;
`;

const LeftArea = styled.div`
  position: fixed;
  top: 20%;
  left: 0;
  width: 38%; // 화면의 절반
  height: 100%;
  z-index: 1;
`;

const Card = styled.div`
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 1.25rem;
  position: absolute;
  top: 20px;
  left: 0;
  width: 100%;
  height: 100%;
  background: ${({ flipped }) => (flipped ? "#aaa" : "#ffffff")};
  color: ${({ flipped }) => (flipped ? "#ffffff" : "#000000")};
  border: 0.01px solid #dddddd;
  box-shadow: 0px 4px 64px rgba(0, 0, 0, 0.1);
  border-radius: 0.625rem;
  transition: transform 0.6s;
  transform-style: preserve-3d;
  transform: ${({ flipped }) =>
    flipped ? "rotateY(180deg)" : "rotateY(0deg)"};
`;

const CardFace = styled.div`
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  width: 100%;
  height: 100%;
  backface-visibility: hidden;
`;
