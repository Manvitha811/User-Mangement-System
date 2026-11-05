import {useState} from "react";
function ForgotPassWord(){
    const [email,setEmail]=useState("");
   const handleSubmit=(event)=>{
       event.preventDefault();
       if(email.trim()===""){
           alert("please enter your email address");
           return;
       }
       alert("email enterd:"+ email);
   };
    return(
        <div>
            <form onSubmit={handleSubmit}>
                <h2>Forgot Password</h2>
                <p>Enter your email address and we'll send you a link to reset your password.</p>
                <input type="email" placeholder="Enter email" value={email} onChange={(event)=>setEmail(event.target.value)} />
                <button type="submit">Send Reset Link</button>
            </form>
        </div>

    );
}
export default ForgotPassWord;