import React, { useState } from "react";
import styled from "styled-components";
import { Icon } from "../common/Icon.component";
import axios from "axios";

const NewCard = () => {
  const [name, setCardbookName] = useState("");
  const [selectedImage, setSelectedImage] = useState(null);

  const handleChange = (e) => {
    setCardbookName(e.target.value);
  };

  const handleImageClick = () => {
    // 이미지 선택을 위한 파일 선택 창 열기
    const input = document.createElement("input");
    input.type = "file";
    input.accept = "image/*";
    input.onchange = handleImageUpload;
    input.click();
  };

  const handleImageUpload = (e) => {
    const file = e.target.files[0];
    setSelectedImage(file);
    alert("이미지가 업로드되었습니다."); // 이미지 업로드 완료 알림
  };

  const handleClick = () => {
    const formData = new FormData();
    formData.append("name", name);

    if (selectedImage) {
      formData.append("image", selectedImage);
    }

    axios
      .post(`${import.meta.env.VITE_API_KEY}/cardbook`, formData)
      .then((response) => {
        console.log("카드북 등록 성공!");
        window.location.reload(); // 페이지 새로고침
      })
      .catch((error) => {
        console.error("카드북 등록 실패:", error);
      });
  };

  return (
    <NewCardContainer>
      <ImageAdd onClick={handleImageClick}>
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
        <InputTitle value={name} onChange={handleChange} />

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
  margin-top: 80px;
`;

const TitleAdd = styled.div`
  width: 226px;
  height: 192.5px;
  border: 2px dashed #007af3;
  margin-top: 80px;
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
