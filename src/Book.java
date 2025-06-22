import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Book 
{
    /*********************** Instance Fields **********************/
    private String title;
    private String author;
    private String ISBN;
    private String edition;
    private int publishYear;
    private int numOfCopies;
    
    /************** Static (Non-Instance) Fields ****************/

    // Declare an ArrayList called 'books' to hold Book objects. This list is shared among all instances of the class.
    private static ArrayList<Book> books = new ArrayList<>();

    // Declare a static Scanner object reference, intended for shared use.
    private static Scanner scanner = new Scanner(System.in);

    // Declare a static Path object representing the path to the CSV file "books_data.csv". This path is shared across all instances.
    private static Path filePath = Path.of("C:\\Users\\willj\\OneDrive\\Documents\\Projects\\Java Projects\\Assignment 3\\lib\\books_data.csv");

    /************************* Methods ************************/
    // A static method to read csv file, create objects of the file data, and then add the objects to the arrayList 'books'
    public static void loadBookData() {
        if (Files.exists(filePath)) {
            Files.lines(filePath).forEach(line -> {
                String[] data = line.split(",");
                if (data.length == 6) {
                    Book book = new Book(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]));
                    books.add(book);
                }
            });
        } else {
            System.out.println("CSV file not found!");
        }
    }

    // A default constructor method
    public Book() 
    {
        // Empty body
    }

    // Constructor method to initialize instance fields
    public Book(String title, String author, String ISBN, String edition, int publishYear, int numOfCopies) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.edition = edition;
        this.publishYear = publishYear;
        this.numOfCopies = numOfCopies;
    }

    // Getter and Setter for title
    public String getTitle() 
    {
        return title;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    // Getter and Setter for author
    public String getAuthor() 
    {
        return author;
    }

    public void setAuthor(String author) 
    {
        this.author = author;
    }

    // Getter and Setter for ISBN
    public String getISBN() 
    {
        return ISBN;
    }

    public void setISBN(String ISBN) 
    {
        this.ISBN = ISBN;
    }

    // Getter and Setter for edition
    public String getEdition() 
    {
        return edition;
    }

    public void setEdition(String edition) 
    {
        this.edition = edition;
    }

    // Getter and Setter for publishYear
    public int getPublishYear() 
    {
        return publishYear;
    }

    public void setPublishYear(int publishYear) 
    {
        this.publishYear = publishYear;
    }

    // Getter and Setter for numOfCopies
    public int getNumOfCopies() 
    {
        return numOfCopies;
    }

    public void setNumOfCopies(int numOfCopies) 
    {
        this.numOfCopies = numOfCopies;
    }

    // Instance method to add new books
    public void addBook() {
        System.out.println("\t\t\tA new book is being added ....");
        System.out.print("\t\t\tBook's Title: ");
        String title = scanner.nextLine();
        System.out.print("\t\t\tBook's Author: ");
        String author = scanner.nextLine();
        System.out.print("\t\t\tBook's ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("\t\t\tBook's Edition: ");
        String edition = scanner.nextLine();
        System.out.print("\t\t\tBook's Publish Year: ");
        int publishYear = Integer.parseInt(scanner.nextLine());
        System.out.print("\t\t\tBook's Number Of Copies: ");
        int numOfCopies = Integer.parseInt(scanner.nextLine());

        Book newBook = new Book(title, author, ISBN, edition, publishYear, numOfCopies);
        books.add(newBook);
        System.out.println("\t\t\tThe book has been added successfully!");
    }

    // Instance method to borrow books 
    public void isBorrowed(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.getNumOfCopies() > 0) {
                    book.setNumOfCopies(book.getNumOfCopies() - 1);
                    System.out.println("\tA library visitor requested '" + title + "', which is currently available and ready to get picked!");
                } else {
                    System.out.println("\tA library visitor requested '" + title + "', which is currently out of stock!");
                }
                return;
            }
        }
        System.out.println("\tThe book '" + title + "' was not found in the repository!");
    }

    // Instance method to return books 
    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setNumOfCopies(book.getNumOfCopies() + 1);
                System.out.println("\t'" + title + "' has been returned!");
                return;
            }
        }
        System.out.println("\tThe book '" + title + "' was not found in the repository!");
    }

    // Instance method to print all available books
    public void printAvailableBooks() {
        System.out.println("\tAvailable books:");
        for (Book book : books) {
            System.out.println("\t" + book.getTitle() + ", " + book.getAuthor() + ", " + book.getISBN() + ", " + book.getEdition() + ", " + book.getPublishYear() + ", " + book.getNumOfCopies());
        }
    }

    /************************* Main Method ***************************/
    public static void main(String[] args) throws IOException
    {
        // Call the 'loadBookData' method to create Book objects from file data
        loadBookData();

        // Create an instance of the Book class using the default constructor and assign it to a reference variable named 'bookReader'
        Book bookReader = new Book();

        // Call the method 'isBorrowed' on the 'bookReader' object to borrow book 'Arm live'  
        bookReader.isBorrowed("Arm live");

        // Call the method 'isBorrowed' on the 'bookReader' object to borrow book 'Arm live'
        bookReader.isBorrowed("Arm live");

        // Call the method 'isBorrowed' on the 'bookReader' object to borrow book 'Film class economy' 
        bookReader.isBorrowed("Film class economy");

        // Call the method 'addBook' on the 'bookReader' object to add a book 
        bookReader.addBook();

        // Call the method 'returnBook' on the 'bookReader' object to return book 'Nearly national'
        bookReader.returnBook("Nearly national");

        // Call the method 'returnBook' on the 'bookReader' object to return book 'Arm live'
        bookReader.returnBook("Arm live");

        // Call the method 'isBorrowed' on the 'bookReader' object to borrow book 'Arm live'
        bookReader.isBorrowed("Arm live");

        // Call the method 'printAvailableBooks' on the 'bookReader' object to print the all available books
        bookReader.printAvailableBooks();
    }
}