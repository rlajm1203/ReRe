import axios from "axios";

export const mainContents = () => {
  return axios.get(`${import.meta.env.VITE_API_KEY}/cardbooks`);
};

export const indexContents = (cardBookId) => {
  return axios.get(
    `${import.meta.env.VITE_API_KEY}/cardbook/${cardBookId}/themes`
  );
};

export const cardContents = (cardBookId, themeId) => {
  return axios.get(
    `${
      import.meta.env.VITE_API_KEY
    }/cardbook/${cardBookId}/theme/${themeId}/cards`
  );
};

export const searchContents = (searchKeyword) => {
  return axios.get(
    `${import.meta.env.VITE_API_KEY}/cardbook/search?keyword=${searchKeyword}`
  );
};
