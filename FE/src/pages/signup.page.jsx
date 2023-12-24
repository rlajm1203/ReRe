import React from "react";
import styled from "styled-components";
import { MainContainer } from "../styles/Container.jsx";
import Header from "../components/common/layout/Header.component.jsx";
import Button from "../components/common/Button.component.jsx";

const SignupPage = () => {
  return (
    <div>
      <Header />
      <MainContainer>
        <Title>회원가입</Title>
        <SubTitle>암기에 목 마를 때, 뤼뤼</SubTitle>
        <Form>
          <label>아이디</label>
          <Input type="text" placeholder="example" />
          <label>비밀번호</label>
          <Input type="password" placeholder="******" />
          <label>비밀번호 확인</label>
          <Input type="password" placeholder="******" />
          <label>닉네임</label>
          <Input type="text" placeholder="example" />
          <Button>가입하기</Button>
        </Form>
      </MainContainer>
    </div>
  );
};

export default SignupPage;

const Input = styled.input`
  display: flex;
  margin: 5px auto 30px auto;
  padding: 16px 205px 16px 15px;
  border: 1px solid #bbb;
  border-radius: 6px;
`;

const Title = styled.h2`
  text-align: center;
  font-weight: 400;
  font-size: 28px;
  margin-bottom: 12px;
  margin-top: 60px;
`;

const SubTitle = styled.h3`
  text-align: center;
  font-weight: 400;
  font-size: 17px;
  margin-bottom: 34px;
`;

const Form = styled.form`
  margin: 0 auto;
`;
