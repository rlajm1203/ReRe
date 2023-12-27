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
  padding: 20px;
  display: flex;
  width: 280px;
  border-radius: 0px 4px 4px 0px;
  border: #007af3 1px solid;
  margin-top: 80px;
`;

const Grid = () => {
  return (
    <GridContainer>
      <ImageField src="bookImage.png" alt="book cover" />
      <ButtonField>
        <div>해커스 토익 보카</div>
        <div></div>
        <div></div>
        <div></div>
      </ButtonField>
    </GridContainer>
  );
};

export default Grid;
