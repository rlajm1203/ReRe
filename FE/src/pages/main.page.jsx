import styled from "styled-components";
import { Icon } from "../components/common/Icon.component";

const Header = styled.header`
  position: relative;
  width: 100%;
  height: 80px;
  border-bottom: 1.5px solid #ccc;
  background-color: orange;
`;

const Logo = styled.img`
  position: absolute;
  top: 0;
  bottom: 0;
  margin: auto 0;
  width: 130px;
  display: block;
`;

const Inner = styled.div`
  position: relative;
  width: 1330px;
  height: 70px;
  margin: 0 auto;
`;

const A = styled.a`
  display: block;
  padding: 11px 16px;
  font-size: 17px;
  text-decoration: none;

  &:hover {
    color: #007af3;
  }
`;

const Menu = styled.ul`
  display: flex;
  list-style: none;
  margin-left: 60px;
`;

const Search = styled.div`
  position: relative;
  height: 35px;
  margin: auto 0 auto 490px;
`;

const Input = styled.input`
  width: 198px;
  height: 35px;
  padding: 4px 10px;
  border: 1px solid #ccc;
  background-color: #ececec;
  box-sizing: border-box;
  border-radius: 5px;
  outline: none;
  position: relative;
`;
const Button = styled.button`
  height: 35px;
  position: relative;
  margin: auto 0 auto 10px;
  padding: 0 13px;
  font-size: 17px;
  background-color: #fff;
  border: 1px solid #bbb;
  border-radius: 5px;
`;

const Submenu = styled.div`
  display: flex;
`;

const MainPage = () => {
  return (
    <Header>
      <Inner>
        <a href="/">
          <Logo src="logo.png" alt="logo" />
        </a>

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
            <Icon type="search" />
          </Search>
          <Button>로그인</Button>
          <Button>회원가입</Button>
        </Submenu>
      </Inner>
    </Header>
  );
};

export default MainPage;
