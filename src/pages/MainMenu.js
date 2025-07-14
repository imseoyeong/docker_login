import {Link} from "react-router-dom";
import {useSelector} from "react-redux";

export default function MainMenu(){
    const adminLoginFlag=useSelector(state=>state.userInfo.adminLoginFlag);
    return (
        <>
            <Link to={"/"}>홈</Link>|<Link to={"/search"}>검색</Link>|
            {adminLoginFlag && <><Link to={"/create-userinfo"}>고객정보추가</Link>|</>}<Link to={"/logout"}>로그아웃</Link>
        </>
    );
}