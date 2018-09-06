package org.mybatis.domain;

public class Book {
	
	private int bookNo;
	private String bookName;
	private String bookGenre;
	private String bookPublisher;
	private String bookAuthor;
	private String bookDate;
	private String bookIsbn;
	
	public int getBookNo() { return bookNo; }
	public void setBookNo(int bookNo) { this.bookNo = bookNo; }
	
	public String getBookName() { return bookName; }
	public void setBookName(String bookName) { this.bookName = bookName; }
	
	public String getBookGenre() { return bookGenre; }
	public void setBookGenre(String bookGenre) { this.bookGenre = bookGenre; }
	
	public String getBookPublisher() { return bookPublisher; }
	public void setBookPublisher(String bookPublisher) { this.bookPublisher = bookPublisher; }
	
	public String getBookAuthor() { return bookAuthor; }
	public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor; }
	
	public String getBookDate() { return bookDate; }
	public void setBookDate(String bookDate) { this.bookDate = bookDate; }
	
	public String getBookIsbn() { return bookIsbn; }
	public void setBookIsbn(String bookIsbn) { this.bookIsbn = bookIsbn; }
	
	@Override
	public String toString() {
		return "Book [bookNo=" + bookNo + ", bookName=" + bookName + ", bookGenre=" + bookGenre + ", bookPublisher="
				+ bookPublisher + ", bookAuthor=" + bookAuthor + ", bookDate=" + bookDate + ", bookIsbn=" + bookIsbn
				+ "]";
	}
	
}
