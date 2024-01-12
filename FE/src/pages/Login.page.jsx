import React from "react";
import Header from "../components/common/layout/Header.component.jsx";
import Button from "../components/common/Button.component.jsx";
import Input from "../components/signup/Input.component.jsx";
import styled from "styled-components";
import { MainContainer } from "../styles/Container.jsx";
import { useForm } from "react-hook-form";
import axios from "axios";

function LoginPage({}) {
  const {
    register,
    handleSubmit,
    getValues,
    formState: { isSubmitting, errors },
  } = useForm();

  const handleLogin = (data) => {
    axios
      .post("http://192.168.0.200:8080/users/login", data)
      .then((res) => {
        console.log(res);
        alert(res.data.message);
        if (res.data.success) {
          window.location.href = "/";
        }
      })
      .catch((err) => {
        console.log(err);
      });
    console.log(data);
  };

  return (
    <div>
      <form onSubmit={handleSubmit(handleLogin)}>
        <Header />
        <MainContainer>
          <LogoCenter>
            <a href="/">
              <Logo src="logo.png" alt="logo" />
            </a>
            <Input
              label="아이디"
              id="loginId"
              type="text"
              placeholder="example"
              register={register}
            />
            <Input
              label="비밀번호"
              id="pw"
              type="password"
              placeholder="******"
              register={register}
            />
            <Button>로그인</Button>
          </LogoCenter>
        </MainContainer>
      </form>
    </div>
  );
}

export default LoginPage;

const LogoCenter = styled.div`
  margin: 0 auto;
`;

const Logo = styled.img`
  width: 120px;
  height: 107px;
  margin: 80px 0 30px 113px;
`;
