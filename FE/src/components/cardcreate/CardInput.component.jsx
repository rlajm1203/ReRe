import styled from "styled-components";
import React from "react";

const CardInputWithGreyBar = ({ greyBarText, value, onChange }) => {
  return (
    <Container>
      <GreyBar text={greyBarText} />
      <CardInput value={value} onChange={onChange} />
    </Container>
  );
};

export default CardInputWithGreyBar;

export const GreyBar = ({ text }) => {
  return <GreyBox>{text}</GreyBox>;
};

const CardInput = ({ value, onChange }) => {
  return <InputBox type="text" value={value} onChange={onChange} />;
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

const InputBox = styled.input`
  display: flex;
  position: relative;
  top: 60px;
  width: 585px;
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

const GreyBox = styled.label`
  display: flex;
  position: relative;
  top: 60px;
  width: 587px;
  height: 30px;
  padding-left: 15px;
  padding-top: 15px;
  border: 1px solid #bbb;
  font-size: 16px;
  color: #666;

  outline: none;
  background-color: #f9f9f9;
`;
