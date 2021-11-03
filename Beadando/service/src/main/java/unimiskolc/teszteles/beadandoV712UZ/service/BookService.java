package unimiskolc.teszteles.beadandoV712UZ.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import unimiskolc.teszteles.beadandoV712UZ.dao.BookDao;
import unimiskolc.teszteles.beadandoV712UZ.modell.Book;
import unimiskolc.teszteles.beadandoV712UZ.service.exceptions.BookAlreadyExist;
import unimiskolc.teszteles.beadandoV712UZ.service.exceptions.BookDoesNotExist;

public class BookService {
	private BookDao dao;
	
	public BookService(BookDao dao)
	{
		super();
		this.dao = dao;
	}
	
	public Collection<Book> getAllBook(){
		return dao.readAllBooks();
	}
	
	public void deleteBook(String title) throws BookDoesNotExist
	{
		dao.deleteBook(title);
	}
	
	public Book readaBookbyTitle(String title) throws BookDoesNotExist
	{
		return dao.readBookbyTitle(title);
	}
	
	public void createBook(Book book) throws BookAlreadyExist
	{
		dao.createBook(book);
	}
	
	public void updateBook(Book book)  throws BookDoesNotExist
	{
		dao.updateBook(book);
	}
	

	public Collection<Book> getallBuyableBook()
	{
		Collection<Book> list = getAllBook();
		Collection<Book> buyable = list.stream().filter(a -> a.isBuyable()).collect(Collectors.toList());
		return buyable;
	}
	
	public Collection<Book> getBooksBetweenDates(LocalDate from,LocalDate to)
	{
		Collection<Book> list = getAllBook();
		Predicate<Book> pred = a -> a.getPublication().isAfter(from) && a.getPublication().isBefore(to);
		CollectionUtils.filter(list,pred);
		return list;
	}
	
	public Collection<Book> getBooksMoreExpensiveThan(double price)
	{
		Collection<Book> list = getAllBook();
		Collection<Book> list2 = new ArrayList<Book>();
		list.forEach(a -> {
			if(a.getPrice()>price)
			{
				list2.add(a);
			}
		});
		return list2;
	}
	
	public Book getTheMostExpensiveBook()
	{
		Collection<Book> list = getAllBook();
		return list.stream().max(Comparator.comparing(Book::getPrice)).get();
	}
	
	public Book getTheLongestBook()
	{
		Collection<Book> list = getAllBook();
		return list.stream().max(Comparator.comparingInt(a -> a.getNumberofpage())).get();
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Book> getBooksBetweenAgeLimitation(int from,int to)
	{
		Collection<Book> list = getAllBook();
		return  list.stream().filter(a -> a.getAgelimitation() >= from && a.getAgelimitation() <= to).collect(Collectors.toList());
		
	}
}
