import React from "react";
import styled from "styled-components";

const BlueBox = () => {
  return (
    <div>
      <BlueSquare>
        <Img src="macos-original-icon.png"></Img>
        <Img src="1.png"></Img>
        <Img src="2.png"></Img>
        <Img src="3.png"></Img>
        <Img src="4.png"></Img>
      </BlueSquare>
    </div>
  );
};

const BlueSquare = styled.div`
  width: 100%;
  height: 230px;
  border-radius: 10px;
  border: 1px solid #007af3;
  display: flex;
  justify-content: center;
  align-items: center;
`;
const Img = styled.img`
  width: 90px;
`;
export default BlueBox;
