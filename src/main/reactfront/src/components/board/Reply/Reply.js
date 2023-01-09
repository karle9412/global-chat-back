import React from "react";

export default class Reply extends React.Component{
  render(){
    return(
      <>
      <div>유저 사진</div>
      <nav>
        <div>유저 이름</div>
        <div>작성일</div>
      </nav>
      <nav>
      <div>댓글 내용</div>
      <div>언어교환</div>
      <div>답글</div>
      <div>수정</div>
      <div>삭제</div>
      </nav>
      <div>
        번역
      </div>
      </>
    )
  }
}