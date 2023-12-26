import React from "react";
import styled from "styled-components";
import { MainContainer } from "../styles/Container.jsx";
import Header from "../components/common/layout/Header.component.jsx";
import Button from "../components/common/Button.component.jsx";
import Input from "../components/signup/Input.component.jsx";

const SignupPage = () => {
  return (
    <div>
      <Header />
      <MainContainer>
        <Title>회원가입</Title>
        <SubTitle>암기에 목 마를 때, 뤼뤼</SubTitle>
        <Form>
          <Input label="아이디" type="text" placeholder="example" />
          <Input label="비밀번호" type="password" placeholder="******" />
          <Input label="비밀번호 확인" type="password" placeholder="******" />
          <Input label="닉네임" type="text" placeholder="example" />
          <Button>가입하기</Button>
        </Form>
      </MainContainer>
    </div>
  );
};

export default SignupPage;

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
