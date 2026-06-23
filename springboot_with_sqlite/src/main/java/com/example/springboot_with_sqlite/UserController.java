
//CRUD 주소 매핑
//웹 요청을 받아 처리하는 컨트롤러

package com.example.springboot_with_sqlite;

//롬복에서 비어있는 생성자를 자동으로 만들어주는 기능을 가져옴
import lombok.RequiredArgsConstructor;
//웹 요청(GET, POST, PUT, DELETE) 및 파라미터 처리를 위한 스프링 웹 관련 기능들을 가져옴
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//자바에서 여러개의 객체를 리스트 형태로 담기 위한 데이터 타입을 가져옴
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
//어노테이션이 클래스가 웹 요청을 받는 웹 서버의 컨트롤러임을 지정함
//자바 객체를 리턴하면 사용자가 보기 편하게 JSON 형식의 텍스트로 변환하여 브라우저나 Posrman에 응답해줌

@RequestMapping("/api/users")
//이 컨트롤러에 들어오는 모든 요청 주소 앞에 /api/users를 기본으로 붙이겠다는 뜻
//ex: 데이터 조회 주소는 자동으로 http:/localhost:8000/api/users가 됨

@RequiredArgsConstructor
//final 키워드가 붙은 변수는(여기서는 userRepository)를 자동으로 스프링이 연결해주는 생성자를 만듦
// 의존성 주입(DI)

public class UserController {
    //final을 붙여서 생성할 때 DB 창구인 UserRepository를 스프링으로부터 강제로 주입받아 연결
    private final UserRepository userRepository;

    //1. create : 회원 등록 -post
    @PostMapping
    //어노테이션 주소창에 아무것도 안 붙인 post 요청이 들어오면 실행되는 메서드
    public User creatUser(@RequestBody User user){
        //RequestBody : 사용자가 보낸 json 데이터

        return userRepository.save(user);
        //주입받은 DB창구의 .save()기능을 써서 전달받은 user 객체를 SQLite에 저장하고
        //저장 완료된 결과를 다시 리턴
    }

    //2. read : 전체 회원 조회 -get
    @GetMapping
    //어노테이션 그냥 'GET/api/users' 주소로 요청이 들어왔을 때 실행되는 메서드
    public List<User> getAllUsers(){
        
        return userRepository.findAll();
        //DB 창구의 findeAll()기능을 사용하여 
        //SQLite의 users 테이블에 있는 모든 데이터를 긁어모아 리스트로 리턴
    }   
    
    //3. update : 회원 정보 수정 -put
    @PutMapping("/{id}")
    //어노테이션 PUT/api/users/1 처럼 주소 뒤에 수정할 유저의 ID 번호를 붙여서 요청이 올 때 실행
    public User updateUser(@PathVariable("id") Long id, @RequestBody User userDetails){
        // @PathVariable : 주소창에 들어온 id 값을 추출해서 자바 변수인 long id 에 넣음
        // @RequestBody : 사용자가 새로 수정한 이름과 나이가 담긴 json 데이터를 자바 객체 (userDetails)로 받아옴

        User user = userRepository.findById(id).orElseThrow();
        // .findAll(id)를 써서 해당 번호의 유저를 DB에서 찾음
        //데이터가 없으면 .orElseThrow()에 의해 에러를 발생
        user.setName(userDetails.getName());
        user.setAge(userDetails.getAge());
        
        return userRepository.save(user);

    }

    //4. Delete
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        //주소창의 id 값을 가져와서 long id에 넣음

        userRepository.deleteById(id);
        return "Deleted: "+id;
    }


}
