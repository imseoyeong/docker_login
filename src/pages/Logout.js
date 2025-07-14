
import {useDispatch, useSelector} from "react-redux";
import {adminLogout, setToken, userLogout} from "../store";
import {useNavigate} from "react-router-dom";
import {useEffect} from "react";
import apiClient from "../api/axiosInstance";

export default function Logout(){
    const adminLoginFlag=useSelector(state=>state.userInfo.adminLoginFlag);
    const userLoginFlag =useSelector(state=>state.userInfo.userLoginFlag);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {
       const logoutData = async ()=>{
           try{
               const response = await apiClient.delete("/refresh-cookie");
               dispatch(setToken(null));

               if(adminLoginFlag){
                   dispatch(adminLogout());
               }
               if(userLoginFlag){
                   dispatch(userLogout());
               }
               navigate("/");
           }catch(error){
               console.log(error);
           }
       };
       logoutData();

    }, []);


    return (
        <>
        </>
    );
}