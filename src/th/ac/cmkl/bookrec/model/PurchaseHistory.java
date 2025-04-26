package th.ac.cmkl.bookrec.model;

import java.util.Date;

/**
 * Represents a single purchase made by a user.
 */
public class PurchaseHistory {

    private Book book;
    private Date purchaseDate;

    /**
     * Constructs a purchase history entry with the given book and date.
     *
     * @param book the book that was purchased
     * @param purchaseDate the date the book was purchased
     */
    public PurchaseHistory(Book book, Date purchaseDate) {
        this.book = book;
        this.purchaseDate = purchaseDate;
    }

    /**
     * Returns the purchased book.
     *
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * Returns the purchase date.
     *
     * @return the purchase date
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }
}
