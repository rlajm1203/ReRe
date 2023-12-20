import styled from "styled-components";
import { Icon } from "../components/common/Icon.component";

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
  width: 1300px;
  height: 70px;
  margin: 0 auto;
  background-color: orange;
`;

const MainPage = () => {
  return (
    <header>
      <Inner class="inner">
        <a href="/" class="logo">
          <Logo src="public/logo.png" alt="logo" />
        </a>

        <div class="sub-menu">
          <ul class="menu">
            <li>
              <a href="#">망각곡선이란?</a>
            </li>
            <li>
              <a href="#">뤼뤼 사용법</a>
            </li>
            <li>
              <a href="#">내 정보</a>
            </li>
          </ul>
          <div class="search">
            <input type="text" placeholder="검색어를 입력하세요" />
            <Icon type="search" />
          </div>
        </div>
      </Inner>
    </header>
  );
};

export default MainPage;
