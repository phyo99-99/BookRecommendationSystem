package th.ac.cmkl.bookrec.model;

import java.util.List;

/**
 * Represents a book with searchable and comparable attributes.
 */
public class Book {
    private String title;
    private String author;
    private String category;
    private int pageCount;
    private List<String> keywords;

    public Book(String title, String author, String category, int pageCount, List<String> keywords) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.pageCount = pageCount;
        this.keywords = keywords;
    }

    public boolean matchesQuery(String query) {
        return title.contains(query) || author.contains(query) || keywords.contains(query);
    }

    public boolean isSimilar(Book other) {
        return this.category.equals(other.category);
    }

    // Getters and setters omitted for brevity
}
