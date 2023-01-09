import React, { useMemo } from "react"
import countryList from "react-select-country-list";

const Local = ({value, onChange}) => {

  const LocalChangeInput = (event) => {
    onChange(event.target.value);
  };

  const options = countryList().getData()

  return (
    <select
      value={value}
      onChange={(value) => LocalChangeInput(value)}>
      {options.map((country) => <option key={country.value}>{country.label}</option>)}
    </select>
  )
}

export default Local