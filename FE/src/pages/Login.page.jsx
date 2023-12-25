import React from "react";
import Header from "../components/common/layout/Header.component.jsx";
import Button from "../components/common/Button.component.jsx";
import Input from "../components/common/Input.component.jsx";
import styled from "styled-components";
import { MainContainer } from "../styles/Container.jsx";

const LoginPage = () => {
  return (
    <div>
      <Header />
      <MainContainer>
        <Form>
          <a href="/">
            <Logo src="logo.png" alt="logo" />
          </a>
          <Input label="아이디" type="text" placeholder="example" />
          <Input label="비밀번호" type="password" placeholder="******" />
          <Button>로그인</Button>
        </Form>
      </MainContainer>
    </div>
  );
};

export default LoginPage;

const Form = styled.form`
  margin: 0 auto;
`;

const Logo = styled.img`
  width: 230px;
  height: 79px;
  margin: 100px 0 30px 60px;
`;
