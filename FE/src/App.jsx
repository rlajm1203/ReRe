import "../src/styles/App.css";
import SignupPage from "./pages/Signup.page.jsx";
import MainPage from "./pages/Main.page.jsx";
import LoginPage from "./pages/Login.page.jsx";
import IndexPage from "./pages/Index.page.jsx";
import SearchPage from "./pages/Search.page.jsx";
import CardPage from "./pages/Card.page.jsx";
import CardCreatePage from "./pages/CardCreate.page.jsx";
import { Routes, Route } from "react-router-dom";

function App() {
  return (
    <Routes>
      <Route path="/" element={<MainPage />} />
      <Route path="/signup" element={<SignupPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/index" element={<IndexPage />} />
      <Route path="/search" element={<SearchPage />} />
      <Route path="/card" element={<CardPage />} />
      <Route path="/card/create" element={<CardCreatePage />} />
    </Routes>
  );
}

export default App;
