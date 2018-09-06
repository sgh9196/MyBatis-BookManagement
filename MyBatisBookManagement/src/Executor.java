import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.domain.MappingInfo;

public class Executor implements MappingInfo {
	
	private static SqlSessionFactory sqlSessionFactory;
	private static SqlSession sqlSession;
	private static BookController bookController;
	private static Scanner sc = new Scanner(System.in);
	
	public static void mybatis_Setting() {
		
		try {
			
			// 마이바티스 설정 XML 파일 경로
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
		} catch(IOException e) {
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
		bookController.createBookTable(sqlSession, parameter);
		
		transactionClose();
		
	}
	
	public static void bookInsert() {
		
		transactionOpen();
		
		BookController bookContorller = new BookController();
		bookContorller.bookInsert(sqlSession, parameter);
		
		transactionClose();
		
	}
	
	public static void main(String[] args) {
	
		try {
			mybatis_Setting();
			createBookTable();
			
			
			while(true) {
				
				System.out.print("1. 등록   2. 조회   3. 수정   4. 삭제\n >> ");
				switch(sc.nextInt()) {
					case 1:
						bookInsert();
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
					default:
						break;
				}
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			transactionClose();
		}
		
	}
	
}
