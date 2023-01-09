import React from 'react';
import BoardDetail from './board/BoardDetail';
import BoardWrite from './board/BoardWrite';
import RandomFriends from './board/RandomFriends';
import SearchBoard from './board/SearchBoard';
import Header from './Header';

export default class Board extends React.Component {
    constructor() {
        super()
    }
    render() {
        return (
            <>
                <Header />
                <div>
                    <h2>오늘의 친구를 만나보세요!</h2>
                </div>
                <RandomFriends />
                <nav>
                    <SearchBoard />
                    <BoardWrite />
                </nav>
                <div>
                    <BoardDetail />
                </div>
            </>
        )
    }

}