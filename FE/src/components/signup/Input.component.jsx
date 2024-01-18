import React from "react";
import styled from "styled-components";

const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
`;

const InputLabel = styled.label`
  margin-bottom: 5px;
  margin-top: 20px;
`;

const InputField = styled.input`
  padding: 16px 205px 16px 15px;
  border: 1px solid #bbb;
  border-radius: 6px;
  margin: 0 auto;
`;

const Input = ({ label, register, id, rules }) => {
  return (
    <InputContainer>
      <InputLabel>{label}</InputLabel>
      <InputField {...register(id, rules)} />
    </InputContainer>
  );
};

export default Input;
