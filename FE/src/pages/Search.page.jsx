import React from "react";
import Header from "../components/common/layout/Header.component.jsx";
import MainBar from "../components/main/MainBar.component.jsx";
import CardBook from "../components/common/CardBook.component.jsx";
import { MainContainer } from "../styles/Container.jsx";
import { useQuery } from "@tanstack/react-query";
import { searchContents } from "../service/main.js";
import { useParams } from "react-router-dom";
import { useState } from "react";
import Grid from "../components/main/Grid.component.jsx";

const SearchPage = () => {
  const [selectedCardbookId, setSelectedCardbookId] = useState(null);

  const handleCardbookClick = (cardbookId) => {
    setSelectedCardbookId(cardbookId);
  };
  const { searchKeyword } = useParams();
  console.log(searchKeyword);
  const { data, isLoading, error } = useQuery({
    queryKey: ["searchbooks"],
    queryFn: () => searchContents(searchKeyword),
  });
  console.log();

  return (
    <div>
      <Header />
      {isLoading ? (
        <div>Loading...</div>
      ) : (
        <MainContainer>
          <>
            <MainBar text={searchKeyword + " 검색결과"}></MainBar>
            <Grid
              cardbookId={selectedCardbookId}
              data={data?.data?.response}
              onCardbookClick={handleCardbookClick}
            ></Grid>
          </>
        </MainContainer>
      )}
    </div>
  );
};

export default SearchPage;
