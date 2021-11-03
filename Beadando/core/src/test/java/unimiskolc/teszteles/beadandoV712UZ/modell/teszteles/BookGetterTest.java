package unimiskolc.teszteles.beadandoV712UZ.modell.teszteles;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.*;

import unimiskolc.teszteles.beadandoV712UZ.enums.Genre;
import unimiskolc.teszteles.beadandoV712UZ.enums.Language;
import unimiskolc.teszteles.beadandoV712UZ.modell.Book;
import java.lang.*;

public class BookGetterTest {

	// Ebben a tesztosztályban a getter metódusok kerülnek tesztelésre
	Book book;

	@Before
	public void setUp() {
		book = new Book("Geza Gardonyi", 3600, 1200, LocalDate.parse("1901-01-01"), Genre.NOVEL, Language.HUNGARIAN,
				true, 12, "Book Publisher Office", "Egri csillagok");
	}

	@Test
	public void getAgelimitationTest() {
		int expected = 12;
		assertEquals(expected, book.getAgelimitation());
	}

	@Test
	public void getAuthorTest() {
		String expected = "Geza Gardonyi";
		assertEquals(expected, book.getAuthor());
	}

	@Test
	public void getGenreTest() {
		Genre expected = Genre.NOVEL;
		assertEquals(expected, book.getGenre());
	}

	@Test
	public void getLanguageTest() {
		Language expected = Language.HUNGARIAN;
		assertEquals(expected, book.getLanguage());
	}

	@Test
	public void getNumberofpageTest() {
		int expected = 1200;
		assertEquals(expected, book.getNumberofpage());
	}

	@Test
	public void getPriceTest() {
		double expected = 3600;
		int equal = Double.compare(expected, book.getPrice());
		assertEquals(equal, 0);
	}

	@Test
	public void getPubicationOfficeTest() {
		String expected = "Book Publisher Office";
		assertEquals(expected, book.getPublishingoffice());
	}

	@Test
	public void getPublicationTimeTest() {
		LocalDate expected = LocalDate.parse("1901-01-01");
		assertEquals(expected, book.getPublication());
	}

	@Test
	public void getTitleTest() {
		String expected = "Egri csillagok";
		assertEquals(expected, book.getTitle());
	}
	
	@Test
	public void getBuyableTest()
	{
		assertTrue(book.isBuyable());
	}

}
