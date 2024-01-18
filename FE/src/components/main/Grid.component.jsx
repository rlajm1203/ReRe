import React from "react";
import CardBook from "../common/CardBook.component";
import styled from "styled-components";
import NewCard from "./NewCard.component";

const Grid = ({ data, barText, cardbookId }) => {
  console.log(data);
  console.log(cardbookId);
  return (
    <GridContainer>
      {data.map((data) => {
        return <CardBook cardbookId={cardbookId} data={data}></CardBook>;
      })}
      {barText === "나의 카드북" && <NewCard></NewCard>}
    </GridContainer>
  );
};

export default Grid;

const GridContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-gap: 4.8rem;
  grid-row-gap: 0;
  flex-wrap: wrap;
`;
