import {Link} from "react-router-dom";

export default function InitMenu(){
    return (
        <>
            <Link to={"/user-login"}>일반 로그인</Link>|<Link to={"/admin-login"}>관리자 로그인</Link>|<Link to={"/admin-join"}>관리자 추가</Link>
        </>
    );
}