package th.ac.cmkl.bookrec;

import th.ac.cmkl.bookrec.model.Book;
import th.ac.cmkl.bookrec.model.User;
import th.ac.cmkl.bookrec.util.FileStorageHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.text.SimpleDateFormat;

/**
 * Main
 *
 * Console interface for Book Recommendation System.
 * Users can search and add to cart, view and checkout cart, view purchase history, and get recommendations.
 *
 * Created by Taha Keler (TAHA)
 * 28 April 2025
 */
public class Main
{
    private static final String ADVENTURE = "Adventure";
    private static final String ROMANCE = "Romance";
    private static final String HISTORY = "History";
    private static final String MYSTERY = "Mystery";
    private static final String SCIENCE_FICTION = "Science Fiction";
    private static final String FANTASY = "Fantasy";
    private static final String BIOGRAPHY = "Biography";
    private static final String COMICS = "Comics";

    private static List<Book> cart = new ArrayList<>();

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
    
        List<User> users = FileStorageHandler.loadUsers();
        List<Book> books = FileStorageHandler.loadBooks();  // Make sure the books are loaded here
    
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
                    search(scanner, books);  // Pass books to search method
                    break;
                case "2":
                    viewCartAndCheckout(scanner, currentUser, books);  // Pass books to checkout method
                    break;
                case "3":
                    viewPurchaseHistory(scanner, currentUser);
                    break;
                case "4":
                    getRecommendations(scanner, currentUser, books);  // Pass books to recommendations method
                    break;
                case "5":
                    FileStorageHandler.saveUsers(users);
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select 1-5.");
                    break;
            }
        }
        scanner.close();
    }    

    /**
     * Creates a set of sample books if none exist.
     *
     * @param books the list of books to add sample entries to
     */
    private static void createSampleBooks(List<Book> books)
    {
        // Adventure Books
        books.add(new Book("Into the Wild", "Jon Krakauer", ADVENTURE, Arrays.asList("adventure", "mountains", "freedom")));
        books.add(new Book("The Call of the Wild", "Jack London", ADVENTURE, Arrays.asList("dogs", "wilderness", "survival")));
        books.add(new Book("Life of Pi", "Yann Martel", ADVENTURE, Arrays.asList("survival", "ocean", "faith")));
        books.add(new Book("Wild", "Cheryl Strayed", ADVENTURE, Arrays.asList("hiking", "journey", "self-discovery")));
    
        // Mystery Books
        books.add(new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", MYSTERY, Arrays.asList("mystery", "detective", "thriller")));
        books.add(new Book("Gone Girl", "Gillian Flynn", MYSTERY, Arrays.asList("psychological", "thriller", "mystery")));
        books.add(new Book("The Da Vinci Code", "Dan Brown", MYSTERY, Arrays.asList("mystery", "historical", "secrets")));
        books.add(new Book("Big Little Lies", "Liane Moriarty", MYSTERY, Arrays.asList("mystery", "secrets", "lies")));
    
        // Science Fiction Books
        books.add(new Book("The Martian", "Andy Weir", SCIENCE_FICTION, Arrays.asList("space", "survival", "mars")));
        books.add(new Book("Neuromancer", "William Gibson", SCIENCE_FICTION, Arrays.asList("cyberpunk", "hacking", "AI")));
        books.add(new Book("Fahrenheit 451", "Ray Bradbury", SCIENCE_FICTION, Arrays.asList("censorship", "dystopia", "future")));
        books.add(new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", SCIENCE_FICTION, Arrays.asList("space", "humor", "adventure")));
    
        // Fantasy Books
        books.add(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", FANTASY, Arrays.asList("magic", "school", "friendship")));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", FANTASY, Arrays.asList("dragons", "adventure", "journey")));
        books.add(new Book("A Song of Ice and Fire", "George R.R. Martin", FANTASY, Arrays.asList("fantasy", "dragons", "kingdoms")));
        books.add(new Book("The Name of the Wind", "Patrick Rothfuss", FANTASY, Arrays.asList("magic", "adventure", "legend")));
    
        // Biography Books
        books.add(new Book("Steve Jobs", "Walter Isaacson", BIOGRAPHY, Arrays.asList("apple", "innovation", "entrepreneurship")));
        books.add(new Book("Long Walk to Freedom", "Nelson Mandela", BIOGRAPHY, Arrays.asList("freedom", "apartheid", "South Africa")));
        books.add(new Book("Becoming", "Michelle Obama", BIOGRAPHY, Arrays.asList("memoir", "politics", "first lady")));
        books.add(new Book("The Glass Castle", "Jeanette Walls", BIOGRAPHY, Arrays.asList("poverty", "family", "childhood")));
    
        // Comics Books
        books.add(new Book("Batman: Year One", "Frank Miller", COMICS, Arrays.asList("gotham", "batman", "justice")));
        books.add(new Book("Watchmen", "Alan Moore", COMICS, Arrays.asList("superheroes", "vigilante", "society")));
        books.add(new Book("Spider-Man: Blue", "Jeph Loeb, Tim Sale", COMICS, Arrays.asList("spiderman", "love", "hero")));
        books.add(new Book("Maus", "Art Spiegelman", COMICS, Arrays.asList("WWII", "holocaust", "history")));
    
        // Romance Books
        books.add(new Book("Pride and Prejudice", "Jane Austen", ROMANCE, Arrays.asList("love", "marriage", "society")));
        books.add(new Book("The Fault in Our Stars", "John Green", ROMANCE, Arrays.asList("cancer", "love", "teenagers")));
        books.add(new Book("Outlander", "Diana Gabaldon", ROMANCE, Arrays.asList("time travel", "history", "romance")));
        books.add(new Book("The Notebook", "Nicholas Sparks", ROMANCE, Arrays.asList("first love", "tragedy", "memory")));
        books.add(new Book("Me Before You", "Jojo Moyes", ROMANCE, Arrays.asList("love", "disability", "sacrifice")));
    
        // History Books
        books.add(new Book("The Diary of a Young Girl", "Anne Frank", HISTORY, Arrays.asList("WWII", "diary", "history")));
        books.add(new Book("Unbroken", "Laura Hillenbrand", HISTORY, Arrays.asList("WWII", "survival", "determination")));
        books.add(new Book("The Wright Brothers", "David McCullough", HISTORY, Arrays.asList("aviation", "invention", "brothers")));
        books.add(new Book("Team of Rivals", "Doris Kearns Goodwin", HISTORY, Arrays.asList("Abraham Lincoln", "history", "leadership")));
        books.add(new Book("Alexander Hamilton", "Ron Chernow", HISTORY, Arrays.asList("history", "founding fathers", "politics")));
    
        FileStorageHandler.saveBooks(books);
    }
    
    
    /**
     * Handles user login or registration with password validation and username uniqueness.
     *
     * @param scanner the scanner for input
     * @param users the list of existing users
     * @return the logged-in or newly created user
     */
    private static User loginOrRegisterUser(Scanner scanner, List<User> users)
    {
        while (true)
        {
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();

            User foundUser = findUser(users, username);

            if (foundUser != null)
            {
                while (true)
                {
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine().trim();

                    if (foundUser.login(username, password))
                    {
                        System.out.println("Login successful.");
                        return foundUser;
                    }
                    else
                    {
                        System.out.println("Incorrect password. Try again.");
                    }
                }
            }
            else
            {
                System.out.println("\nUsername available. Creating new account.");

                String password;
                while (true)
                {
                    System.out.print("Create a password (at least 8 characters): ");
                    password = scanner.nextLine().trim();
                    if (password.length() >= 8)
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Password too short. Try again.");
                    }
                }

                User newUser = new User(username, password);
                users.add(newUser);
                System.out.println("Account created successfully!");
                return newUser;
            }
        }
    }

    /**
     * Prints the main menu options.
     */
    private static void printMainMenu()
    {
        System.out.println("\n-------------------------------------------");
        System.out.println(" Main Menu");
        System.out.println("-------------------------------------------");
        System.out.println("1. Search");
        System.out.println("2. View Cart and Checkout");
        System.out.println("3. View Purchase History");
        System.out.println("4. Get Recommendations");
        System.out.println("5. Exit");
        System.out.print("\nSelect an option (1-5): ");
    }

    /**
     * Allows user to search for books and add them directly to cart.
     * Supports typo correction and case-insensitive search.
     *
     * @param scanner the scanner for input
     * @param books the list of all books
     */
    private static void search(Scanner scanner, List<Book> books)
    {
        while (true)
        {
            System.out.println("\nAvailable Books:");
            for (int i = 0; i < books.size(); i++)
            {
                Book book = books.get(i);
                System.out.printf("%d. [%s] %s by %s%n", i + 1, book.getCategory(), book.getTitle(), book.getAuthor());
            }

            System.out.print("\nEnter a title or keyword to search (or 0 to return): ");
            String input = scanner.nextLine().trim();

            if (input.equals("0"))
            {
                return; // User pressed '0' to return to the menu
            }

            List<Book> matches = new ArrayList<>();
            String lowerInput = input.toLowerCase();

            // Check for matching books
            for (Book book : books)
            {
                if (book.getTitle().toLowerCase().contains(lowerInput)
                        || book.getAuthor().toLowerCase().contains(lowerInput)
                        || book.getCategory().toLowerCase().contains(lowerInput)
                        || book.getKeywords().toString().toLowerCase().contains(lowerInput))
                {
                    matches.add(book);
                }
            }

            // No exact match found
            if (matches.isEmpty())
            {
                System.out.println("\nNo exact match found. Did you mean:");
                for (Book book : books)
                {
                    if (similar(book.getTitle(), input))
                    {
                        System.out.println("- " + book.getTitle());
                    }
                }
                continue;
            }

            System.out.println("\nMatching Books:");
            for (int i = 0; i < matches.size(); i++)
            {
                Book book = matches.get(i);
                System.out.printf("%d. [%s] %s by %s%n", i + 1, book.getCategory(), book.getTitle(), book.getAuthor());
            }

            System.out.print("\nEnter the number of the book to add to cart (or 0 to search again): ");
            
            try
            {
                String selectedOption = scanner.nextLine().trim();

                if (selectedOption.equals("0"))
                {
                    continue; // Return to search again
                }

                int selection = Integer.parseInt(selectedOption);

                if (selection >= 1 && selection <= matches.size())
                {
                    Book selectedBook = matches.get(selection - 1);
                    cart.add(selectedBook); // Add book to the cart
                    FileStorageHandler.addBook(selectedBook, books); // Save updated books list
                    System.out.println("Book added to cart successfully!");
                }
                else
                {
                    System.out.println("Invalid number. Please try again.");
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    /**
     * View books in the cart and checkout if desired.
     *
     * @param scanner the scanner for input
     * @param user the current user
     * @param books the list of books to manage
     */
    private static void viewCartAndCheckout(Scanner scanner, User user, List<Book> books)
    {
        if (cart.isEmpty())
        {
            System.out.println("\nYour cart is empty.");
            return;
        }

        while (true)
        {
            System.out.println("\nYour Cart:");
            for (int i = 0; i < cart.size(); i++)
            {
                Book book = cart.get(i);
                System.out.printf("%d. [%s] %s by %s%n", i + 1, book.getCategory(), book.getTitle(), book.getAuthor());
            }

            System.out.println("\nOptions:");
            System.out.println("1. Remove a Book");
            System.out.println("2. Checkout");
            System.out.println("0. Return to Main Menu");
            System.out.print("\nSelect an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice)
            {
                case "1":
                    System.out.print("Enter the number of the book to remove: ");
                    try
                    {
                        int removeNum = Integer.parseInt(scanner.nextLine().trim());
                        if (removeNum >= 1 && removeNum <= cart.size())
                        {
                            Book removed = cart.remove(removeNum - 1); // Remove from cart
                            FileStorageHandler.removeBook(removed, books); // Remove from books list
                            System.out.println("Removed: " + removed.getTitle());
                        }
                        else
                        {
                            System.out.println("Invalid number.");
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Invalid input.");
                    }
                    break;

                case "2":
                    System.out.println("\nChecking out...");
                    for (Book book : cart)
                    {
                        user.buyBook(book); // Purchase each book
                    }
                    cart.clear(); // Clear cart after checkout
                    FileStorageHandler.saveBooks(books); // Save final list of books
                    System.out.println("Purchase completed successfully!");
                    return;

                case "0":
                    return;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    /**
     * Displays the user's purchase history.
     *
     * @param scanner the scanner for input
     * @param user the current user
     */
    private static void viewPurchaseHistory(Scanner scanner, User user)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        if (user.viewPurchaseHistory().isEmpty())
        {
            System.out.println("\nYou have not purchased any books yet.");
        }
        else
        {
            System.out.println("\nYour Purchase History:");
            for (var purchase : user.viewPurchaseHistory())
            {
                System.out.printf("- %s by %s (Purchased on: %s)%n",
                    purchase.getBook().getTitle(),
                    purchase.getBook().getAuthor(),
                    sdf.format(purchase.getPurchaseDate()));
            }
        }

        System.out.print("\nPress 0 to return: ");
        while (!scanner.nextLine().trim().equals("0"))
        {
            System.out.print("Invalid input. Press 0 to return: ");
        }
    }

    /**
     * Provides book recommendations to the user and allows returning to main menu.
     *
     * @param scanner the scanner for input
     * @param user the current user
     * @param books the list of all books
     */
    private static void getRecommendations(Scanner scanner, User user, List<Book> books)
    {
        List<Book> recommendations = user.getRecommendations(books);

        if (recommendations.isEmpty())
        {
            System.out.println("\nNo recommendations available yet. Try buying some books first.");
        }
        else
        {
            System.out.println("\nRecommended Books:");
            for (Book recBook : recommendations)
            {
                System.out.printf("- %s by %s%n", recBook.getTitle(), recBook.getAuthor());
            }
        }

        System.out.print("\nPress 0 to return: ");
        while (!scanner.nextLine().trim().equals("0"))
        {
            System.out.print("Invalid input. Press 0 to return: ");
        }
    }

    /**
     * Finds a user by username.
     *
     * @param users the list of users
     * @param username the username to search
     * @return the matching user, or null if not found
     */
    private static User findUser(List<User> users, String username)
    {
        for (User user : users)
        {
            if (user.getUsername().equalsIgnoreCase(username))
            {
                return user;
            }
        }
        return null;
    }

    /**
     * Helper function: checks if two strings are similar for typo correction.
     * 
     * @param text the correct text
     * @param input the user's input
     * @return true if they are similar enough, false otherwise
     */
    private static boolean similar(String text, String input)
    {
        text = text.toLowerCase();
        input = input.toLowerCase();

        if (text.contains(input) || input.contains(text))
        {
            return true;
        }

        int distance = levenshteinDistance(text, input);
        return distance <= 3; // Allow small typos
    }

    /**
     * Calculates the Levenshtein distance between two strings.
     *
     * @param a the first string
     * @param b the second string
     * @return the Levenshtein distance
     */
    private static int levenshteinDistance(String a, String b)
    {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++)
        {
            for (int j = 0; j <= b.length(); j++)
            {
                if (i == 0)
                {
                    dp[i][j] = j;
                }
                else if (j == 0)
                {
                    dp[i][j] = i;
                }
                else if (a.charAt(i - 1) == b.charAt(j - 1))
                {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else
                {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                    Math.min(dp[i - 1][j],
                                             dp[i][j - 1]));
                }
            }
        }
        return dp[a.length()][b.length()];
    }
}
