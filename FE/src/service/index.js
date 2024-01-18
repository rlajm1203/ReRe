import axios from "axios";

axios.defaults.withCredentials = true;

export const instance = axios.create({
  baseURL: "https://be.econo-rere.store",
  timeout: 2000,
  headers: {
    "Content-Type": "application/json",
  },
});
