import React, { useEffect, useState } from "react";
import Header from "../components/common/layout/Header.component.jsx";
import styled from "styled-components";
import Bar from "../components/common/Bar.component.jsx";
import "swiper/css";
import "swiper/css/effect-cards";
import "../styles/CardStyle.css";
import { cardTest } from "../components/card/CardTest.js";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";
import { cardContents } from "../service/main.js";
const CardPage = () => {
  const [flipped, setFlipped] = useState(false);
  const [cardIndex, setCardIndex] = useState(0);
  const navigate = useNavigate();
  const { cardBookId, themeId } = useParams();
  const { data, isLoading, error } = useQuery({
    queryKey: ["cards"],
    queryFn: () => cardContents(cardBookId, themeId),
    select: (data) => data?.data?.response,
  });

  const [datas, setDatas] = useState(data && data);
  console.log(datas?.length);
  useEffect(() => {
    setDatas(data);
  }, [data]);

  const handleFlip = () => {
    setFlipped(!flipped);
  };
  const handleRightAreaClick = () => {
    setCardIndex((prevIndex) => (prevIndex + 1) % datas.length);
    setFlipped(false);
  };
  const handleLeftAreaClick = () => {
    const newDatas = datas.filter((_, index) => index !== cardIndex);
    setDatas(newDatas);
    if (newDatas.length > 0) {
      if (cardIndex >= newDatas.length) {
        setCardIndex(0);
      }
    } else {
      alert("모든 학습이 끝났습니다.");
      navigate(-1);
    }
    setFlipped(false);
  };
  return (
    <div>
      <Header />
      <Bar text={data?.message}></Bar>
      {isLoading ? (
        <div>Loading...</div>
      ) : (
        <CardContainer>
          <Card
            style={{
              marginTop: 20,
              marginLeft: 65,
              width: "90%",
              height: "90%",
            }}
          ></Card>
          <Card
            style={{
              marginTop: 15,
              marginLeft: 45,
              width: "93%",
              height: "93%",
            }}
          ></Card>
          <Card
            style={{
              marginTop: 10,
              marginLeft: 25,
              width: "96%",
              height: "96%",
            }}
          ></Card>

          <Card onClick={handleFlip} flipped={flipped}>
            <CardFace className={flipped ? "card-back" : "card-front"}>
              {flipped
                ? datas && datas[cardIndex]?.answer
                : datas && datas[cardIndex]?.content}
              <CardNum>
                {cardIndex + 1}/{datas?.length}
              </CardNum>
            </CardFace>
          </Card>
        </CardContainer>
      )}
      ;
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
  width: 38%;
  height: 100%;
  z-index: 1;
`;
const LeftArea = styled.div`
  position: fixed;
  top: 20%;
  left: 0;
  width: 38%;
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
  background: ${({ flipped }) => (flipped ? "#aaa" : "#FFFFFF")};
  color: ${({ flipped }) => (flipped ? "#FFFFFF" : "#000000")};
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
  padding: 1.3rem;
  justify-content: center;
  align-items: center;
  position: absolute;
  width: 100%;
  height: 100%;
  backface-visibility: hidden;
`;

const CardNum = styled.div`
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
`;
