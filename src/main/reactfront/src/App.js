import React from 'react'
import { BrowserRouter, Routes, Route} from 'react-router-dom';
import Home from './components/Home';
import Profile from './components/Profile';
import ChatRoom from './components/ChatRoom';
import Registry from './components/Registry';
import SignUp from './components/SignUp';
import GoogleLoginButton from './components/login/GoogleLoginButton'
import Board from './components/Board';

const App = () => {
  return (
    <>
   <BrowserRouter>
    <Routes>
     <Route path='/' element={<Home/>} />
     <Route path='/profile' element={<Profile/>} />
     <Route path='/chatroom' element={<ChatRoom/>} />
     <Route path='/registry' element={<Registry/>} />
     <Route path='/signup' element={<SignUp/>} />
     <Route path='/googleLoginButton' element={<GoogleLoginButton/>} />
     <Route path='/board' element={<Board/>}/>
   </Routes>
   </BrowserRouter>
    </>
  );
}

export default App;