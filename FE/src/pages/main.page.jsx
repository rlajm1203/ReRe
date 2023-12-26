import Header from "../components/common/layout/Header.component.jsx";
import { MainContainer } from "../styles/Container.jsx";
import styled from "styled-components";
import Grid from "../components/common/Grid.component.jsx";
import { Icon } from "../components/common/Icon.component.jsx";
import MainBar from "../components/main/MainBar.component.jsx";

const MainPage = () => {
  return (
    <div>
      <Header />
      <MainContainer>
        <MainBar text="기본 카드북"></MainBar>
        <Grid />
        <MainBar text="나의 카드북"></MainBar>
        <Grid />
      </MainContainer>
    </div>
  );
};

export default MainPage;
