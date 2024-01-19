import Header from "../components/common/layout/Header.component.jsx";
import { MainContainer } from "../styles/Container.jsx";
import MainBar from "../components/main/MainBar.component.jsx";
import { mainPageCardbooks } from "../components/main/MainTest.js";
import Grid from "../components/main/Grid.component.jsx";
import axios from "axios";
import NewCard from "../components/main/NewCard.component.jsx";
import styled from "styled-components";
import { useState } from "react";
import { mainContents } from "../service/main.js";
import { useQuery } from "@tanstack/react-query";

const MainPage = () => {
  //const datas = mainPageCardbooks.response;
  // axios.get("http://192.168.0.200:8080/cardbooks").then((res) => {
  //   console.log(res);
  // });
  const [selectedCardbookId, setSelectedCardbookId] = useState(null);

  const handleCardbookClick = (cardbookId) => {
    setSelectedCardbookId(cardbookId);
  };

  const { data, isLoading, error } = useQuery({
    queryKey: ["cardbooks"],
    queryFn: () => mainContents(),
  });
  console.log(data);

  return (
    <div>
      <Header />
      {isLoading ? (
        <div>Loading...</div>
      ) : (
        <MainContainer>
          <MainBar text="기본 카드북"></MainBar>
          <Grid
            cardbookId={selectedCardbookId}
            data={data?.data?.response.originCardbooks}
            barText="기본 카드북"
            onCardbookClick={handleCardbookClick}
          ></Grid>
          <MainBar text="나의 카드북"></MainBar>
          <MyCardBookContainer>
            <Grid
              cardbookId={selectedCardbookId}
              data={data?.data?.response.myCardbooks}
              barText="나의 카드북"
              onCardbookClick={handleCardbookClick}
            ></Grid>
          </MyCardBookContainer>
        </MainContainer>
      )}
    </div>
  );
};

export default MainPage;

const MyCardBookContainer = styled.div``;
