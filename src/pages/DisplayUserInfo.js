import {useSelector} from "react-redux";
import {Link} from "react-router-dom";

export default function DisplayUserInfo(){
    const userInfoList=useSelector(state=>state.userInfo.userInfoList);
    const list=userInfoList.map(t=> (
        <div key={t.userId}>
            <hr/>
            <p>고객아이디 : {t.userId}</p>
            <p>고객이름 : {t.userName}</p>
            <p>전화번호 : {t.mobile}</p>
            <p>출생년도 : {t.birthYear}</p>
            <p>신장 : {t.height}</p>
            <p>거주지역 : {t.addr}</p>
            <p>가입일자 : {t.joinDate}</p>
            <Link to={"/display-buyinfo/" + t.userId}>구매기록보기</Link>

            <hr/>
        </div>
    ));

    return (
        <>
            {list}
        </>
    );
}