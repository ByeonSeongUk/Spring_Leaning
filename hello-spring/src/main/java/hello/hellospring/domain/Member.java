package hello.hellospring.domain;

import javax.persistence.*;

@Entity // JPA가 관리하는 Entity 명시
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name") // DB에 컬럼명과 매핑
    private String name;

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
