
import {Outlet} from "react-router-dom";
import {useState, useRef} from "react";
import {useNavigate} from "react-router-dom";

export default function ConditionSelect(){
    const [context, setContext]=useState(null);
    const flag=useRef(false);
    const navigate=useNavigate();
    const handleSubmit=(e)=>{
        e.preventDefault();
        flag.current=false;
        // setContext(null);
        let newContext=null;
        if(e.target.check[0].checked){
            newContext={addr:"addr"};
            flag.current=true;
        }
        if(e.target.check[1].checked){
            newContext={...newContext, birthyear:"birthyear"};
            flag.current=true;
        }
        setContext(newContext);
        navigate("/search/detail-condition");
    }
    return (
        <>
            <form onSubmit={handleSubmit}>
                <label htmlFor="addr">지역:</label>
                <input id="addr" type="checkbox" name="check"/>
                <label htmlFor="birthyear">출생년도</label>
                <input id="birthyear" type="checkbox" name="check"/>
                <button type="submit">조건선택</button>
            </form>
            {flag.current && <Outlet context={context}/>}
        </>
    );
}