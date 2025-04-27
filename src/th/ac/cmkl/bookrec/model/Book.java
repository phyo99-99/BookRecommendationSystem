package th.ac.cmkl.bookrec.model;

import java.util.List;

/**
 * Book
 *
 * Represents a book with searchable and comparable attributes,
 * including title, author, category, page count, and a list of keywords.
 *
 * Created by Taha Keler and Phyo Theingi
 * April 27, 2025
 */

public class Book
{
    /** Title of the book */
    private String title;

    /** Author of the book */
    private String author;

    /** Category or genre of the book */
    private String category;

    /** Number of pages in the book */
    private int pageCount;

    /** List of keywords describing the book */
    private List<String> keywords;

    /**
     * Constructor for Book class.
     *
     * @param title The book's title.
     * @param author The book's author.
     * @param category The book's category or genre.
     * @param pageCount Number of pages.
     * @param keywords List of keywords for searching.
     */
    public Book(String title, String author, String category, int pageCount, List<String> keywords)
    {
        this.title = title;
        this.author = author;
        this.category = category;
        this.pageCount = pageCount;
        this.keywords = keywords;
    }

    /**
     * Checks if the book matches a search query.
     *
     * @param query The search query string.
     * @return true if title, author, or keywords match the query; false otherwise.
     */
    public boolean matchesQuery(String query)
    {
        return title.toLowerCase().contains(query.toLowerCase()) ||
               author.toLowerCase().contains(query.toLowerCase()) ||
               keywords.toString().toLowerCase().contains(query.toLowerCase());
    }

    /**
     * Determines if two books are similar based on category.
     *
     * @param other Another book to compare with.
     * @return true if both books are in the same category; false otherwise.
     */
    public boolean isSimilar(Book other)
    {
        return this.category.equalsIgnoreCase(other.category);
    }

    /**
     * Gets the title of the book.
     *
     * @return Title of the book.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Gets the author of the book.
     *
     * @return Author of the book.
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * Gets the category of the book.
     *
     * @return Category of the book.
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Gets the page count of the book.
     *
     * @return Number of pages.
     */
    public int getPageCount()
    {
        return pageCount;
    }

    /**
     * Gets the list of keywords for the book.
     *
     * @return List of keywords.
     */
    public List<String> getKeywords()
    {
        return keywords;
    }

    /**
     * String representation of the book.
     *
     * @return Formatted string with book details.
     */
    @Override
    public String toString()
    {
        return title + " by " + author + " (" + category + ")";
    }
}
