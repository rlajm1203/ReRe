import React from "react";
import styled from "styled-components";
import { StyledIcon, Icon } from "../Icon.component";
import { Link } from "react-router-dom";

const Header = () => {
  return (
    <div>
      <HeaderArea>
        <Inner>
          <Link to="/">
            <Logo
              style={{ zIndex: 9999, position: "relative" }}
              src="/logo.png"
              alt="logo"
            />
          </Link>

          <Submenu>
            <Menu>
              <li>
                <A href="#">망각 곡선이란?</A>
              </li>
              <li>
                <A href="#">뤼뤼 사용법</A>
              </li>
              <li>
                <A href="#">내 정보</A>
              </li>
            </Menu>
            <Search>
              <Input type="text" />
              <IconSpace>
                <Icon type="search" />
              </IconSpace>
            </Search>
            <Button>
              <Link to="/login">로그인</Link>
            </Button>
            <Button>
              <Link to="/signup">회원가입</Link>
            </Button>
          </Submenu>
        </Inner>
      </HeaderArea>
    </div>
  );
};

export default Header;

const HeaderArea = styled.header`
  position: relative;
  width: 100%;
  height: 5rem;
  border-bottom: 0.08375rem solid #ccc;
`;

const Logo = styled.img`
  bottom: 0;
  margin: auto 0;
  transform: translate(40px, -4px);
  width: 3.125rem;
  display: block;
`;

const Inner = styled.div`
  position: relative;
  width: 1320px;
  display: flex;
  align-items: center;
  max-width: 83.125rem;
  height: 5rem;
  margin: 0 auto;
`;

const A = styled.a`
  display: block;
  padding: 0.6875rem 1rem;
  font-size: 1.0625rem;
  text-decoration: none;

  &:hover {
    color: #007af3;
  }
`;

const Menu = styled.ul`
  display: flex;
  list-style: none;
  top: 0;
  bottom: 0;
  margin: auto 0 auto 6.5rem;
`;

const Search = styled.div`
  position: relative;
  height: 2.1875rem;
  margin: auto 0 auto 30.625rem; /* 490px를 rem으로 변환한 값 */
`;

const Input = styled.input`
  width: 12.375rem; /* 198px를 rem으로 변환한 값 */
  height: 2.1875rem;
  padding: 0.25rem 2rem 0.25rem 0.625rem;
  border: 0.0625rem solid #ccc;
  background-color: #ececec;
  box-sizing: border-box;
  border-radius: 0.3125rem; /* 5px를 rem으로 변환한 값 */
  outline: none;
  position: relative;
`;
const Button = styled.button`
  height: 2.1875rem;
  position: relative;
  margin: auto 0 auto 0.625rem; /* 10px를 rem으로 변환한 값 */
  padding: 0 0.5725rem;
  font-size: 1.0625rem;
  background-color: #fff;
  border: 0.0625rem solid #bbb;
  border-radius: 0.3125rem; /* 5px를 rem으로 변환한 값 */
`;

const Submenu = styled.div`
  display: flex;
  top: 0;
  bottom: 0;
  margin: auto 0;
  position: absolute;
  height: 5rem;
`;

const IconSpace = styled(StyledIcon)`
  position: absolute;
  right: 9px;
  top: 7px;
`;
