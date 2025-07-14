import apiClient from "../api/axiosInstance";
import errorDisplay from "../util/errorDisplay";
import {useState} from "react";
import {HttpStatusCode} from "axios";
import {useRef} from "react";
import {useNavigate} from "react-router-dom";

export default function JoinUser(){
    const [check, setCheck]=useState(false);
    const userIdRef=useRef();
    const navigate = useNavigate();
    const handleClick=async (e)=>{
        try{
            const urlpath="/userinfo/check-id/"+userIdRef.current.value;
            const response=await apiClient.get(urlpath);
            if(response.status === 250){
                alert("이미 있는 아이디입니다.");
            }else{
                alert(response.data);
                setCheck(true);
            }
        }catch(error){
            errorDisplay(error);
        }
    }

    const handleSubmit=async (e)=>{
        e.preventDefault();
        try{
            if(!check){
                alert("아이디 중복체크를 해주세요");
                return;
            }
            const formData = new FormData(e.target);
            const data = Object.fromEntries(formData.entries()); //폼객체를 일반 객체로 변환
            if(!data.mobile){
                data.mobile=null;
            }
            const response=await apiClient.post("/userinfo/join-userinfo", data);
            navigate("/create-userinfo/join/result");

        }catch(error){
            errorDisplay(error);
        }
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <p>고객아이디 : <input type="text" ref={userIdRef} name="userId" required/>
                    <button type="button" onClick={handleClick}>아이디 중복 체크</button></p>
                <p>비밀번호 : <input type="password" name="password" required/></p>
                <p>고객이름 : <input type="text" name="userName" required/></p>
                <p>전화번호 : <input type="text" name="mobile"/></p>
                <p>출생년도 : <input type="text" name="birthYear" required/></p>
                <p>신장 : <input type="text" name="height" required/></p>
                <p>거주지역 : <input type="text" name="addr" required/></p>
                <p>가입일자 : <input type="date" name="joinDate" required/></p>
                <p><button type="submit">저장</button></p>
            </form>
        </>
    );
}