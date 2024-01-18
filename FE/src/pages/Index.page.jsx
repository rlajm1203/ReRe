import React from "react";
import Header from "../components/common/layout/Header.component.jsx";
import { MainContainer } from "../styles/Container.jsx";
import Bar from "../components/common/Bar.component.jsx";
import styled from "styled-components";
import BlueBox from "../components/index/BlueBox.component.jsx";
import NewBlueBox from "../components/index/NewBlueBox.component.jsx";
import { Link } from "react-router-dom";
import { indexPageIndexs } from "../components/index/IndexTest.js";
import IndexGrid from "../components/index/IndexGrid.component.jsx";
import { useQuery } from "@tanstack/react-query";
import { mainContents } from "../service/main.js";

const IndexPage = () => {
  const datas = indexPageIndexs.response;
  const { data, isLoading, error } = useQuery({
    queryKey: ["Indexs"],
    queryFn: () => mainContents(),
  });
  const sse = new EventSource("https://be.econo-rere.store/connect");

  sse.addEventListener("connect", (e) => {
    const { data: receivedConnectData } = e;
    console.log("connect event data: ", receivedConnectData); // "connected!"
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
