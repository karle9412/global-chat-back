import React  from "react"

const UserName = ({value, onChange}) => {

  const UserNameChangeInput = (event) => {
    onChange(event.target.value)
  }

  return (
    <div>
      <input
       type="text"
        placeholder="닉네임"
        value={value}
        onChange={(value) => UserNameChangeInput(value)}/>
    </div>
  )
}

export default UserName