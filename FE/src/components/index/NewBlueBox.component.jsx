import React from "react";
import styled from "styled-components";
import { Icon } from "../common/Icon.component";

const NewBlueBox = (cardBookId) => {
  return (
    <div>
      <NewBlueSquare>
        <IconSpace>
          <Icon type="plus" size={"35px"} />
        </IconSpace>
        <p style={{ color: "#007af3" }}>신규</p>
      </NewBlueSquare>
    </div>
  );
};

export default NewBlueBox;

const NewBlueSquare = styled.div`
  width: 91%;
  height: 180px;
  border-radius: 10px;
  border: 2px dashed #007af3;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 60px;
  margin-bottom: 70px;
  align-items: center;
`;

const IconSpace = styled.div`
  color: #007af3;
`;
