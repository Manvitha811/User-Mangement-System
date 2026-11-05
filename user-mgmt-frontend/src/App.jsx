import { useState } from 'react'
import heroImg from './assets/hero.png'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import Welcome from "./Welcome";
import Dashboard from "./Dashboard";
import Login from "./Login";
import{BrowserRouter,Routes,Route,Navigate} from "react-router-dom";
import Register from "./Register";
import ForgotPassword from "./ForgotPassword";
import ResetPassword from "./ResetPassword";
function App() {
   return(

   <BrowserRouter>
       <Routes>
           <Route path="/" element={<Navigate to="/login" replace />} />
           <Route path="/login" element={<Login/>}/>
           <Route path="/register" element={<Register/>}/>
           <Route path="/forgot-password" element={<ForgotPassword/>}/>
           <Route path="/reset-password" element={<ResetPassword/>}/>
           <Route path="/dashboard" element={<Dashboard/>}/>
       </Routes>
   </BrowserRouter>

     );

}

export default App
