import React from "react";
import styled from "styled-components";

const CardBookContainer = styled.div`
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
  width: 230px;
  border-radius: 0px 4px 4px 0px;
  border: #007af3 1px solid;
  margin-top: 80px;
`;

const CardBook = (data) => {
  console.log(data);
  return (
    <CardBookContainer>
      <ImageField src="bookImage.png" alt="book cover" />
      <ButtonField>
        <div>{data.cardbookName}</div>
        <div>{data.writer}</div>
      </ButtonField>
    </CardBookContainer>
  );
};

export default CardBook;
