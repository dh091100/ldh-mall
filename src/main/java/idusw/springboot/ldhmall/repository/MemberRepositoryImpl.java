package idusw.springboot.ldhmall.repository;

import idusw.springboot.ldhmall.model.Member;
import idusw.springboot.ldhmall.repository.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @Override
    public int insert(Member member) {
        return 0;
    }

    @Override
    public Member selectById(Member member) {
        // Database 처리 코드
        return null;}

    @Override
    public List<Member> selectList() {
        return null;
    }

    @Override
    public int update(Member member) {
        return 0;
    }

    @Override
    public int delete(Member member) {
        return 0;
    }
}
