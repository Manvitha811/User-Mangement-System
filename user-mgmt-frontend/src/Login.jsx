import "./Login.css";
import {Link,useNavigate} from "react-router-dom";
import {useState} from "react";
function Login(){
    const [email,setEmail]=useState("");
    const [password,setPassword]=useState("");
    const navigate=useNavigate();
    const handleLogin = async () => {
        if (email.trim() === "" || password.trim() === "") {
            alert("Please enter both email and password");
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email: email,
                    pwd: password
                })
            });

            const data = await response.json();
            if (response.ok && data.status === 200) {
                navigate("/dashboard", {
                    state: {
                        email: data.payload.email
                    }
                });
            } else {
                alert(data.msg || "Invalid email or password");
            }

            console.log("Login response:", data);

        } catch (error) {
            console.error("Login error:", error);
        }
    };

    return(
        <div className="login-container">
            <div className="login-card">
            <h2>User Login</h2>
                <p>Welcome back! Please log in to your account.</p>
            <input type={"email"} placeholder="Enter email" value={email} onChange={(event) => setEmail(event.target.value)}/>
                <p>Entered email: {email}</p>
                <input type={"password"} placeholder="Enter password" value={password} onChange={(event)=>setPassword(event.target.value)} />
            <button className="login-button" onClick={handleLogin}>Login</button>
                <p>
                    Don't have an account? <Link to="/register">Register here</Link>
                </p>

                <p>
                    <Link to="/forgot-password">Forgot Password?</Link>
                </p>

            </div>
        </div>

    );

}
export default Login;