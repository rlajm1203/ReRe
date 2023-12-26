import React from "react";
import Header from "../components/common/layout/Header.component.jsx";
import Bar from "../components/common/Bar.component.jsx";
import Grid from "../components/common/Grid.component.jsx";
import { MainContainer } from "../styles/Container.jsx";

const SearchPage = () => {
  return (
    <div>
      <Header />
      <Bar text="운영체제 검색 결과"></Bar>
      <MainContainer>
        <Grid />
      </MainContainer>
    </div>
  );
};

export default SearchPage;
