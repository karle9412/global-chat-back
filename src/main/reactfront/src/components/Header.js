import { Link } from "react-router-dom"
export default function Header() {
    return(
        <>
        <header>
            <nav>
                <Link to="/">Home</Link>
                <Link to="/profile">Profile</Link>
                <Link to="chatroom">ChatRoom</Link>
                <Link to="registry">Registry</Link>
                <Link to="signup">SignUp</Link>
                <Link to="/googleLoginButton"> GoogleLogin</Link>
            </nav>
        </header>
        </>
    )
}