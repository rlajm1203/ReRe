import React from "react";
import styled from "styled-components";

const GridContainer = styled.div`
  display: flex;
`;

const ImageField = styled.img`
  width: 132px;
  height: 192.5px;
  margin-top: 80px;
`;

const ButtonField = styled.button`
  padding: 0px 195px 100px 15px;
  width: 280px;
  border-radius: 0px 4px 4px 0px;
  border: #007af3 1px solid;
  margin-top: 80px;
`;

const Grid = () => {
  return (
    <GridContainer>
      <ImageField src="bookImage.png" alt="book cover" />
      <ButtonField>해커스 토익 기출 보카</ButtonField>
    </GridContainer>
  );
};

export default Grid;
