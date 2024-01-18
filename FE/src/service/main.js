import axios from "axios";

export const mainContents = () => {
  return axios.get("https://be.econo-rere.store/cardbooks");
};
