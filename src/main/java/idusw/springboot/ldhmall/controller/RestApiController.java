package idusw.springboot.ldhmall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// Annotation (애노테이션, 어노테이션) : java, lombok, spring (O)
// 컴파일러에서 정확한 처리를 위해 저공하는 부연 설명
// vs comment(주석) : 소스코드를 이해하는데 도움을 주기위한 부연 설명
// @RestController 는 현재 클래스가 Rest 형식으로 동작하는 요청 흐름을 제어하는 클래스임을 컴파일러에게 알려줌
public class RestApiController {
    // http://<host-ip>:<port>/api/v1을 get 방식으로 요청하는 경우 처리
    @GetMapping(value={"api/v1", "api/v1/"})
    public String getRestApiv1(){
        return "<h1> rest 방식으로 처리한 결과를 반환 : response 객체를 사용함.</h1>";
    }
}
