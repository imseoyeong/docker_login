import './App.css';
import Login from "./Login";
import {useEffect, useState} from "react";
import TestConponent from "./TestConponent";
import axios from "axios";
import {useDispatch} from "react-redux";
import {setToken} from "./store";
import {BrowserRouter, Route, Routes} from "react-router-dom";
function App() {
  const [isLogin, setLogin]=useState(false);
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path={"/"} element={<Login/>}/>
          <Route path={"/"} element={<TestConponent/>}/>
        </Routes>
      </BrowserRouter>
    </>
  );
}

// function App() {
//   return (
//       <>
//       <Login></Login>
//       </>
//   );
// }

export default App;
