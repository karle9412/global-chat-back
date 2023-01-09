
import React from "react";
import Reply from "./Reply/Reply";

export default class BoardDetail extends React.Component {
  render(){
    return(
      <>
        <div>유저 사진</div>
        <div>유저 이름</div>
        <div>게시글 제목</div>
        <div>게시글 사진</div>
        <div>게시글 내용</div>
        <Reply />

      </>
    )
  }
}