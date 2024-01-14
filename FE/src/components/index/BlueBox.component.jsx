import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";

const BlueBox = ({ data }) => {
  console.log(data);
  return (
    <div>
      <EditBox>
        <button style={{ fontSize: 16 }}>수정 </button>
        <div style={{ marginTop: 2, marginRight: 10, marginLeft: 10 }}>|</div>
        <button style={{ fontSize: 16 }}>삭제</button>
      </EditBox>
      <BlueSquare style={{}}>
        <div>
          <Link to="/card">
            <Img
              style={{ marginRight: 147 }}
              src="macos-original-icon.png"
            ></Img>
          </Link>

          <p
            style={{
              maxWidth: 100,
              width: "100%",
              textAlign: "center",
              marginTop: 10,
              marginLeft: -5,
            }}
          >
            {data.themeName}
          </p>
        </div>
        <PlantBox>
          <div>
            <Img
              style={{ width: 75, transform: "translate(0px, 7px)" }}
              src="1.png"
            ></Img>
            <p
              style={{
                width: "100%",
                textAlign: "center",
              }}
            >
              1시간
            </p>
          </div>
          <div>
            <Img style={{ width: 75 }} src="2.png"></Img>
            <p
              style={{
                width: "100%",
                textAlign: "center",
              }}
            >
              하루
            </p>
          </div>
          <div>
            <Img style={{ width: 100 }} src="3.png"></Img>
            <p
              style={{
                width: "100%",
                textAlign: "center",
              }}
            >
              3일
            </p>
          </div>
          <div>
            <Img style={{ width: 100 }} src="4.png"></Img>
            <p
              style={{
                width: "100%",
                textAlign: "center",
              }}
            >
              7일
            </p>
          </div>
        </PlantBox>
      </BlueSquare>
    </div>
  );
};

const BlueSquare = styled.div`
  width: 91%;
  height: 180px;
  border-radius: 10px;
  border: 1px solid #007af3;
  display: flex;
  justify-content: space-between;
  padding: 60px;
  align-items: center;
  margin-bottom: 70px;
`;
const Img = styled.img`
  width: 90px;
`;

const PlantBox = styled.div`
  width: 900px;
  display: flex;
  align-items: end;
  bottom: 0;
  justify-content: space-between;
`;

const EditBox = styled.div`
  display: flex;
  justify-content: flex-end;
  margin-bottom: 10px;
`;

export default BlueBox;
