import React from "react"

const Passwd = ({ value, onChange }) => {
  const passwdChangeInput = (event) => {
    onChange(event.target.value)
  }

  return (
    <div>
      <input
       type="password"
        placeholder="비밀번호"
        value={value}
        onChange={(value) => passwdChangeInput(value)}/>
    </div>
  )
}

export default Passwd