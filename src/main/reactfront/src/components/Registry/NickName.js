import React  from "react"

const NickName = ({value, onChange}) => {

  const NickNameChangeInput = (event) => {
    onChange(event.target.value)
  }

  return (
    <div>
      <input
       type="text"
        placeholder="닉네임"
        value={value}
        onChange={(value) => NickNameChangeInput(value)}/>
    </div>
  )
}

export default NickName