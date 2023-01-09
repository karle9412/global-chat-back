import React from "react"
import PhoneInput from "react-phone-input-2";
import "react-phone-input-2/lib/style.css";

const PhoneNumber = ({ value, onChange }) => {

  const PhoneNumberChangeInput = (event) => {
    onChange(event);
    console.log(event)
  };

  return (
    <>
      <div>
        <PhoneInput
          country={"ca"}
          value={value}
          onChange={(value) => PhoneNumberChangeInput(value)}
        />
      </div>
    </>
  )
}

export default PhoneNumber