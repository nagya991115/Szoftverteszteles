package unimiskolc.teszteles.beadandoV712UZ.modell.teszteles;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import unimiskolc.teszteles.beadandoV712UZ.enums.Genre;
import unimiskolc.teszteles.beadandoV712UZ.enums.Language;
import unimiskolc.teszteles.beadandoV712UZ.exceptions.InvalidAgeLimitationException;
import unimiskolc.teszteles.beadandoV712UZ.exceptions.InvalidAuthorException;
import unimiskolc.teszteles.beadandoV712UZ.exceptions.InvalidPageNumberExcepiton;
import unimiskolc.teszteles.beadandoV712UZ.exceptions.InvalidPriceException;
import unimiskolc.teszteles.beadandoV712UZ.exceptions.InvalidPublicationException;
import unimiskolc.teszteles.beadandoV712UZ.exceptions.InvalidPublishingOfficeException;
import unimiskolc.teszteles.beadandoV712UZ.exceptions.InvalidTitleFormatException;
import unimiskolc.teszteles.beadandoV712UZ.modell.Book;

public class BookSetterTest {
	Book book;

	@Before
	public void setUp() {
		book = new Book("Geza Gardonyi", 3600, 1200, LocalDate.parse("1901-01-01"), Genre.NOVEL, Language.HUNGARIAN,
				true, 12, "Book Publisher Office", "Egri csillagok");
	}

//---------------------------------------------------------------------------------------------------------------------
	@Test
	public void setPriceOKTest() throws InvalidPriceException {
		double price = 3000;
		book.setPrice(price);
		int equal = Double.compare(price, book.getPrice());
		assertEquals(equal, 0);
	}

	@Test(expected = InvalidPriceException.class)
	public void setPriceNotOKTest() throws InvalidPriceException {
		double price = -2500;
		book.setPrice(price);
	}

//---------------------------------------------------------------------------------------------------------------------

	@Test
	public void setAgeLimitationOKTest() throws InvalidAgeLimitationException {
		int age = 14;
		book.setAgelimitation(age);
		assertEquals(age, book.getAgelimitation());
	}

	@Test(expected = InvalidAgeLimitationException.class)
	public void setAgeLimitationNOTOKTest() throws InvalidAgeLimitationException {
		int age = -2;
		book.setAgelimitation(age);
	}

	@Test(expected = InvalidAgeLimitationException.class)
	public void setAgeLimitationNOTOKTest_v2() throws InvalidAgeLimitationException {
		int age = 22;
		book.setAgelimitation(age);
	}
//---------------------------------------------------------------------------------------------------------------------

	@Test
	public void setBuyableTest() {
		book.setBuyable(false);
		assertFalse(book.isBuyable());
	}

//---------------------------------------------------------------------------------------------------------------------	

	@Test
	public void setNumberofpageOKTest() throws InvalidPageNumberExcepiton {
		int page = 120;
		book.setNumberofpage(page);
		assertEquals(page, book.getNumberofpage());
	}

	@Test(expected = InvalidPageNumberExcepiton.class)
	public void setNumberofpageNotOKTest() throws InvalidPageNumberExcepiton {
		int page = 0;
		book.setNumberofpage(page);
	}
//---------------------------------------------------------------------------------------------------------------------

	@Test
	public void setPublicationOKTest() throws InvalidPublicationException {
		LocalDate publishingtime = LocalDate.parse("2010-01-01");
		book.setPublication(publishingtime);
		assertEquals(publishingtime, book.getPublication());
	}

	@Test(expected = InvalidPublicationException.class)
	public void setPublicationNotOKTest() throws InvalidPublicationException {
		LocalDate publishingtime = LocalDate.parse("2030-01-01");
		book.setPublication(publishingtime);
	}

	@Test(expected = InvalidPublicationException.class)
	public void setPublicationNotOKTest_v2() throws InvalidPublicationException {
		LocalDate publishingtime = LocalDate.parse("1800-01-01");
		book.setPublication(publishingtime);
	}
//---------------------------------------------------------------------------------------------------------------------

	@Test
	public void setPublishingOfficeOKTest() throws InvalidPublishingOfficeException {
		String office = "Valami";
		book.setPublishingoffice(office);
		boolean ok = false;
		if (book.getPublishingoffice().equals(office)) {
			ok = true;
		}
		assertTrue(ok);
	}

	@Test(expected = InvalidPublishingOfficeException.class)
	public void setPublishingOfficeNotOKTest() throws InvalidPublishingOfficeException {
		String office = "";
		book.setPublishingoffice(office);
	}

//---------------------------------------------------------------------------------------------------------------------
	@Test
	public void setTitleOKTest() throws InvalidTitleFormatException {
		String title = "A";
		book.setTitle(title);
		assertEquals(title, book.getTitle());
	}

	@Test(expected = InvalidTitleFormatException.class)
	public void setTitleNotOKTest() throws InvalidTitleFormatException {
		String title = "";
		book.setTitle(title);
	}
//---------------------------------------------------------------------------------------------------------------------	

	@Test
	public void setGenreTest() {
		Genre genre = Genre.FANTASY;
		book.setGenre(genre);
		assertEquals(genre, book.getGenre());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setGenreNotOKTest() throws IllegalArgumentException {
		String genre = "NotExisting";
		book.setGenre(Genre.valueOf(genre));
	}

//---------------------------------------------------------------------------------------------------------------------		
	@Test
	public void setLanguageTest() {
		Language language = Language.ENGLISH;
		book.setLanguage(language);
		assertEquals(language, book.getLanguage());
	}
   
	@Test(expected = IllegalArgumentException.class)
	public void setLanguageNotOKTest() throws IllegalArgumentException {
		String language = "NotExisting";
		book.setLanguage(Language.valueOf(language));
	}

//---------------------------------------------------------------------------------------------------------------------
	@Test
	public void setAuthorOKTest() throws InvalidAuthorException {
		String author = "Nagy Adam";
		if (author.matches("^[A-Z]{1}[a-z]+ [A-Z]{1}[a-z]+")) {
			book.setAuthor(author);
			assertEquals(author, book.getAuthor());
		}

	}

	@Test
	public void EmptyConstructorTest() {
		new Book();
	}

	@Test
	public void hashcodeTest() {
		int expected = -57972338;
		assertEquals(expected, book.hashCode());
	}

	@Test
	public void bookequalnullobjectTest() {
		assertFalse(book.equals(null));
	}

	@Test
	public void bookEqualAnotherBookTest() {
		Book booktest = new Book();
		assertFalse(book.equals(booktest));
	}
	
	@Test
	public void differentBookTest() throws InvalidTitleFormatException
	{
		Book booktest = new Book();
		booktest.setTitle("Nem Egri csillagok");
		assertFalse(book.equals(booktest));
	}
	
	@Test
	public void sameBookTest() {
		assertTrue(book.equals(book));
	}
	
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void bookEqualotherTypeTest() {
	assertFalse(book.equals(new Object[] {10}));
	}

}
