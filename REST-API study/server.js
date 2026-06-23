// 1. express 하는 도구르 가져와 서버를 만들 준비를 한다
const express = require('express');
const fs = require('fs');
const app = express();
const PORT = 3000;

//2. 클라이언트가 보낸 json 데이터를 서버가 읽을 수 있도록 번역기를 켠다.
app.use(express.json());

const FILE_PATH = './books.json';

//파일을 읽어서 js 배열로 변환하는 함수
const readBooks = () =>{
    const data = fs.readFileSync(FILE_PATH, 'utf-8');
    return JSON.parse(data);
};

//js 배열을 다시 json 파일로 저장하는 함수
const writeBooks =(data) =>{
    fs.writeFileSync(FILE_PATH, JSON.stringify(data,null, 2), 'utf-8');
}


//4,GET 모든 책 조회
app.get('/api/books', (req, res) => {
    const books = readBooks();
    res.json(books);
});

//5. post 새로운 책 등록
app.post('/api/books', (req ,res) => {
    //예외처리 (방어벽:validation) : 제목이 없거나, 공백만 있거나, 문자열이 아닐 때
    if(req.body.title || req.body.title.trim() === '' || typeof req.body.title !=='string'){
        //400 Bad Request 에러와 함께 경고 메세지
        return res.status(400).json({message: "올바른 책 제목(문자열)을 입력해 주세요"})
    }

    const books = readBooks();

    //사용자가 보낸 책 제목은 req.body.title에 들어있음
    const newBook = {
        id: books.length > 0 ?books[books.length -1].id +1: 1,
        title : req.body.title
    };

    //배열에 새 책을 추가
    books.push(newBook);
    writeBooks(books);

    //성공했을 때 201 상태코드와 새 책의 데이터를 돌려줌
    res.status(201).json(newBook);
});

// ⭕ 정상 작동하는 DELETE 코드 예시
app.delete('/api/books/:id', (req, res) => { // 슬래시(/) 추가 및 (req, res) 순서 고침!
    const bookId = parseInt(req.params.id); // params에 's' 붙이고, 소문자 bookId로 생성

    // 생성할 때 쓴 이름(bookId)과 비교할 때 쓴 이름(bookId)을 똑같이 맞춰줍니다!
    books = books.filter(book => book.id !== bookId); 

    res.json({ message: `${bookId}번 책이 삭제되었습니다.` });
});

//put 특정 책 수정
app.put("/api/books/:id", (req, res) =>{
    const bookId = parseInt(req.params.id);

    const book = books.find(b => b.id === bookId);

    if (!book){
        return res.status(404).json({message: "책을 찾을 수 없습니다"});
    }

    book.title = req.body.title

    res.json(book)
})

app.listen(PORT, () => {
    console.log(`서버가 http://localhost:${PORT} 에서 실행 중입니다!`);
});