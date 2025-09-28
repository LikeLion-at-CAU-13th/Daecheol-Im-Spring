package com.example.likelion13th.repository;

import com.example.likelion13th.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
    Optional<Member> findByEmail(String email);
    // 이름 prefix로 검색
    List<Member> findByNameStartingWith(String prefix);
    // 성인 + 이름 오름차순 + 페이징
    Page<Member> findByAgeGreaterThanEqualOrderByNameAsc(int age, Pageable pageable);

}

/*@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll(){
        return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }

    public  Optional<Member> findByEmail(String email){
        List<Member> result = em.createQuery("SELECT m FROM Member m WHERE m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
        return result.stream().findFirst();
    }

    public List<Member> findByName(String name){
        return em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public void delete(Member member) {
        em.remove(member);
    }
}*/
