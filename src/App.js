import './App.css';
import Login from "./Login";
import {useEffect, useState} from "react";
import TestConponent from "./TestConponent";
import axios from "axios";
import {useDispatch} from "react-redux";
import {setToken} from "./store";
import {BrowserRouter, Route, Routes} from "react-router-dom";
function App() {

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path={"/"} element={<Login/>}/>
          <Route path={"/test"} element={<TestConponent/>}/>
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
