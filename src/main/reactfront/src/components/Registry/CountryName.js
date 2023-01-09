import React, { useMemo } from "react"
import countryList from "react-select-country-list";

const CountryName = ({value, onChange}) => {

  const CountryNameChangeInput = (event) => {
    onChange(event.target.value);
  };

  const options = countryList().getData()

  return (
    <select
      value={value}
      onChange={(value) => CountryNameChangeInput(value)}>
      {options.map((country) => <option key={country.value}>{country.label}</option>)}
    </select>
  )
}

export default CountryName  