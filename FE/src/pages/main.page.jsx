import Header from "../components/common/layout/Header.component.jsx";
import { MainContainer } from "../styles/Container.jsx";
import MainBar from "../components/main/MainBar.component.jsx";
import { mainPageCardbooks } from "../components/main/MainTest.js";
import Grid from "../components/main/Grid.component.jsx";
import axios from "axios";
import NewCard from "../components/main/NewCard.component.jsx";
import styled from "styled-components";

const MainPage = () => {
  const datas = mainPageCardbooks.response.originCardbooks;

  // axios.get("http://192.168.0.200:8080/cardbooks").then((res) => {
  //   console.log(res);
  // });

  return (
    <div>
      <Header />
      <MainContainer>
        <MainBar text="기본 카드북"></MainBar>
        <Grid data={datas}></Grid>
        <MainBar text="나의 카드북"></MainBar>
        <MyCardBookContainer>
          <Grid data={datas}></Grid>
        </MyCardBookContainer>
      </MainContainer>
    </div>
  );
};

export default MainPage;

const MyCardBookContainer = styled.div``;
