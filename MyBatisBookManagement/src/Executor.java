import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.domain.Book;
import org.mybatis.domain.MappingInfo;

public class Executor implements MappingInfo {

	private static SqlSessionFactory sqlSessionFactory;
	private static SqlSession sqlSession;

	private static BookController bookController;
	private static Book book;
	private static Scanner sc = new Scanner(System.in);

	public static void mybatis_Setting() {

		try {

			// 마이바티스 설정 XML 파일 경로
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void transactionOpen() {
		sqlSession = sqlSessionFactory.openSession();
	}

	public static void transactionClose() {
		sqlSession.close();
	}

	public static void createBookTable() {

		transactionOpen();

		bookController = new BookController();

		sqlSession.update(parameter + "BookMapper.createBookTable");
		sqlSession.commit();

		transactionClose();

	}

	public static void mBookInsert() {

		transactionOpen();

		bookController = new BookController();
		book = bookController.bookInsert();

		sqlSession.insert(parameter + "BookMapper.bookInsert", book);
		sqlSession.commit();

		transactionClose();

	}

	public static void mBookSelect() {
		
		try {
			
			transactionOpen();
			
			bookController = new BookController();
			book = new Book();
			
			book = bookController.bookNameSelect(book);
			
			book = sqlSession.selectOne(parameter+"BookMapper.bookNameSelect", book);
			
			System.out.println(book.toString());
			
			transactionClose();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public static void mBookUpdate() {

		try {

			transactionOpen();

			int result = 0;

			bookController = new BookController();

			book = new Book();
			print("수정 할 도서의 ISBN >> ");
			book.setBookIsbn(sc.next());

			System.out.print("1. 도서명   2. 장르   3. 출판사   4. 저자명   5. 출판일\n>> ");

			switch (sc.nextInt()) {
			case 1:
				book = bookController.bookNameUpdate(book);
				result = sqlSession.update(parameter + "BookMapper.bookNameUpdate", book);
				break;
			case 2:
				book = bookController.bookGenreUpdate(book);
				result = sqlSession.update(parameter + "BookMapper.bookGenreUpdate", book);
				break;
			case 3:
				book = bookController.bookPublisherUpdate(book);
				result = sqlSession.update(parameter + "BookMapper.bookPublisherUpdate", book);
				break;
			case 4:
				book = bookController.bookAuthorUpdate(book);
				result = sqlSession.update(parameter + "BookMapper.bookAuthorUpdate", book);
				break;
			case 5:
				book = bookController.bookDateUpdate(book);
				result = sqlSession.update(parameter + "BookMapper.bookDateUpdate", book);
				break;
			default:
				return;
			}

			checkISBN(result);

		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}

		transactionClose();

	}

	public static void mBookDelete() {

		try {

			transactionOpen();

			bookController = new BookController();
			book = new Book();

			book = bookController.bookDelete(book);

			int result = sqlSession.delete(parameter + "BookMapper.bookDelete", book);
			checkISBN(result);

		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}

		transactionClose();

	}

	public static void checkISBN(int result) {
		if (result == 0)
			print("ERR > 존재하는 ISBN이 없음\n");
		else {
			print("SUCCESS > 완료\n");
			sqlSession.commit();
		}
	}

	public static void print(String msg) {
		System.out.print(msg);
	}

	public static void main(String[] args) {

		try {

			mybatis_Setting();
			createBookTable();

			while (true) {

				System.out.print("1. 등록   2. 조회   3. 수정   4. 삭제\n>> ");
				switch (sc.nextInt()) {
					case 1:
						mBookInsert();
						break;
					case 2:
						mBookSelect();
						break;
					case 3:
						mBookUpdate();
						break;
					case 4:
						mBookDelete();
						break;
					default:
						System.exit(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transactionClose();
		}

	}

}
