
//DB 조작 인터페이스

package com.example.springboot_with_sqlite;

//스프링 데이터 JPA가 제공하는 기본적인 CRUD 기능이 다 들어있는 JPA Repository를 가져옴
import org.springframework.data.jpa.repository.JpaRepository;
//이 인터페이스가 데이터베이스 접근용 클래스임을 나타내는 어노테이션을 가져옴
import org.springframework.stereotype.Repository;

@Repository
//어노테이션이 인터페이스가 DB에 접근하는 역할을 수행한다고 스프링에게 등록
public interface UserRepository extends JpaRepository<User, Long>{
    //JpaRepository<User, Long>을 상속받으면 우리가 조작할 대상은 'User'엔티티이고,
    //그 유저의 ID 타입은 Long 이라고 지정
    //비어있어 보이지만 상속 덕분에 기본적으로 save(), findAll(), findById(), .deleteBy()메서드가 자동으로 있음
    

    
}