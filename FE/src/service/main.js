import axios from "axios";

export const mainContents = () => {
  return axios.get("http://172.30.1.59:8080/cardbooks");
};
