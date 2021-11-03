package unimiskolc.teszteles.beadandoV712UZ.dao;

import java.util.Collection;
import unimiskolc.teszteles.beadandoV712UZ.modell.Book;
import unimiskolc.teszteles.beadandoV712UZ.service.exceptions.BookAlreadyExist;
import unimiskolc.teszteles.beadandoV712UZ.service.exceptions.BookDoesNotExist;

public interface BookDao {

	public Collection<Book> readAllBooks();

	public Book readBookbyTitle(String title)  throws BookDoesNotExist;

	public void createBook(Book book) throws BookAlreadyExist;

	public void updateBook(Book book) throws BookDoesNotExist;

	public void deleteBook(String title) throws BookDoesNotExist;

}
