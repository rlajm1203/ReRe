import React from "react";
import Header from "../components/common/layout/Header.component.jsx";
import MainBar from "../components/main/MainBar.component.jsx";
import CardBook from "../components/common/CardBook.component.jsx";
import { MainContainer } from "../styles/Container.jsx";

const SearchPage = () => {
  return (
    <div>
      <Header />
      <MainContainer>
        <MainBar text="운영체제 검색 결과"></MainBar>
        <CardBook />
      </MainContainer>
    </div>
  );
};

export default SearchPage;
