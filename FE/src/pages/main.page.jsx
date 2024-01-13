import Header from "../components/common/layout/Header.component.jsx";
import { MainContainer } from "../styles/Container.jsx";
import MainBar from "../components/main/MainBar.component.jsx";
import { mainPageCardbooks } from "../components/main/MainTest.js";
import Grid from "../components/main/Grid.component.jsx";
import axios from "axios";
import NewCard from "../components/main/NewCard.component.jsx";

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
        <Grid data={datas}></Grid>
        <NewCard></NewCard>
      </MainContainer>
    </div>
  );
};

export default MainPage;
