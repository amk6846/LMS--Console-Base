import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private double price;

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Price: $" + price;
    }
}

public class LibraryManagementSystem {
    private ArrayList<Book> books;

    public LibraryManagementSystem() {
        books = new ArrayList<>();
    }

    public void addBook(String title, String author, double price) {
        if (price < 0) {
            System.out.println("Price cannot be negative. Book not added.");
            return;
        }
        Book book = new Book(title, author, price);
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void removeBook(int index) {
        if (index >= 0 && index < books.size()) {
            books.remove(index);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Invalid book index.");
        }
    }

    public void editBook(int index, String newTitle, String newAuthor, double newPrice) {
        if (index >= 0 && index < books.size()) {
            if (newPrice < 0) {
                System.out.println("Price cannot be negative. Book not updated.");
                return;
            }
            Book book = books.get(index);
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setPrice(newPrice);
            System.out.println("Book information updated successfully.");
        } else {
            System.out.println("Invalid book index.");
        }
    }

    public void searchBooks(String searchTerm) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println(i + 1 + ". " + books.get(i));
            }
        }
    }

    public void buyBook(int index, double userBalance) {
        if (index >= 0 && index < books.size()) {
            Book book = books.get(index);
            double bookPrice = book.getPrice();
            if (userBalance >= bookPrice) {
                userBalance -= bookPrice;
                System.out.println("Book purchased successfully.");
                System.out.println("Remaining balance: $" + userBalance);
            } else {
                System.out.println("Insufficient funds to buy this book.");
            }
        } else {
            System.out.println("Invalid book index.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryManagementSystem lms = new LibraryManagementSystem();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Edit Book");
            System.out.println("4. Search Books");
            System.out.println("5. Display Books");
            System.out.println("6. Buy Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter price: $");
                        double price = scanner.nextDouble();
                        scanner.nextLine();
                        lms.addBook(title, author, price);
                        break;
                    case 2:
                        System.out.print("Enter the book number to remove: ");
                        int removeIndex = scanner.nextInt();
                        scanner.nextLine();
                        lms.removeBook(removeIndex - 1);
                        break;
                    case 3:
                        System.out.print("Enter the book number to edit: ");
                        int editIndex = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter new title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter new author: ");
                        String newAuthor = scanner.nextLine();
                        System.out.print("Enter new price: $");
                        double newPrice = scanner.nextDouble();
                        scanner.nextLine();
                        lms.editBook(editIndex - 1, newTitle, newAuthor, newPrice);
                        break;
                    case 4:
                        System.out.print("Enter search term: ");
                        String searchTerm = scanner.nextLine();
                        lms.searchBooks(searchTerm);
                        break;
                    case 5:
                        lms.displayBooks();
                        break;
                    case 6:
                        System.out.print("Enter the book number to buy: ");
                        int buyIndex = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter your balance: $");
                        double userBalance = scanner.nextDouble();
                        scanner.nextLine();
                        lms.buyBook(buyIndex - 1, userBalance);
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}
