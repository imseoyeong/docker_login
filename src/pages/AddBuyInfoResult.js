import {useNavigate} from "react-router-dom";

export default function AddBuyInfoResult(){
    const navigate = useNavigate();
    return (
        <>
            <h2>"정상적으로 저장되었습니다."</h2>
            <button onClick={(e) => {
                navigate("/create-userinfo/add-buyinfo");
            }}>계속입력</button>
        </>
    );
}