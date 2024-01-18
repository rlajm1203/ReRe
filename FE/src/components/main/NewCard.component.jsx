import React from "react";
import styled from "styled-components";
import { Icon } from "../common/Icon.component";
import { useState } from "react";
import { mainPageCardbooks } from "./MainTest.js";

const NewCard = () => {
  const [cardbookName, setCardbookName] = useState("");

  const handleChange = (e) => {
    setCardbookName(e.target.value);
  };

  const handleClick = () => {
    const newCardbook = {
      cardbookId: Date.now(), // 고유한 ID를 생성하기 위해 현재 시간을 사용합니다.
      cardbookName: cardbookName,
      writer: "관리자",
      createDate: new Date().toISOString().split("T")[0], // 현재 날짜를 ISO 8601 형식으로 포맷합니다.
    };

    const updatedCardbooks = [
      ...mainPageCardbooks.response.originCardbooks,
      newCardbook,
    ];

    const updatedResponse = {
      ...mainPageCardbooks.response,
      originCardbooks: updatedCardbooks,
    };

    const updatedMainPageCardbooks = {
      ...mainPageCardbooks,
      response: updatedResponse,
    };

    console.log(updatedMainPageCardbooks);
    // 여기서는 콘솔에 출력하였지만, 실제로는 서버로 데이터를 전송하거나 상태를 업데이트하는 등의 작업을 수행해야 합니다.
  };

  return (
    <NewCardContainer>
      <ImageAdd>
        <IconSpace>
          <Icon type="camera" size={30}></Icon>
          <Circle></Circle>
        </IconSpace>
        <div style={{ textAlign: "center", marginTop: 10, color: "#007af3" }}>
          이미지 등록
        </div>
      </ImageAdd>
      <TitleAdd>
        <div style={{ marginLeft: 20, marginTop: 30, color: "#007af3" }}>
          카드북 이름
        </div>
        <InputTitle value={cardbookName} onChange={handleChange} />

        <AddButton onClick={handleClick}>신규 카드북 등록</AddButton>
      </TitleAdd>
    </NewCardContainer>
  );
};

export default NewCard;

const ImageAdd = styled.div`
  width: 132px;
  height: 192.5px;
  border: 2px dashed #007af3;
  border-right: none;
`;

const TitleAdd = styled.div`
  width: 226px;
  height: 192.5px;
  border: 2px dashed #007af3;
`;

const NewCardContainer = styled.div`
  display: flex;
  margin-bottom: 80px;
`;

const IconSpace = styled.button`
  font-size: 45px;
  align-items: center;
  justify-content: center;
  margin-top: 60px;
  margin-left: 35px;
  color: #007af3;
`;

const Circle = styled.div`
  width: 60px;
  height: 60px;
  border-radius: 50%;
  margin-top: -68px;
  margin-left: 0px;
  border: 1px solid #007af3;
`;

const InputTitle = styled.input`
  margin-left: 20px;
  margin-top: 10px;
  width: 175px;
  height: 25px;
  border: 1px solid #b1b1b1;
  border-radius: 3px;
`;

const AddButton = styled.button`
  width: 180px;
  height: 40px;
  font-weight: 600;
  border-radius: 5px;
  background-color: #007af3;
  color: white;
  margin-top: 40px;
  margin-left: 20px;
`;
