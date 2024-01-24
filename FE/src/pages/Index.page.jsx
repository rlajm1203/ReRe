import React from "react";
import Header from "../components/common/layout/Header.component.jsx";
import { MainContainer } from "../styles/Container.jsx";
import Bar from "../components/common/Bar.component.jsx";
import styled from "styled-components";
import BlueBox from "../components/index/BlueBox.component.jsx";
import NewBlueBox from "../components/index/NewBlueBox.component.jsx";
import { Link, useParams } from "react-router-dom";
import { indexPageIndexs } from "../components/index/IndexTest.js";
import IndexGrid from "../components/index/IndexGrid.component.jsx";
import { useQuery } from "@tanstack/react-query";
import { indexContents } from "../service/main.js";

const IndexPage = () => {
  //const datas = indexPageIndexs.response;
  const { cardBookId } = useParams();
  const { data, isLoading, error } = useQuery({
    queryKey: ["Indexs", cardBookId],
    queryFn: () => indexContents(cardBookId),
  });
  // const sse = new EventSource(`${import.meta.env.VITE_API_KEY}/connect`);

  // sse.addEventListener("connect", (e) => {
  //   const { data: receivedConnectData } = e;
  //   console.log("connect event data: ", receivedConnectData);
  // });

  const responseData = data?.data.response;
  return (
    <div>
      <Header />
      {isLoading ? (
        <div></div>
      ) : (
        <MainContainer>
          <Bar text={data?.data.response.cardbookName}></Bar>
          <IndexGrid data={responseData}></IndexGrid>
          <Link to={`/cardcreate/${cardBookId}`}>
            <NewBlueBox cardBookId={cardBookId}></NewBlueBox>
          </Link>
        </MainContainer>
      )}
    </div>
  );
};

export default IndexPage;
