import {useState, useRef} from "react";
import errorDisplay from "../util/errorDisplay";
import apiClient from "../api/axiosInstance";
import {useNavigate} from "react-router-dom";


export default function AddBuyInfo(){
    const [selectUserId, setSelectUserId]=useState(false);
    const userIdRef=useRef();
    const navigate = useNavigate();

    const handleUserSelect = async (e)=>{
        e.preventDefault();
        try{
            if(userIdRef.current.value.length === 0){
                alert("아이디를 입력하세요");
                return;
            }
            const response = await apiClient.get("/userinfo/check-id/" + userIdRef.current.value);
            if(response.status===250){
                userIdRef.current.disabled=true;
                setSelectUserId(true);
            }else{
                alert("없는 아이디입니다.");
                userIdRef.current.value="";
            }
        }catch(error){
            errorDisplay(error);
        }
    }

    const handleSumbit=async (e)=>{
        e.preventDefault();
        try{
            const formData=new FormData(e.target);
            const data=Object.fromEntries(formData.entries());
            data.userId=userIdRef.current.value;
            const response=await apiClient.post("/buyinfo/new", data);
            navigate("/create-userinfo/add-buyinfo/result");
        }catch(error){
            errorDisplay(error);
        }
    }
    return(
        <>
            <form onSubmit={handleSumbit}>
                고객아이디 : <input type="text" ref={userIdRef} name="userId" ></input>
                <button type="button" onClick={handleUserSelect}>아이디 선택</button>
            {selectUserId &&
                    <><p> 제품이름 : <input type="text" name="prodName" required></input></p>
                    <p> 그룹이름 : <input type="text" name="groupName"></input></p>
                    <p> 가격 : <input type="text" name="price" required></input></p>
                    <p> 구매개수 : <input type="text" name="amount" required></input></p>
                    <button type="submit">입력완료</button></>}
            </form>
        </>
    );
}