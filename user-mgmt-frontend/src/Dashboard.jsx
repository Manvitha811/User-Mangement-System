import "./Dashboard.css";
import {useState,useEffect} from "react";
import { useLocation } from "react-router-dom";
function Dashboard(){
    const[quote,setQuote]=useState(null);
    const location = useLocation();
    const email = location.state?.email;
    const [country, setCountry] = useState("");
    const [state, setState] = useState("");
    const [city, setCity] = useState("");
    useEffect(() => {
        fetch("http://localhost:8080/quote")
            .then(response => response.json())
            .then(data => {
                setQuote(data.payload);
            })
            .catch(error => {
                console.error("Error fetching quote:", error);
            });
    }, []);
    const [profile, setProfile] = useState(null);
    useEffect(() => {
        if (!email) return;

        fetch(`http://localhost:8080/user/${encodeURIComponent(email)}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error("Failed to fetch user details");
                }
                return response.json();
            })
            .then(data => {
                console.log("User profile:", data);
                setProfile(data.payload);
            })
            .catch(error => {
                console.error("Profile error:", error);
            });

    }, [email]);
    useEffect(() => {
        if (!profile) return;

        fetch("http://localhost:8080/countries")
            .then(response => response.json())
            .then(data => {
                const selectedCountry = data.payload.find(
                    item => item.countryId === profile.countryId
                );

                setCountry(selectedCountry?.name || "");
            })
            .catch(error => console.error("Country error:", error));

    }, [profile]);
    useEffect(() => {
        if (!profile) return;

        fetch(`http://localhost:8080/states/${profile.countryId}`)
            .then(response => response.json())
            .then(data => {
                const selectedState = data.payload.find(
                    item => item.stateId === profile.stateId
                );

                setState(selectedState?.stateName || "");
            })
            .catch(error => console.error("State error:", error));

    }, [profile]);
    return(
        <div className="dashboard-container">
            <header className="dashboard-header">
                <h2>User Management </h2>
                <button>Logout</button>
            </header>
            <main className="dashboard-content">
                {quote && (
                    <div className="quote-card">
                        <h3>Quote of the Day</h3>
                        <p>"{quote.quote}"</p>
                        <p>— {quote.author}</p>
                    </div>
                )}
                <h2>Welcome to Dashboard</h2>
                <p>Manage your profile and account details here.</p>
                {profile?(
                <div className="profile-card">
                    <h3>Profile Details</h3>

                    <p><strong>Name:</strong> {profile.name}</p>
                    <p><strong>Email:</strong> {profile.email}</p>
                    <p><strong>Phone:</strong> {profile.phno}</p>
                    <p><strong>Country:</strong> {country}</p>
                    <p><strong>State:</strong> {state}</p>
                    <p><strong>City:</strong> {city}</p>
                    <button onClick={() => setProfile({
                        name: "Rahul",
                        email: "rahul@gmail.com",
                        phone: "9999999999",
                        country: "India",
                        state: "Telangana",
                        city: "Hyderabad"
                    })}>
                        Change Profile
                    </button>
                </div>):(
                    <p>Loading profile...</p>
                )}
            </main>

        </div>
    );
}
export default Dashboard;