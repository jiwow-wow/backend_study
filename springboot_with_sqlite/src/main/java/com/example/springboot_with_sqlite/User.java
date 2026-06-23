
//회원 정보 데이터 구조 정의
package com.example.springboot_with_sqlite; // 해당 파일이 위치한 폴더의 경로

//데이터베이스(JPA)와 관련된 기능을 가져옴 (테이블 ID, 컬럼, 등 설정용) 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//롬복 라이브러리에서 Getter와 Setter 메서드를 자동으로 만들어주는 기능을 가져옴
import lombok.Getter;
import lombok.Setter;

@Entity
//[어노테이션]이 클래스가 db의 테이블과 매핑되는 객체임을 스프링에게 알린다
// 이 어노테이션이 붙으면 스프링 부트가 켜질 떄 이 구조대로 DB 에 테이블을 만든다
@Table(name = "users")
// [어노테이션] DB에 만들어질 테이블의 이름을 users로 지정
@Getter
//어노테이션 클래스 안의 변수들(id, name, age)의 값을 가져오는 getId(), getName(), getAge() 등의 메서드를 자동으로 생성

@Setter 
//어노테이션 변수들의 값을 변경하는 메서드를 자동으로 생성


public class User {
    @Id
    //어노테이션 이 필드가 테이블으 고유 식별자(primary key, 기본키)임을 지정
    //db의 주민등록번호 같은 역할

    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    //어노테이션 ID값을 개발자가 직접 넣지 않아도, DB에 데이터가 들어갈 때마다 1,2,3 ... 순서대로 자동 증가

    private Long id;
    private String name;
    private Integer age;

}
