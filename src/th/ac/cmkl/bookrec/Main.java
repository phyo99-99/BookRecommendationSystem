package th.ac.cmkl.bookrec;

import th.ac.cmkl.bookrec.model.Book;
import th.ac.cmkl.bookrec.model.User;
import th.ac.cmkl.bookrec.util.FileStorageHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Main
 *
 * This class provides the console-based interface for the Book Recommendation System.
 * Users can search books, buy books, view their purchase history, and receive recommendations.
 *
 * Created by Taha Keler (TAHA)
 * 27 April 2025
 */
public class Main
{
    /**
     * Main entry point of the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        List<User> users = FileStorageHandler.loadUsers();
        List<Book> books = FileStorageHandler.loadBooks();

        if (books.isEmpty())
        {
            createSampleBooks(books);
        }

        System.out.println("===========================================");
        System.out.println("   Welcome to the Book Recommendation System");
        System.out.println("===========================================\n");

        User currentUser = loginOrRegisterUser(scanner, users);

        boolean running = true;
        while (running)
        {
            printMainMenu();
            String input = scanner.nextLine().trim();

            switch (input)
            {
                case "1":
                    searchBooks(scanner, currentUser, books);
                    break;
                case "2":
                    buyBook(scanner, currentUser, books);
                    break;
                case "3":
                    viewPurchaseHistory(currentUser);
                    break;
                case "4":
                    getRecommendations(currentUser, books);
                    break;
                case "5":
                    System.out.println("\nSaving your data...");
                    FileStorageHandler.saveUsers(users);
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
                    break;
            }
        }

        System.out.println("\nThank you for using the Book Recommendation System. Goodbye.");
        scanner.close();
    }

    /**
     * Creates sample books if no books are found in storage.
     *
     * @param books the list of books to add sample entries to
     */
    private static final String ADVENTURE = "Adventure";
    private static final String ROMANCE = "Romance";
    private static final String HISTORY = "History";
    private static final String MYSTERY = "Mystery";
    private static final String SCIENCE_FICTION = "Science Fiction";
    private static final String FANTASY = "Fantasy";
    private static final String BIOGRAPHY = "Biography";
    private static final String COMICS = "Comics";
    private static void createSampleBooks(List<Book> books)
    {
        // Adventure
        books.add(new Book("The Lost Expedition", "Amelia West", ADVENTURE, Arrays.asList("jungle", "exploration", "lost city")));
        books.add(new Book("Across the Desert", "James North", ADVENTURE, Arrays.asList("desert", "journey", "survival")));
        books.add(new Book("Island of Secrets", "Helen Shore", ADVENTURE, Arrays.asList("island", "mystery", "hidden treasure")));

        // Romance
        books.add(new Book("Love in the Rain", "Sophie Moore", ROMANCE, Arrays.asList("love", "rain", "serendipity")));
        books.add(new Book("Winter Hearts", "Daniel Grey", ROMANCE, Arrays.asList("winter", "holiday romance", "snow")));
        books.add(new Book("A Parisian Affair", "Isabelle Dupont", ROMANCE, Arrays.asList("paris", "affair", "romance")));

        // History
        books.add(new Book("The Rise of Kingdoms", "Arthur Fielding", HISTORY, Arrays.asList("kingdoms", "medieval", "rulers")));
        books.add(new Book("World at War", "Henry Douglas", HISTORY, Arrays.asList("world war", "history", "conflict")));
        books.add(new Book("Ancient Civilizations", "Laura Grant", HISTORY, Arrays.asList("ancient", "civilizations", "archaeology")));

        // Mystery
        books.add(new Book("Whispers in the Dark", "Thomas King", MYSTERY, Arrays.asList("mystery", "secrets", "darkness")));
        books.add(new Book("The Hidden Clue", "Victoria Lane", MYSTERY, Arrays.asList("clues", "investigation", "detective")));
        books.add(new Book("Shadows of the Past", "Michael Reed", MYSTERY, Arrays.asList("past", "shadows", "revelations")));

        // Science Fiction
        books.add(new Book("Journey to Mars", "Christopher Hale", SCIENCE_FICTION, Arrays.asList("mars", "space", "journey")));
        books.add(new Book("The Last AI", "Natalie Brooks", SCIENCE_FICTION, Arrays.asList("AI", "future", "technology")));
        books.add(new Book("Galactic Frontiers", "Kevin Stone", SCIENCE_FICTION, Arrays.asList("galaxy", "adventure", "exploration")));

        // Fantasy
        books.add(new Book("The Dragon's Quest", "Emily Rivers", FANTASY, Arrays.asList("dragons", "magic", "quest")));
        books.add(new Book("Sword of Light", "Brian Storm", FANTASY, Arrays.asList("sword", "hero", "light")));
        books.add(new Book("Enchanted Realms", "Chloe Winter", FANTASY, Arrays.asList("enchantment", "realms", "fantasy worlds")));

        // Biography
        books.add(new Book("A Life Remembered", "Olivia Smith", BIOGRAPHY, Arrays.asList("life story", "memoir", "personal journey")));
        books.add(new Book("The Innovator's Journey", "Jacob Miles", BIOGRAPHY, Arrays.asList("innovation", "entrepreneur", "success")));
        books.add(new Book("Against All Odds", "Sophia Blake", BIOGRAPHY, Arrays.asList("struggle", "resilience", "victory")));

        // Comics
        books.add(new Book("Heroes United", "Stan Harper", COMICS, Arrays.asList("heroes", "team", "comic action")));
        books.add(new Book("Masked Crusaders", "Liam Black", COMICS, Arrays.asList("masked", "vigilantes", "justice")));
        books.add(new Book("Tales from Metro City", "Grace Lane", COMICS, Arrays.asList("city", "crime", "heroes")));

        FileStorageHandler.saveBooks(books);
    }

