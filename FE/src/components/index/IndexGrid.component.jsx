import React from "react";
import styled from "styled-components";
import BlueBox from "./BlueBox.component";

const IndexGrid = (datas) => {
  console.log(datas.data.themes);
  return (
    <GridContainer>
      {datas.data.themes.map((data) => {
        return <BlueBox data={data} key={data.themeId}></BlueBox>;
      })}
    </GridContainer>
  );
};

export default IndexGrid;

const GridContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
`;
