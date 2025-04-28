package th.ac.cmkl.bookrec.model;

import java.io.Serializable;
import java.util.Date;

/**
 * PurchaseHistory
 *
 * Represents a single purchase made by a user.
 *
 * Created by Taha Keler (TAHA) and Phyo Theingi (PHYO)
 * 27 April 2025
 */
public class PurchaseHistory implements Serializable
{
    /** Book purchased */
    private Book book;

    /** Date of purchase */
    private Date purchaseDate;

    public PurchaseHistory(Book book, Date purchaseDate)
    {
        this.book = book;
        this.purchaseDate = purchaseDate;
    }

    public Book getBook()
    {
        return book;
    }

    public Date getPurchaseDate()
    {
        return purchaseDate;
    }
}