    /**
     * Handles user login or registration.
     *
     * @param scanner the scanner for input
     * @param users the list of all existing users
     * @return the logged-in user
     */
    private static User loginOrRegisterUser(Scanner scanner, List<User> users)
    {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        User currentUser = findUser(users, username);

        if (currentUser == null)
        {
            System.out.println("\nNew user detected. Creating your profile...");
            currentUser = new User(username, password);
            users.add(currentUser);
        }
        else
        {
            if (!currentUser.login(username, password))
            {
                System.out.println("\nIncorrect password. Exiting...");
                System.exit(0);
            }
            System.out.println("\nLogin successful. Welcome back, " + username + ".");
        }

        return currentUser;
    }

    /**
     * Prints the main menu options.
     */
    private static void printMainMenu()
    {
        System.out.println("\n-------------------------------------------");
        System.out.println(" Main Menu");
        System.out.println("-------------------------------------------");
        System.out.println("1. Search Books");
        System.out.println("2. Buy a Book");
        System.out.println("3. View Purchase History");
        System.out.println("4. Get Recommendations");
        System.out.println("5. Exit");
        System.out.print("\nPlease select an option (1-5): ");
    }

    /**
     * Allows user to search for books by keyword.
     *
     * @param scanner the scanner for input
     * @param user the current user
     * @param books the list of all books
     */
    private static void searchBooks(Scanner scanner, User user, List<Book> books)
    {
        System.out.print("\nEnter search keyword: ");
        String keyword = scanner.nextLine().trim();
        List<Book> searchResults = user.searchBooks(books, keyword);

        if (searchResults.isEmpty())
        {
            System.out.println("\nNo matching books found.");
        }
        else
        {
            System.out.println("\nSearch Results:");
            for (int i = 0; i < searchResults.size(); i++)
            {
                Book book = searchResults.get(i);
                System.out.printf("%d. [%s] %s by %s%n", i + 1, book.getCategory(), book.getTitle(), book.getAuthor());
            }
        }
    }

    /**
     * Allows user to buy a book from the list.
     *
     * @param scanner the scanner for input
     * @param user the current user
     * @param books the list of all books
     */
    private static void buyBook(Scanner scanner, User user, List<Book> books)
    {
        System.out.println("\nAvailable Books:");
        for (int i = 0; i < books.size(); i++)
        {
            Book book = books.get(i);
            System.out.printf("%d. [%s] %s by %s%n", i + 1, book.getCategory(), book.getTitle(), book.getAuthor());
        }

        System.out.print("\nEnter book number to purchase: ");
        try
        {
            int selection = Integer.parseInt(scanner.nextLine().trim());
            if (selection >= 1 && selection <= books.size())
            {
                user.buyBook(books.get(selection - 1));
                System.out.println("\nBook purchased successfully.");
            }
            else
            {
                System.out.println("\nInvalid book selection.");
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("\nInvalid input. Please enter a valid number.");
        }
    }

    /**
     * Displays the user's purchase history.
     *
     * @param user the current user
     */
    private static void viewPurchaseHistory(User user)
    {
        List<Book> history = new ArrayList<>();
        for (var purchase : user.viewPurchaseHistory())
        {
            history.add(purchase.getBook());
        }

        if (history.isEmpty())
        {
            System.out.println("\nYou have not purchased any books yet.");
        }
        else
        {
            System.out.println("\nYour Purchase History:");
            for (Book book : history)
            {
                System.out.printf("- [%s] %s by %s%n", book.getCategory(), book.getTitle(), book.getAuthor());
            }
        }
    }

    /**
     * Provides book recommendations to the user.
     *
     * @param user the current user
     * @param books the list of all books
     */
    private static void getRecommendations(User user, List<Book> books)
    {
        List<Book> recommendations = user.getRecommendations(books);

        if (recommendations.isEmpty())
        {
            System.out.println("\nNo recommendations available yet. Try buying a book first.");
        }
        else
        {
            System.out.println("\nRecommended For You:");
            for (Book recBook : recommendations)
            {
                System.out.printf("- [%s] %s by %s%n", recBook.getCategory(), recBook.getTitle(), recBook.getAuthor());
            }
        }
    }

    /**
     * Finds a user by username.
     *
     * @param users the list of all users
     * @param username the username to search for
     * @return the matching User or null if not found
     */
    private static User findUser(List<User> users, String username)
    {
        for (User user : users)
        {
            if (user.getUsername().equals(username))
            {
                return user;
            }
        }
        return null;
    }
}
