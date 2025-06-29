package LibraryManagementSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Register user");
            System.out.println("2. Login user");
            System.out.println("3. Add book");
            System.out.println("4. List all books");
            System.out.println("5. Search book by title");
            System.out.println("6. Delete book by ID");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    LibraryManagementSystem.UserManager.registerUser();
                    break;
                case 2:
                    LibraryManagementSystem.UserManager.loginUser();
                    break;
                case 3:
                    LibraryManagementSystem.BookManager.addBook();
                    break;
                case 4:
                    LibraryManagementSystem.BookManager.listAllBooks();
                    break;
                case 5:
                    LibraryManagementSystem.BookManager.searchBookByTitle();
                    break;
                case 6:
                    LibraryManagementSystem.BookManager.deleteBookById();
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
