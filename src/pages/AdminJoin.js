import React, {useRef, useState} from "react";
import apiClient from "../api/axiosInstance";

import {useNavigate} from "react-router-dom";

export default function AdminJoin(){
    const navigate = useNavigate();
    const handleAdminJoin= async (e)=>{
        e.preventDefault();
        const data = {authenNumber:e.target.authenNumber.value,
            username:e.target.adminName.value ,
            password:e.target.password.value }
        try {
            const response = await apiClient.post("/admin-join", data );
            navigate("/");

        } catch (error) {
            if(error.response && error.response.status === 450 || error.response.status === 460){
                alert(error.response.data);
            }
            e.target.authenNumber.value="";
            e.target.adminName.value="";
            e.target.password.value="";
            console.log(error);
        }

    };

    return (
        <>
            <form onSubmit={handleAdminJoin}>
                <p>인증번호 : <input
                    type="text"
                    placeholder="Authentication Number"
                    name="authenNumber"
                    required
                /></p>
                <p>관리자 아이디 : <input
                    type="text"
                    placeholder="Admin Name"
                    name="adminName"
                />
                </p>
                <p>관리자 비밀번호 : <input
                    type="password"
                    placeholder="Password"
                    name="password"
                    required
                />
                </p>
                <button type="submit">추가</button>
            </form>
        </>
    );
}
