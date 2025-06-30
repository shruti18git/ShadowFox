import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    String name;
    String phone;
    String email;

    Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
    }
}

public class ContactManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();
        int choice;

        System.out.println(" === Welcome to the Simple Contact Management System! === ");

        do {
            System.out.println("\n *** MENU *** ");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit (to stop the program)");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    contacts.add(new Contact(name, phone, email));
                    System.out.println("Contact added successfully!");
                }

                case 2 -> {
                    if (contacts.isEmpty()) {
                        System.out.println("No contacts to display.");
                    } else {
                        System.out.println("Contact List:");
                        for (int i = 0; i < contacts.size(); i++) {
                            System.out.println("\nContact " + (i + 1) + ":");
                            contacts.get(i).display();
                        }
                    }
                }

                case 3 -> {
                    if (contacts.isEmpty()) {
                        System.out.println("No contacts to update.");
                    } else {
                        System.out.print("Enter contact number to update: ");
                        int index = sc.nextInt();
                        sc.nextLine();
                        if (index > 0 && index <= contacts.size()) {
                            System.out.print("Enter new Name: ");
                            String name = sc.nextLine();
                            System.out.print("Enter new Phone: ");
                            String phone = sc.nextLine();
                            System.out.print("Enter new Email: ");
                            String email = sc.nextLine();
                            contacts.set(index - 1, new Contact(name, phone, email));
                            System.out.println("Contact updated successfully!");
                        } else {
                            System.out.println("Invalid contact number!");
                        }
                    }
                }

                case 4 -> {
                    if (contacts.isEmpty()) {
                        System.out.println("No contacts to delete.");
                    } else {
                        System.out.print("Enter contact number to delete: ");
                        int index = sc.nextInt();
                        sc.nextLine();
                        if (index > 0 && index <= contacts.size()) {
                            contacts.remove(index - 1);
                            System.out.println("Contact deleted successfully!");
                        } else {
                            System.out.println("Invalid contact number!");
                        }
                    }
                }

                case 5 -> System.out.println("Exiting... Thank you for using the Contact Manager!");

                default -> System.out.println("Invalid choice. Please try again.");
            }

            if (choice != 5) {
                System.out.println("\nYou can perform another action or enter 5 to exit.");
            }

        } while (choice != 5);

        sc.close();
    }
}
