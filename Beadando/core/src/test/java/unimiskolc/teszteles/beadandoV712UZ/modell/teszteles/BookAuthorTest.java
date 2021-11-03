package unimiskolc.teszteles.beadandoV712UZ.modell.teszteles;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import unimiskolc.teszteles.beadandoV712UZ.exceptions.InvalidAuthorException;
import unimiskolc.teszteles.beadandoV712UZ.modell.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class BookAuthorTest {

	@Parameters
	public static Collection<Object> data() {
		List<Object> data = new ArrayList<Object>();
		data.add(new String[] { "kisbetuvel kezdodok" });
		data.add(new String[] { "Elsonagy masodikkicsi" });
		data.add(new String[] { "elsokicsi Masodiknagy" });
		data.add(new String[] { "tobb mint ket szo" });
		data.add(new String[] { "KKetnagybetu Egymasmellett" });
		return data;
	}

	String author;

	public BookAuthorTest(String author) {
		this.author = author;
	}

	@Test(expected = InvalidAuthorException.class)
	public void testAuthorNames() throws InvalidAuthorException {
		Book book = new Book();
		book.setAuthor(author);
	}

}
