import {NavLink,Link, Outlet, useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {useEffect} from "react";
import apiClient from "../api/axiosInstance";
import {adminLogout, saveCsrfToken} from "../store";
import axios from "axios";
import InitMenu from "./InitMenu";
import MainMenu from "./MainMenu";

export default function MainLayout(){
    const navigate = useNavigate();
    const adminLoginFlag=useSelector(state=>state.userInfo.adminLoginFlag);
    const userLoginFlag =useSelector(state=>state.userInfo.userLoginFlag);
    const dispatch = useDispatch();

    return (
        <>
            <h1>&lt;고객 관리&gt;</h1>
            {!(adminLoginFlag || userLoginFlag)? <InitMenu/> : <MainMenu/>}

            <Outlet/>
        </>
    );
}