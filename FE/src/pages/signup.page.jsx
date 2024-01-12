import React from "react";
import styled from "styled-components";
import { MainContainer } from "../styles/Container.jsx";
import Header from "../components/common/layout/Header.component.jsx";
import Button from "../components/common/Button.component.jsx";
import Input from "../components/signup/Input.component.jsx";
import { useForm } from "react-hook-form";
import InputDuplicate from "../components/signup/InputDuplicate.component.jsx";
import { useState } from "react";
import axios from "axios";

function SignupPage({}) {
  const {
    register,
    handleSubmit,
    getValues,
    formState: { isSubmitting, isSubmitted, errors },
  } = useForm();

  const onSubmit = (data) => {
    console.log(data);
  };

  const [doubleName, setDoubleName] = useState(false);
  const [doubleId, setDoubleId] = useState(false);

  const passwordCheck = (password) => {
    return {
      required: "비밀번호를 입력하세요.",
      minLength: {
        value: 8,
        message: "8자리 이상 비밀번호를 사용하세요.",
      },
      validate: (value) =>
        value === password || "비밀번호가 일치하지 않습니다.",
    };
  };

  const handleSignup = (data) => {
    if (doubleName && doubleId) {
      axios
        .post("http://192.168.0.200:8080/users/signup", data)
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
      console.log(data);
    } else {
      alert("닉네임과 아이디를 중복 확인하세요.");
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit(handleSignup)}>
        <Header />
        <MainContainer>
          <Title>회원가입</Title>
          <SubTitle>암기에 목 마를 때, 뤼뤼</SubTitle>
          <LabelCenter>
            <InputDuplicate
              label="아이디"
              type="text"
              placeholder="example"
              register={register}
              setState={setDoubleId}
              id="id"
              rules={{
                required: "아이디를 입력하세요.",
                pattern: {
                  value: /^[a-z0-9]{4,20}$/i,
                  message: "4~20자의 영문 대소문자, 숫자만 사용 가능합니다.",
                },
              }}
            />
            {errors.id && <ErrorMessage>{errors.id.message}</ErrorMessage>}
            <Input
              label="비밀번호"
              type="password"
              placeholder="******"
              id="password"
              register={register}
              rules={{
                required: "비밀번호를 입력하세요.",
                minLength: {
                  value: 8,
                  message: "8자리 이상 비밀번호를 사용하세요.",
                },
              }}
            />
            {errors.password && (
              <ErrorMessage>{errors.password.message}</ErrorMessage>
            )}
            <Input
              label="비밀번호 확인"
              type="password"
              placeholder="******"
              id="passwordConfirm"
              register={register}
              rules={passwordCheck(getValues("password"))}
            />
            {errors.passwordConfirm && (
              <ErrorMessage>{errors.passwordConfirm.message}</ErrorMessage>
            )}

            <InputDuplicate
              label="닉네임"
              type="text"
              placeholder="example"
              id="nickname"
              register={register}
              rules={{ required: "닉네임을 입력하세요." }}
              setState={setDoubleName}
            />
            {errors.nickname && (
              <ErrorMessage>{errors.nickname.message}</ErrorMessage>
            )}
            <Button
              style={{ marginBottom: 100 }}
              disabled={isSubmitting}
              type="submit"
            >
              가입하기
            </Button>
          </LabelCenter>
        </MainContainer>
      </form>
    </div>
  );
}

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

const LabelCenter = styled.div`
  margin: 0 auto;
`;

const ErrorMessage = styled.span`
  color: red;
  margin-bottom: 10px;
  top: 0px;
`;
