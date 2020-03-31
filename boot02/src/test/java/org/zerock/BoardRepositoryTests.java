package org.zerock;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Board;
import org.zerock.persistence.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTests {

	@Autowired
	private BoardRepository boardRepo;

	@Test
	public void inspect() {
		// 실제 객체의 클래스 이름
		Class<?> clz = boardRepo.getClass();

		System.out.println(clz.getName());

		// 클래스가 구현하고 있는 인터페이스 목록
		Class<?>[] interfaces = clz.getInterfaces();

		Stream.of(interfaces).forEach(inter -> System.out.println(inter.getName()));

		// 클래스의 부모 클래스
		Class<?> superClasses = clz.getSuperclass();

		System.out.println(superClasses.getName());
	}

	@Test
	public void testInsert() {

		Board board = new Board();
		board.setTitle("title");
		board.setContent("content");
		board.setWriter("user00");

		boardRepo.save(board);

	}

	@Test
	public void testRead() {

		//Board board = boardRepo.findOne(1L);

		//System.out.println(board);
		
		// Board 타입은 식별 데이터를 Long 타입으로 사용했으므로, '1L'과 같이 Long 타입으로 파라미터를 지정
		boardRepo.findById(1L).ifPresent((board)->{
			System.out.println(board);
		});

	}

	@Test
	public void testUpdate() {

		System.out.println("Read First.........................");
		Board board = boardRepo.findById(1L).get();

		System.out.println("Update Title.......................");
		board.setTitle("수정된 제목입니다");

		System.out.println("Call Save( ).......................");
		boardRepo.save(board);

	}

}
