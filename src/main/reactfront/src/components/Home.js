import React from 'react'
import { useNavigate } from 'react-router-dom'


function Home() {
  const navigate = useNavigate();

  const startChat = () => {
    navigate('/signup');
  }

  return (
    
    <>
      <div>
        환영합니다!<br />
        <button type='button' onClick={startChat}>시작하기</button>
      </div>
    </>
    
  )
}

export default Home
