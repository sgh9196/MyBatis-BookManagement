import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.domain.Book;

public class BookController {
	
	private Scanner sc = new Scanner(System.in);
	
	public void createBookTable(SqlSession sqlSession, String parameter) {
		
		try {
			
			sqlSession.update(parameter+"BookMapper.createBookTable");
			
			sqlSession.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void bookInsert(SqlSession sqlSession, String parameter) {
		
		try {
			
			Book book = new Book();
			
			System.out.print("도서명 : "); book.setBookName(sc.next());
			System.out.print("장르 : "); book.setBookGenre(sc.next());
			System.out.print("출판사 : "); book.setBookPublisher(sc.next());
			System.out.print("저자명 : "); book.setBookAuthor(sc.next());
			System.out.print("출판일 : "); book.setBookDate(sc.next());
			System.out.print("ISBN : "); book.setBookIsbn(sc.next());
			
			sqlSession.insert(parameter+"BookMapper.bookInsert", book);
			
			sqlSession.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
