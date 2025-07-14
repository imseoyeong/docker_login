import React from "react";
import apiClient from "../api/axiosInstance";
import {useDispatch} from "react-redux";
import {useNavigate} from "react-router-dom";
import {adminLogin, setToken} from "../store";

export default function AdminLogin(){
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleAdminLogin= async (e)=>{
        e.preventDefault();
        const data = {username:"ADMIN_"+e.target.adminName.value ,password:e.target.password.value }
        try {
            const response = await apiClient.post("/login",new URLSearchParams(data));
            dispatch(setToken(response.headers["authorization"]));
            dispatch(adminLogin());
            navigate("/");
        } catch (error) {
            let message=null;
            if(error.response && error.response.data.error){
                message = error.response.data.error;
            }
            if(error.response && error.response.data.result){
               message = message + " " + error.response.data.result;
            }
            if(message !==null){
                alert(message);
            }
            console.log(error);
        }
    }
    return (
        <>
            <form onSubmit={handleAdminLogin}>
                <p>관리자 아이디 : <input
                    type="text"
                    placeholder="Admin Name"
                    name="adminName"
                    required
                />
                </p>
                <p>관리자 비밀번호 : <input
                    type="password"
                    placeholder="Password"
                    name="password"
                    required
                />
                </p>
                <button type="submit">관리자 로그인</button>
            </form>
        </>
    );
}
