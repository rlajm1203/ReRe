import Header from "../components/common/layout/Header.component.jsx";
import { MainContainer } from "../styles/Container.jsx";
import styled from "styled-components";
import CardBook from "../components/common/CardBook.component.jsx";
import { Icon } from "../components/common/Icon.component.jsx";
import MainBar from "../components/main/MainBar.component.jsx";
import { mainPageCardbooks } from "../components/main/MainTest.js";
import Grid from "../components/main/Grid.component.jsx";
const MainPage = () => {
  console.log(mainPageCardbooks.response.originCardbooks);
  const datas = mainPageCardbooks.response.originCardbooks;

  return (
    <div>
      <Header />
      <MainContainer>
        <MainBar text="기본 카드북"></MainBar>
        <Grid data={datas}></Grid>
        <MainBar text="나의 카드북"></MainBar>
        <Grid data={datas}></Grid>
      </MainContainer>
    </div>
  );
};

export default MainPage;
