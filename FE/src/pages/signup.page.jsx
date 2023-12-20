import React from "react";
import styled from "styled-components";

const Input = styled.input`
  display: flex;
  margin: 0 auto;
`;

const Button = styled.button`
  display: flex;
  margin: 0 auto;
`;

const SignupPage = () => {
  return (
    <div>
      <h2>회원가입</h2>
      <h3>암기에 목 마를 때, 뤼뤼</h3>

      <div>
        <Input type="text" placeholder="example" />
      </div>
      <div>
        <Input type="password" placeholder="******" />
      </div>
      <div>
        <Input type="password" placeholder="******" />
      </div>
      <div>
        <Input type="text" placeholder="example" />
      </div>
      <Button>가입하기</Button>
    </div>
  );
};

export default SignupPage;
