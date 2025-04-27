package th.ac.cmkl.bookrec.model;

import java.util.Date;

/**
 * PurchaseHistory
 *
 * Represents a record of a book purchased by a user, including
 * the purchased book and the date of purchase.
 *
 * Created by Taha Keler and Phyo Theingi
 * April 27, 2025
 */

public class PurchaseHistory
{
    /** The book that was purchased */
    private Book book;

    /** The date when the book was purchased */
    private Date purchaseDate;

    /**
     * Constructs a purchase history entry with the given book and date.
     *
     * @param book The book that was purchased.
     * @param purchaseDate The date the book was purchased.
     */
    public PurchaseHistory(Book book, Date purchaseDate)
    {
        this.book = book;
        this.purchaseDate = purchaseDate;
    }

    /**
     * Returns the purchased book.
     *
     * @return Book that was purchased.
     */
    public Book getBook()
    {
        return book;
    }

    /**
     * Returns the date when the book was purchased.
     *
     * @return Purchase date.
     */
    public Date getPurchaseDate()
    {
        return purchaseDate;
    }
}
