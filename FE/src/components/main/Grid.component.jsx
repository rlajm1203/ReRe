import React from "react";
import CardBook from "../common/CardBook.component";
import styled from "styled-components";

const Grid = (datas) => {
  return (
    <GridContainer>
      {datas.data.map((data) => {
        return <CardBook data={data}></CardBook>;
      })}
    </GridContainer>
  );
};

export default Grid;

const GridContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
`;
