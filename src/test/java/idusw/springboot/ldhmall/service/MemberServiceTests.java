package idusw.springboot.ldhmall.service;

import idusw.springboot.ldhmall.entity.MemberEntity;
import idusw.springboot.ldhmall.model.Member;
import idusw.springboot.ldhmall.repository.MemberJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

//Spring Boot 테스트 애플리케이션임을 부연 설명
@SpringBootTest
public class MemberServiceTests {
    // field DI (Dependency Injection) : (인터페이스로 부터 생성된) 구현체를 주입
    @Autowired
    MemberService memberService; // MemberService 인터페이스 구현체를 주입
    @Autowired
    MemberJpaRepository memberJpaRepository; // JapRepository 인터페이스 구현체를 주입

    @Test
    public void MembersRegister() {
        for (int i = 1; i < 10; i++){
            MemberEntity entity = MemberEntity.builder()
                    .id("id" + i)
                    .pw("cometrue")
                    .name("passion" + i)
                    .email("id" + i +"@induk.ac.kr")
                    .build();
            memberJpaRepository.save(entity);
        }

    }

    @Test
    public void testMemberRegister() {
        MemberEntity entity = MemberEntity.builder()
                .id("dream1")
                .pw("cometrue1")
                .name("comso1")
                .email("comso1@induk.ac.kr")
                .build();
        if(memberJpaRepository.save(entity) != null)
            System.out.println("register success");
        else
            System.out.println("register fail");

    }
    @Test
    public void testMemberLogin() {
        MemberEntity entity = MemberEntity.builder()
                .id("dream1")
                .pw("cometrue1")
                .build();
        Optional<MemberEntity> memberEntityOptional =
                memberJpaRepository.findByIdAndPw(entity.getId(), entity.getPw());
        if(memberEntityOptional.isPresent())
            System.out.println("login success" + memberEntityOptional.get().getId());
        else
            System.out.println("login fail");

    }
    @Test
    public void testMemberList() {
        List<MemberEntity> memberEntityList = memberJpaRepository.findAll();
        for(MemberEntity me : memberEntityList)
            System.out.println(me.getIdx() + "," + me.getPw() + ":" + me.getEmail());
    }
    @Test
    public void testMemberReadByIdx() {
        Member member = memberService.read(Member.builder().idx(6L).build());
        System.out.println(member.getEmail());
        /*
        Optional<MemberEntity> memberEntityOptional = memberJpaRepository.findById(6L);
        if(memberEntityOptional.isPresent())
            System.out.println(memberEntityOptional.get().getIdx() + ","
                    + memberEntityOptional.get().getPw() + ":"
                    + memberEntityOptional.get().getEmail());
         */
    }
    @Test
    public void testMembers(){
        List<Member> memberList = memberService.readList(); // Service -> Controller : dto, List<dto>
        for (Member m : memberList)
            System.out.println(m.getIdx() + ":" + m.getId() + m.getName() + "," + m.getEmail());
    }
    @Test
    public void testMemberUpdate(){
        Member dto = Member.builder()
                .idx(1L)
                .id("passion")
                .pw("cometrue")
                .name("passion")
                .email("passion@induk.ac.kr")
                .build();
        if(memberService.update(dto) > 0)
            System.out.println("업데이트 성공");
        else
            System.out.println("업데이트 실패");
    }
}
