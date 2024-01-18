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
  const datas = indexPageIndexs.response;
  const { cardBookId } = useParams();
  console.log(cardBookId);
  const { data, isLoading, error } = useQuery({
    queryKey: ["Indexs"],
    queryFn: () => indexContents(cardBookId),
  });
  const sse = new EventSource("https://be.econo-rere.store/connect");

  sse.addEventListener("connect", (e) => {
    const { data: receivedConnectData } = e;
    console.log("connect event data: ", receivedConnectData);
  });
  return (
    <div>
      <Header />
      <MainContainer>
        <Bar text={datas.cardbookName}></Bar>
        <IndexGrid data={datas}></IndexGrid>
        <Link to="/cardcreate">
          <NewBlueBox></NewBlueBox>
        </Link>
      </MainContainer>
    </div>
  );
};

export default IndexPage;
