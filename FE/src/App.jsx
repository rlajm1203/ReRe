import "./App.css";
import SignupPage from "./pages/Signup.page.jsx";
import MainPage from "./pages/Main.page.jsx";
import { Routes, Route } from "react-router-dom";

function App() {
  return (
    <Routes>
      <Route path="/signup" element={<SignupPage />} />
      <Route path="/" element={<MainPage />} />
    </Routes>
  );
}

export default App;
