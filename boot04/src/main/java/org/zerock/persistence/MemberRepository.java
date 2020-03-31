package org.zerock.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String> {

	// 매소드 리턴 타입은 List 형태, JPQL에서 엔티티 타입 뿐 아니라, 다른 자료형도 반환 가능
	// List는 결과의 Row 수, Object[]는 칼럼을 의미
	@Query("SELECT m.uid, count(p) FROM Member m LEFT OUTER JOIN Profile p "+
	" ON m.uid = p.member WHERE m.uid = ?1 GROUP BY m")
	public List<Object[]> getMemberWithProfileCount(String uid);
	
	
	@Query("SELECT m, p FROM Member m LEFT OUTER JOIN Profile p " + 
	" ON m.uid = p.member WHERE m.uid = ?1 AND p.current = true")
	public List<Object[]> getMemberWithProfile(String uid);
}
