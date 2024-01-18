import React from "react";
import styled from "styled-components";
import BlueBox from "./BlueBox.component";

const IndexGrid = ({ data }) => {
  const [themes, setThemes] = React.useState(data.themes);

  const handleDeleteTheme = (themeId) => {
    setThemes(themes.filter((theme) => theme.themeId !== themeId));
  };

  return (
    <GridContainer>
      {themes.map((theme) => (
        <BlueBox
          data={theme}
          key={theme.themeId}
          onDelete={() => handleDeleteTheme(theme.themeId)}
        />
      ))}
    </GridContainer>
  );
};

export default IndexGrid;

const GridContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
`;
