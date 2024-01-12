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
    formState: { isSubmitting, errors },
  } = useForm();

  const [doubleName, setDoubleName] = useState(false);
  const [doubleId, setDoubleId] = useState(false);

  const handleIdDuplicate = () => {
    const loginId = getValues("loginId");

    if (!errors.loginId) {
      axios
        .post("http://192.168.0.200:8080/users/login-id/check", { loginId })
        .then((res) => {
          console.log(res);
          if (!res.data.success) {
            setDoubleId(false);
          }
          alert(res.data.message);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  };

  const handleNicknameDuplicate = () => {
    const nickname = getValues("nickname");
    if (!errors.nickname) {
      axios
        .post("http://192.168.0.200:8080/users/nickname/check", {
          nickname,
        })
        .then((res) => {
          console.log(res);
          if (!res.data.success) {
            setDoubleName(false);
          }
          alert(res.data.message);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  };

  const passwordCheck = (pw) => {
    return {
      required: "비밀번호를 입력하세요.",
      pattern: {
        value: /^[a-z0-9]{6,15}$/i,
        message: "6~15자의 영문 대소문자, 숫자만 사용 가능합니다.",
      },
      validate: (value) => value === pw || "비밀번호가 일치하지 않습니다.",
    };
  };

  const handleSignup = (data) => {
    delete data.passwordConfirm;

    if (doubleName && doubleId) {
      axios
        .post("http://192.168.0.200:8080/users/signup", data)
        .then((res) => {
          console.log(res);
          if (res.data.success) {
            window.location.href = "/";
          }
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
              duplicateCheck={handleIdDuplicate}
              id="loginId"
              rules={{
                required: "아이디를 입력하세요.",
                pattern: {
                  value: /^[a-z0-9]{4,20}$/i,
                  message: "4~20자의 영문 대소문자, 숫자만 사용 가능합니다.",
                },
              }}
            />
            {errors.loginId && (
              <ErrorMessage>{errors.loginId.message}</ErrorMessage>
            )}
            <Input
              label="비밀번호"
              type="password"
              placeholder="******"
              id="pw"
              register={register}
              rules={{
                required: "비밀번호를 입력하세요.",
                pattern: {
                  value: /^[a-z0-9]{6,15}$/i,
                  message: "6~15자의 영문 대소문자, 숫자만 사용 가능합니다.",
                },
              }}
            />
            {errors.pw && <ErrorMessage>{errors.pw.message}</ErrorMessage>}
            <Input
              label="비밀번호 확인"
              type="password"
              placeholder="******"
              id="passwordConfirm"
              register={register}
              rules={passwordCheck(getValues("pw"))}
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
              rules={{
                required: "닉네임을 입력하세요.",
                pattern: {
                  value: /^[가-힣a-z0-9]{6,15}$/i,
                  message: "6~15자의 영문 대소문자, 숫자만 사용 가능합니다.",
                },
              }}
              setState={setDoubleName}
              duplicateCheck={handleNicknameDuplicate}
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
