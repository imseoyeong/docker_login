import {useOutletContext} from "react-router-dom";
import apiClient from "../api/axiosInstance";
import {useDispatch} from "react-redux";
import {addUserInfo, clearUserInfo, setUerInfoList} from "../store";
import {useNavigate} from "react-router-dom";
import errorDisplay from "../util/errorDisplay";

export default function DetailCondition(){
    const { addr, birthyear}=useOutletContext();
    const addrObj=(<div> 지역: <input type="text" name="addr"></input></div>);
    const birthYearObj=(<div> 출생년도: <input type="text" name="birthyear"></input></div>);
    const dispatch=useDispatch();
    const navigate=useNavigate();

    const handleSubmit=async (e)=>{
        e.preventDefault();
        let paramsData=null;
        let urlpath=null;
        if(addr){
            paramsData={addr:e.target.addr.value};
            urlpath="addr";
        }
        if(birthyear){
            paramsData={...paramsData, birthyear:e.target.birthyear.value};
            urlpath=urlpath? urlpath+"birthyear" : "birthyear";
        }
        try{
            await dispatch(clearUserInfo());
            const response= await apiClient.get("/userinfo/"+urlpath,{
                params:paramsData,
            });
            response.data.map(t=>dispatch(addUserInfo(t)));
            // dispatch(setUerInfoList(response.data));
            navigate("/display-userinfo");

        }catch(error){
            errorDisplay(error);
        }
    }
    return(
        <>
            <p>
            <form onSubmit={handleSubmit}>
                {addr&&addrObj}
                {birthyear&&birthYearObj}
                <button type="submit">검색</button>
            </form>
            </p>
        </>
    );
}