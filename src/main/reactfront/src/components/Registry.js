import React from "react"
import countryList from "react-select-country-list";
import Local from "./Registry/Local";
import Email from "./Registry/Email";
import UserName from "./Registry/UserName";
import Passwd from "./Registry/Passwd";
import PhoneNumber from "./Registry/PhoneNumber";
import { useNavigate } from "react-router-dom";


export default class registry extends React.Component {
  constructor() {
    super()

    this.state = {
      local: "",
      options: countryList().getData(),
      phoneNumber: "",
      email: "",
      username: "",
      passwd: "",
    }
  }

  LocalChangeInput(local) {
    this.setState({
      local,
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

  UsernameChangeInput(username) {
    this.setState({
      username,
    })
  }

  passwdChangeInput(passwd) {
    this.setState({
      passwd,
    })
  }

  registry_submit = (event) => {
    event.preventDefault();
    const { local, phoneNumber, email, username, passwd } = this.state
    

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

    if (username.length === 0) {
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
        local: local,
        phoneNumber: phoneNumber,
        email: email,
        username: username,
        passwd: passwd,
      }),
    })
      .then(res => {
        alert("회원가입이 되었습니다.")
      })
  }

  render() {
    const { local, phoneNumber, email, username, passwd } = this.state

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
            <Local
              value={local}
              onChange={(value) => this.LocalChangeInput(value)} />
            <PhoneNumber
              value={phoneNumber}
              onChange={(value) => this.phoneNumberChangeInput(value)} />
            <Email
              value={email}
              onChange={(value) => this.emailChangeInput(value)}
              id="email" />
            <UserName
              value={username}
              onChange={(value) => this.UsernameChangeInput(value)} />
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