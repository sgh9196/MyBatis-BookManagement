import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.domain.Book;

public class BookController {
	
	private Scanner sc = new Scanner(System.in);

	
	public Book bookInsert() {
		
		Book book = new Book();
		
		try {
			
			print("도서명 : "); book.setBookName(sc.next());
			print("장르 : "); book.setBookGenre(sc.next());
			print("출판사 : "); book.setBookPublisher(sc.next());
			print("저자명 : "); book.setBookAuthor(sc.next());
			print("출판일 : "); book.setBookDate(sc.next());
			print("ISBN : "); book.setBookIsbn(sc.next());
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return book;
	}
	
	public Book bookNameSelect(Book book) {
		print("도서명 : "); book.setBookName(sc.next());
		return book;
	}
	
	public Book bookNameUpdate(Book book) {
		print("도서명 : "); book.setBookName(sc.next());
		return book;
	}
	
	public Book bookGenreUpdate(Book book) {
		print("장르 : "); book.setBookGenre(sc.next());
		return book;
	}
	
	public Book bookPublisherUpdate(Book book) {
		print("출판사 : "); book.setBookPublisher(sc.next());
		return book;
	}
	
	public Book bookAuthorUpdate(Book book) {
		print("저자 : "); book.setBookAuthor(sc.next());
		return book;
	}
	
	public Book bookDateUpdate(Book book) {
		print("출판일 : "); book.setBookDate(sc.next());
		return book;
	}
	
	public Book bookDelete(Book book) {
		print("삭제 할 도서의 ISBN >> "); book.setBookIsbn(sc.next());
		return book;
	}
	
	public void print(String msg) {
		System.out.print(msg);
	}
	
}
