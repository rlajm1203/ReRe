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
  padding: 16px 115px 16px 15px;
  border: 1px solid #bbb;
  border-radius: 6px;
  margin: 0 auto;
`;

const InputDuplicate = ({ label, register, id, rules }) => {
  return (
    <InputContainer>
      <InputLabel>{label}</InputLabel>
      <div>
        <InputField {...register(id, rules)} />
        <Button>중복 확인</Button>
      </div>
    </InputContainer>
  );
};

export default InputDuplicate;

const Button = styled.button`
  padding: 16px 15px;
  border-radius: 6px;
  margin-left: 8px;
  border: 1px solid #bbb;
`;
