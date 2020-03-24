//첫 번째로 Table 데이터 맵핑을 위한 Model을 하나 만듭니다.
//com.rest.api 하위에 entity라는 이름의 package를 생성하고 User Class를 하나 생성합니다.
//entity는 dbtable 간의 구조와 관계를 JPA가 요구하는 형태로 만든 model입니다.
//테이블에 있는 칼럼 값들의 정보, 테이블 간의 연관 관계(1:N, N:1 등) 정보를 담고 있습니다.
//참고로 Lombok 어노테이션을 사용하면 소스가 간단해 지므로 웬만하면 Lombok 사용을 추천합니다.

package caleb.rest.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder // builder를 사용할수 있게 합니다.
@Entity // jpa entity임을 알립니다.
@Getter // user 필드값의 getter를 자동으로 생성합니다.
@NoArgsConstructor // 인자없는 생성자를 자동으로 생성합니다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
@Table(name = "user") // 'user' 테이블과 매핑됨을 명시

public class User {
    @Id //primaryKey임을 알림.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk생성 전략을 DB에 위임한다는 의미. mysql로 보면 pk 필드를 auto_increment로 설정해 놓은 경우임.
    private long msrl;
    @Column(nullable = false, unique = true, length = 30) //uid column을 명시함. 필수이고 유니크한 필드이며, 길이는 30.
    private String uid;
    @Column(nullable = false, length = 100) //name column을 명시함. 필수이고 길이는 100.
    private String name;
}