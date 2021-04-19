package hello.demo.domain;

import javax.persistence.*;

// jpa는 객체랑 orm(object relational mapping)
@Entity // jpa가 관리하는 entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Id is pk(priamry key)
    // @GeneratedValue is auto_increment, db가 알아서 생성해주는것은 IDENTITY
    private Long id; // 시스템이 저장할 ID

//    @Column(name="username"), 이렇게 명시해줄 수 도 있다
    private String name; // 고객이 입력할 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
