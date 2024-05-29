package idusw.springboot.ldhmall.model;

import lombok.*;

// lombok annotation - boilerplate code를 다루기 위한 라이브러리
// POJO, Beans
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Member { //POJO : plain old java object = beans
    private Long idx;
    private String id;
    private String pw;
    private String name;
    private String email;
    // 접근 메소드들 setter / getter - boilerplate code

}
