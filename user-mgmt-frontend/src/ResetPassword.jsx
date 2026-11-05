import {useState} from "react";
import "./ResetPassword.css";
import {Link} from "react-router-dom";
function ResetPassword(){
    const [email,setEmail]=useState("");
    const[oldPwd,setOldPwd]=useState("");
    const[newPwd,setNewPwd]=useState("");
    const [confirmPwd, setConfirmPwd] = useState("");
    const handleReset=(event)=>{
        event.preventDefault();
        if (
            email.trim() === "" ||
            oldPwd === "" ||
            newPwd === "" ||
            confirmPwd === ""
        ) {
            alert("Please fill in all fields");
            return;
        }
        if(newPwd !== confirmPwd) {
            alert("Passwords do not match");
            return;
        }
        alert("passwords match!");

    };
    return(
        <div className="reset-container">
            <form className="reset-card" onSubmit={handleReset}>
                <h2>Reset Password</h2>
                <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)}/>
                <input type="password" placeholder="old Password" value={oldPwd} onChange={(e) => setOldPwd(e.target.value)}/>
                <input type="password" placeholder="New Password" value={newPwd} onChange={(e) => setNewPwd(e.target.value)}/>
                <input type="password" placeholder="Confirm Password" value={confirmPwd} onChange={(e) => setConfirmPwd(e.target.value)}/>
                <button type="submit">Reset Password</button>
                <p className="back-login">Remembered your password?<Link to="/login">Login here</Link></p>
            </form>
        </div>


    );
}
export default ResetPassword;