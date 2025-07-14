import {NavLink, Link, Outlet} from "react-router-dom";

export default function CreateUserInfo(){
    return (
        <>
            <hr/>
            <Link to={"/create-userinfo/join"}>가입 정보 추가</Link>&nbsp;&nbsp;&nbsp;
            <Link to={"/create-userinfo/add-buyinfo"}>구매 기록 추가</Link>
            <hr/>
            <Outlet></Outlet>
        </>
    );
}