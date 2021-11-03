package unimiskolc.teszteles.beadandoV712UZ.modell;

import java.time.*;
import java.util.*;
import unimiskolc.teszteles.beadandoV712UZ.exceptions.*;
import unimiskolc.teszteles.beadandoV712UZ.enums.*;

public class Book {
	
	protected String author;
	protected double price;
	protected int numberofpage;
	protected LocalDate publication;
	protected Genre genre;
	protected Language language;
	protected boolean buyable;
	protected int agelimitation;
	protected String publishingoffice;
	protected String title;


	@Override
	public int hashCode() {
		return Objects.hash(agelimitation, author, buyable, genre, language, numberofpage, price, publication,
				publishingoffice, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return agelimitation == other.agelimitation && Objects.equals(author, other.author) && buyable == other.buyable
				&& genre == other.genre && language == other.language && numberofpage == other.numberofpage
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(publication, other.publication)
				&& Objects.equals(publishingoffice, other.publishingoffice) && Objects.equals(title, other.title);
	}


	public Book(String author, double price, int numberofpage, LocalDate publication, Genre genre, Language language,
			boolean buyable, int agelimitation, String publishingoffice, String title) {
		super();
		this.author = author;
		this.price = price;
		this.numberofpage = numberofpage;
		this.publication = publication;
		this.genre = genre;
		this.language = language;
		this.buyable = buyable;
		this.agelimitation = agelimitation;
		this.publishingoffice = publishingoffice;
		this.title = title;
	}

	public Book() {

	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) throws InvalidAuthorException {
		String pattern = "^[A-Z]{1}[a-z]+ [A-Z]{1}[a-z]+";
		if (!author.matches(pattern)) {
			throw new InvalidAuthorException();
		} 
		else 
		{
			this.author = author;
		}
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) throws InvalidPriceException {
		if (price < 0) {
			throw new InvalidPriceException();
		} else

		{
			this.price = price;
		}
	}

	public int getNumberofpage() {
		return numberofpage;
	}

	public void setNumberofpage(int numberofpage) throws InvalidPageNumberExcepiton {
		if (numberofpage < 1) {
			throw new InvalidPageNumberExcepiton();
		} else {
			this.numberofpage = numberofpage;
		}
	}

	public LocalDate getPublication() {
		return publication;
	}

	public void setPublication(LocalDate publication) throws InvalidPublicationException {
		if (publication.isAfter(LocalDate.now()) || publication.isBefore(LocalDate.parse("1900-01-01"))) {
			throw new InvalidPublicationException();
		} else {
			this.publication = publication;
		}

	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public boolean isBuyable() {
		return buyable;
	}

	public void setBuyable(boolean buyable) {
		this.buyable = buyable;
	}

	public int getAgelimitation() {
		return agelimitation;
	}

	public void setAgelimitation(int agelimitation) throws InvalidAgeLimitationException {
		if (agelimitation < 6 || agelimitation > 18) {
			throw new InvalidAgeLimitationException();
		} else {
			this.agelimitation = agelimitation;
		}

	}

	public String getPublishingoffice() {
		return publishingoffice;
	}

	public void setPublishingoffice(String publishingoffice) throws InvalidPublishingOfficeException {
		if (publishingoffice.length() < 1) {
			throw new InvalidPublishingOfficeException();
		} else {
			this.publishingoffice = publishingoffice;
		}

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws InvalidTitleFormatException {
		if (title.length() < 1) {
			throw new InvalidTitleFormatException();
		} else {
			this.title = title;
		}
	}

}
