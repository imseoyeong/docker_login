import React from "react";
import {useDispatch} from "react-redux";
import {useNavigate} from "react-router-dom";
import apiClient from "../api/axiosInstance";
import {setToken, userLogin} from "../store";

export default function UserLogin(){
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleAdminLogin= async (e)=>{
        e.preventDefault();
        const data = {username:"USER_"+e.target.userName.value ,password:e.target.password.value }
        try {
            const response = await apiClient.post("/login",new URLSearchParams(data));
            dispatch(setToken(response.headers["authorization"]));
            dispatch(userLogin());
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
    return(
        <>
            <form onSubmit={handleAdminLogin}>
                <p>사용자 아이디 : <input
                    type="text"
                    placeholder="User Name"
                    name="userName"
                    required
                />
                </p>
                <p>사용자 비밀번호 : <input
                    type="password"
                    placeholder="Password"
                    name="password"
                    required
                />
                </p>
                <button type="submit">사용자 로그인</button>
            </form>
        </>
    );
}