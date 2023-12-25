import Header from "../components/common/layout/Header.component.jsx";
import { MainContainer } from "../styles/Container.jsx";
import styled from "styled-components";
import Grid from "../components/common/Grid.component.jsx";
import { Icon } from "../components/common/Icon.component.jsx";

const MainPage = () => {
  return (
    <div>
      <Header />
      <MainContainer>
        <Bar>
          <Icon type="back" />
          <Title>망각 곡선이란?</Title>
        </Bar>

        <Grid />
      </MainContainer>
    </div>
  );
};

export default MainPage;

const Bar = styled.div`
  display: flex;
  align-items: center;
  position: relative;
  top: 125px;
  left: 50px;
`;

const Title = styled.h2`
  text-align: left;
  font-weight: 400;
  font-size: 24px;
  margin-left: 0px; /* 필요한 만큼 조정하여 아이콘과의 간격을 설정하세요 */
`;
