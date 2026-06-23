const express = require('express')
const cors = require('cors')

const app = express()
const port = 3000

app.use(cors()) //괄호 안에 값에 따라 특정 요청에만 반응 가능 디폴트는 모든 요청

app.get('/my/:id', (req, res)=> {
    const req_p = req.params   // Path Variable
    const req_q = req.query    // Query String

    res.send(req_q)
    console.log(req_q)    
    console.log(req_p)
})

app.get('/sound/:name', (req, res)=>{
    const {name} = req.params
    if (name == 'dog'){
        res.json({'sound' : '멍멍'})
    }else if (name =='cat'){
        res.json({'sound': '야옹'})
    }

    console.log()
})

app.listen(port, ()=>{
    console.log("서버 실행중")
})