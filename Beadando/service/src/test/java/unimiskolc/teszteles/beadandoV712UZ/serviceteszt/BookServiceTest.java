package unimiskolc.teszteles.beadandoV712UZ.serviceteszt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import unimiskolc.teszteles.beadandoV712UZ.dao.BookDao;
import unimiskolc.teszteles.beadandoV712UZ.enums.Genre;
import unimiskolc.teszteles.beadandoV712UZ.enums.Language;
import unimiskolc.teszteles.beadandoV712UZ.modell.Book;
import unimiskolc.teszteles.beadandoV712UZ.service.BookService;
import unimiskolc.teszteles.beadandoV712UZ.service.exceptions.BookAlreadyExist;
import unimiskolc.teszteles.beadandoV712UZ.service.exceptions.BookDoesNotExist;
import unimiskolc.teszteles.beadandoV712UZ.service.exceptions.NothingChangedException;

public class BookServiceTest {

	private BookService service;
	private Collection<Book>  books;
	private static BookDao mock;

	@Before
	public void setUp() throws BookDoesNotExist, BookAlreadyExist ,NothingChangedException
	{
		mock = Mockito.mock(BookDao.class);
		service = new BookService(mock);
		
		
		Book book = new Book("Mora Ferenc",1200,120,LocalDate.parse("1918-01-01"),Genre.NOVEL,Language.HUNGARIAN,true,6,"Mora","Kincskereso kiskodmon");
		Book book2 = new Book("Kosztolanyi Dezso",3600,80,LocalDate.parse("1926-01-01"),Genre.FANTASY,Language.HUNGARIAN,true,12,"Genius","Edes Anna");
		Book book3 = new Book("William Golding",2500,125,LocalDate.parse("1954-01-01"),Genre.THRILLER,Language.ENGLISH,true,6,"PublishOffice","Lord of the Flies");
		Book book4 = new Book("Jk Rowling",4500,250,LocalDate.parse("1999-01-01"),Genre.FANTASY,Language.ENGLISH,true,14,"Mora","Harry Potter");
		
		books = new ArrayList();
		books.add(book);
		books.add(book2);
		books.add(book3);
		books.add(book4);
	
		Mockito.when(mock.readAllBooks()).thenReturn(books);
		Mockito.when(mock.readBookbyTitle("Edes Anna")).thenReturn(book2);
		Mockito.doThrow(BookDoesNotExist.class).when(mock).readBookbyTitle("Nem letezik");
		Mockito.doThrow(BookAlreadyExist.class).when(mock).createBook(new Book("Kosztolanyi Dezso",3600,80,LocalDate.parse("1926-01-01"),Genre.FANTASY,Language.HUNGARIAN,true,12,"Genius","Edes Anna"));
		Mockito.doThrow(BookDoesNotExist.class).when(mock).deleteBook("Szia");
		Mockito.doThrow(NothingChangedException.class).when(mock).updateBook(new Book("Kosztolanyi Dezso",3600,80,LocalDate.parse("1926-01-01"),Genre.FANTASY,Language.HUNGARIAN,true,12,"Genius","Edes Anna"));
	}
	
	@Test
	public void readAllBookTest()
	{
		int expected = 4;
		assertEquals(service.getAllBook().size(),expected);
	}
	
	@Test
	public void readBookByIdTest() throws BookDoesNotExist
	{
		String expected = "Edes Anna";
		assertEquals(service.readaBookbyTitle(expected).getTitle(),expected);
	}
	
	@Test(expected = BookDoesNotExist.class)
	public void readBookByIdNotOkTest() throws BookDoesNotExist
	{
		service.readaBookbyTitle("Nem letezik");
	}
	
	@Test
	public void createBookTest() throws BookAlreadyExist
	{
		Book book1 = new Book("Uj Szerzo",1200,120,LocalDate.parse("1918-01-01"),Genre.NOVEL,Language.HUNGARIAN,true,6,"Mora","Valami cim");
		service.createBook(book1);
		Mockito.verify(mock,times(1)).createBook(book1);
	}
	
	@Test(expected = BookAlreadyExist.class)
	public void createBookNotOkTest() throws BookAlreadyExist
	{
		Book book = new Book("Kosztolanyi Dezso",3600,80,LocalDate.parse("1926-01-01"),Genre.FANTASY,Language.HUNGARIAN,true,12,"Genius","Edes Anna");
		service.createBook(book);
	}
	
	@Test
	public void deleteBookTest() throws BookDoesNotExist
	{
		String title = "Edes Anna";
		service.deleteBook(title);
		Mockito.verify(mock,times(1)).deleteBook(title);
	}
	
	@Test(expected = BookDoesNotExist.class)
	public void deleteBookNotOKTest() throws BookDoesNotExist
	{
		String title = "Szia";
		service.deleteBook(title);
	}
	
	@Test 
	public void updateBookTest() throws BookDoesNotExist
	{
		Book booktest = new Book("Mo Ferenc",1200,120,LocalDate.parse("1918-01-01"),Genre.NOVEL,Language.HUNGARIAN,true,6,"Mora","Kincskereso kiskodmon");
		service.updateBook(booktest);
		Mockito.verify(mock,times(1)).updateBook(booktest);
	}

	@Test(expected = NothingChangedException.class)
	public void updateBookNotOkTest() throws BookDoesNotExist
	{
		Book book = new Book("Kosztolanyi Dezso",3600,80,LocalDate.parse("1926-01-01"),Genre.FANTASY,Language.HUNGARIAN,true,12,"Genius","Edes Anna");
		service.updateBook(book);
	}

	@Test
	public void getBooksMoreExpensiveThanTest()
	{
		int expected = 1;
		Collection<Book> actual = service.getBooksMoreExpensiveThan(4000);
		assertEquals(expected,actual.size());
	}
	
	@Test
	public void getTheMostExpensiveBookTest()
	{
		double expected = 4500;
		Book actual = service.getTheMostExpensiveBook();
		assertEquals(Double.compare(expected, actual.getPrice()),0);
	}
	
	@Test
	public void getAllBuyableBookTest()
	{
		int expected = 4;
		Collection<Book> actual = service.getallBuyableBook();
		assertEquals(expected,actual.size());
	}
	
	@Test
	public void getBooksBetweenDatesTest()
	{
		int expected = 1;
		Collection<Book> actual = service.getBooksBetweenDates(LocalDate.parse("1919-01-01"), LocalDate.parse("1935-01-04"));
		assertEquals(expected,actual.size());
	}
	
	@Test
	public void getTheLongestBookTest()
	{
		int expected = 250;
		int actual = service.getTheLongestBook().getNumberofpage();
		assertEquals(expected,actual);
	}
	
	@Test
	public void getBooksBetweenAgeLimitationTest()
	{
		int expected = 2;
		int actual = service.getBooksBetweenAgeLimitation(5, 10).size();
		assertEquals(expected, actual);
	}
	
	@Test(expected =BookAlreadyExist.class )
	public void BookAlreadyExistTest() throws BookAlreadyExist
	{
		throw new BookAlreadyExist();
	}
	
	@Test(expected =BookDoesNotExist.class )
	public void BookDoesNotExistTest() throws BookDoesNotExist
	{
		throw new BookDoesNotExist();
	}
	
	@Test(expected =NothingChangedException.class )
	public void NothingChangedExceptionTest() throws NothingChangedException
	{
		throw new NothingChangedException();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
