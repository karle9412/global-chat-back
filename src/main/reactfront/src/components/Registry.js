import React from "react"
import countryList from "react-select-country-list";
import CountryName from "./Registry/CountryName";
import Email from "./Registry/Email";
import NickName from "./Registry/NickName";
import Passwd from "./Registry/Passwd";
import PhoneNumber from "./Registry/PhoneNumber";
import { useNavigate } from "react-router-dom";


export default class registry extends React.Component {
  constructor() {
    super()

    this.state = {
      countryName: "",
      options: countryList().getData(),
      phoneNumber: "",
      email: "",
      nickName: "",
      passwd: "",
    }
  }

  CountryNameChangeInput(countryName) {
    this.setState({
      countryName,
    })
  }

  phoneNumberChangeInput(phoneNumber) {
    this.setState({
      phoneNumber,
    })
  }

  emailChangeInput(email) {
    this.setState({
      email,
    })
  }

  nickNameChangeInput(nickName) {
    this.setState({
      nickName,
    })
  }

  passwdChangeInput(passwd) {
    this.setState({
      passwd,
    })
  }

  registry_submit = (event) => {
    event.preventDefault();
    const { countryName, phoneNumber, email, nickName, passwd } = this.state
    

    if (email.length === 0) {
      alert("이메일을 입력하지 않았습니다.");
      return false;
    }

    let regex = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
    if (regex.test(email) === false) {
      alert("이메일 형식이 올바르지 않습니다.");
      this.setState({
        email: "",
      });
      return false;
    }

    if (nickName.length === 0) {
      alert("닉네임을 입력하지 않았습니다.");
      return false;
    }

    if (passwd.length === 0) {
      alert("비밀번호를 입력하지 않았습니다.");
      return false;
    }

    fetch('/User/Registry', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        country: countryName,
        phoneNumber: phoneNumber,
        email: email,
        nickName: nickName,
        passwd: passwd,
      }),
    })
      .then(res => {
        alert("회원가입이 되었습니다.")
      })
  }

  render() {
    const { countryName, phoneNumber, email, nickName, passwd } = this.state

    return (
      <>
        <div>
          <h2>글랜챗</h2>
          <h4>가입하실 이메일과 비밀번호를 설정해주세요</h4>
        </div>
        <div>
          {/* email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .phonenumber(userDTO.getPhonenumber())
                    .local(userDTO.getLocal())
                    .role(userDTO.getRole()) */}
          <form>
            <CountryName
              value={countryName}
              onChange={(value) => this.CountryNameChangeInput(value)} />
            <PhoneNumber
              value={phoneNumber}
              onChange={(value) => this.phoneNumberChangeInput(value)} />
            <Email
              value={email}
              onChange={(value) => this.emailChangeInput(value)}
              id="email" />
            <NickName
              value={nickName}
              onChange={(value) => this.nickNameChangeInput(value)} />
            <Passwd
              value={passwd}
              onChange={(value) => this.passwdChangeInput(value)} />
            <input type="submit" value="회원가입" onClick={this.registry_submit}></input>
          </form>
        </div>
      </>
    )
  }

}