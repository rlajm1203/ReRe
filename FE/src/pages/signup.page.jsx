import React from "react";
import styled from "styled-components";
import Inner from "../components/signup/Inner.component";

const Input = styled.input`
  display: flex;
  margin: 0 auto;
`;

const Button = styled.button`
  display: flex;
  margin: 0 auto;
`;

const H2 = styled.h2`
  text-align: center;
  font-weight: 400;
  font-size: 28px;
  margin-bottom: -5px;
  margin-top: 80px;
`;

const H3 = styled.h3`
  text-align: center;
  font-weight: 400;
`;

const SignupPage = () => {
  return (
    <div>
      {/* <Inner /> */}
      <div>
        <H2>회원가입</H2>
        <H3>암기에 목 마를 때, 뤼뤼</H3>
        <form>
          <label>아이디</label>
          <Input type="text" placeholder="example" />
          <label>비밀번호</label>
          <Input type="password" placeholder="******" />
          <label>비밀번호 확인</label>
          <Input type="password" placeholder="******" />
          <label>닉네임</label>
          <Input type="text" placeholder="example" />

          <Button>가입하기</Button>
        </form>
      </div>
    </div>
  );
};

export default SignupPage;
