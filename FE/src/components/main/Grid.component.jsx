import React from "react";
import CardBook from "../common/CardBook.component";
import styled from "styled-components";
import NewCard from "./NewCard.component";

const Grid = (datas) => {
  console.log(datas.data);
  return (
    <GridContainer>
      {datas.data.map((data) => {
        return <CardBook data={data}></CardBook>;
      })}
      <NewCard></NewCard>
    </GridContainer>
  );
};

export default Grid;

const GridContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  flex-wrap: wrap;
`;
