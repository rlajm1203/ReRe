import React from "react";
import CardBook from "../common/CardBook.component";
import styled from "styled-components";

const Grid = (datas) => {
  console.log(datas.data);

  return (
    <>
      {datas.data.map((data) => {
        return <CardBook data={data}></CardBook>;
      })}
    </>
  );
};

export default Grid;
