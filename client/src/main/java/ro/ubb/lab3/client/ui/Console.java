package ro.ubb.lab3.client.ui;


import org.springframework.stereotype.Component;
import ro.ubb.lab3.common.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Component
public class Console {

    private BookEntityService bookServiceClient;
    private PurchaseEntityService purchaseServiceClient;
    private ClientEntityService clientServiceClient;
    private Scanner scanner;

    public Console(BookEntityService bookServiceClient, PurchaseEntityService purchaseServiceClient, ClientEntityService clientServiceClient) {
        this.bookServiceClient = bookServiceClient;
        this.purchaseServiceClient = purchaseServiceClient;
        this.clientServiceClient = clientServiceClient;
        this.scanner = new Scanner(System.in);
    }

    private static void print(String string) {
        System.out.println(string);
    }

    private static void menu() {
        print("1. Operations for Books");
        print("2. Operations for the Clients");
        print("3. Operations for the Purchases");
        print("4. See Client Spending");
        print("0. Exit \n");
    }

    public void runConsole() throws Exception {
        while (true) {
            menu();
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> handleBookSubmenu();
                case 2 -> handleClientSubmenu();
                case 3 -> handlePurchasesSubmenu();
//                case 4 -> handleBookProfitability();
//                case 4 -> handleClientSpending();
//               case 6 -> bookService.getAllBooks();
                case 0 -> {
                    return;
                }
                default -> print("Invalid option.\n");
//            }

            }
        }

    }


    private void handleBookSubmenu() throws ExecutionException, InterruptedException {
        while (true) {
            print("1. Add a new book ");
            print("2. Update a book ");
            print("3. Delete a book ");
            print("4. Show a book ");
            print("5. Show all books ");

            print("0. Return to main menu!\n");
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> this.handleAddBook();
                case 2 -> this.handleUpdateBook();
                case 3 -> this.handleDeleteBook();
                case 4 -> this.handleShowOneBook();
                case 5 -> this.handleShowAllBooks();
                case 0 -> {
                    return;
                }
                default -> print("Invalid option.\n");

            }
        }
    }


    public void handleAddBook() {
        try {
            Book book = getBookdata();
            this.bookServiceClient.addEntity(book);
        } catch (InputMismatchException ime) {
            print("Wrong data type entered!\n");
            scanner.next();
        } catch (Exception exception) {
            print(exception.getMessage());
            print("\n");

        }
    }

    public Book getBookdata() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        print("Enter Book Id: ");
        Long id = scanner.nextLong();
        print("Enter Book Title: ");
        String title = br.readLine();
        print("Enter Book Author: ");
        String author = br.readLine();
        print("Enter publisher: ");
        String publisher = br.readLine();
        print("Enter the Price: ");
        double price = scanner.nextDouble();
        print("Enter the Stock: ");
        int stock = scanner.nextInt();

        return new Book(id, title, author, publisher, price, stock);
    }

    private void handleDeleteBook() {
        try {
            print("Enter Book Id you want to delete: ");
            long bookId = scanner.nextLong();
            System.out.println(this.bookServiceClient.readOneEntity(bookId) + " will be deleted!");
            this.bookServiceClient.deleteOneEntity(bookId);
            print("Deletion completed!\n");

        } catch (Exception exception) {
            print("Error occurred during delete...\n");
        }
    }

    private void handleShowOneBook() throws ExecutionException, InterruptedException {
        print("Enter Book Id");
        long bookId = scanner.nextLong();
        Book book = this.bookServiceClient.readOneEntity(bookId);
        if (Objects.isNull(book)) {
            System.out.println("Couldn't find the book you searched for!\n");
        } else {
            System.out.println(book);
        }
    }


    private void handleUpdateBook() {
        try {
            Book book = getBookdata();
            bookServiceClient.updateEntity(book);
            print("Book Update Completed!\n");
        } catch (Exception exception) {
            print("Error during update!\n");
        }
    }

    private void handleShowAllBooks() throws ExecutionException, InterruptedException {

        List<Book> bookList = this.bookServiceClient.getAllEntities();

        if (!bookList.isEmpty()) {
            for (Book book :
                    bookList) {
                print(book.toString());
            }
        } else {
            System.out.println("Couldn't find books.\n");
        }
    }


    private void handlePurchasesSubmenu() throws ExecutionException, InterruptedException {
        while (true) {
            print("1. Add a new purchase ");
            print("2. Update a purchase ");
            print("3. Delete a purchase ");
            print("4. Show one purchase ");
            print("5. Show all purchase ");
            print("0. Return to main menu!\n");
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> this.handleAddPurchase();
                case 2 -> this.handleUpdatePurchase();
                case 3 -> this.handleDeletePurchase();
                case 4 -> this.handleShowOnePurchase();
                case 5 -> this.handleShowAllPurchases();
                case 0 -> {
                    return;
                }
                default -> print("Invalid option.\n");

            }
        }
    }

    public void handleAddPurchase() {
        try {
            Purchase purchase = getPurchasedata();
            this.purchaseServiceClient.addEntity(purchase);
        } catch (InputMismatchException ime) {
            print("Wrong data type entered!\n");
            scanner.next();
        } catch (Exception exception) {
            print(exception.getMessage());
            print("\n");

        }
    }

    public Purchase getPurchasedata() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        print("Enter Purchase Id: ");
        Long id = scanner.nextLong();
        print("Enter Book Id: ");
        Long bookId = scanner.nextLong();
        print("Enter Client Id: ");
        Long clientId = scanner.nextLong();
        print("Enter number sold: ");
        int numberSold = scanner.nextInt();
        print("Enter date of the purchase: ");
        LocalDate dateOfPurchase = LocalDate.parse(scanner.next());

        return new Purchase(id, bookId, clientId, numberSold, dateOfPurchase);
    }

    private void handleDeletePurchase() {
        try {
            print("Enter Purchase Id you want to delete: ");
            long id = scanner.nextLong();
            System.out.println(this.purchaseServiceClient.readOneEntity(id) + " will be deleted!");
            purchaseServiceClient.deleteOneEntity(id);
            print("Deletion completed!\n");

        } catch (Exception exception) {
            print("Error occurred during delete...\n");
        }
    }

    private void handleShowOnePurchase() throws ExecutionException, InterruptedException {
        print("Enter Purchase Id");
        long id = scanner.nextLong();
        Purchase purchase = this.purchaseServiceClient.readOneEntity(id);
        if (Objects.isNull(purchase)) {
            System.out.println("Couldn't find the purchase you searched for!\n");
        } else {
            System.out.println(purchase);
        }
    }


    private void handleUpdatePurchase() {
        try {
            Purchase purchase = getPurchasedata();
            purchaseServiceClient.updateEntity(purchase);
            print("Purchase Update Completed!\n");
        } catch (Exception exception) {
            print("Error during update!\n");
        }
    }

    private void handleShowAllPurchases() throws ExecutionException, InterruptedException {
        List<Purchase> purchasesList = this.purchaseServiceClient.getAllEntities();

        if (!purchasesList.isEmpty()) {
            for (Purchase purchase : purchasesList) {
                print(purchase.toString());
            }
        } else {
            System.out.println("Couldn't find purchases.\n");
        }
    }


    //    private void handleClientSpending() throws ExecutionException, InterruptedException {
//        List<ClientSpendingDTO> clientSpendingDTOList = this.purchaseService.getClientSpending().get();
//        if (!clientSpendingDTOList.isEmpty()) {
//            for (ClientSpendingDTO clientSpendingDTO :
//                    clientSpendingDTOList) {
//                print(clientSpendingDTO.toString());
//            }
//        } else {
//            System.out.println("Couldn't find ClientSpendingDTOs.\n");
//        }
//    }
//
//
    private void handleClientSubmenu() throws ExecutionException, InterruptedException {
        while (true) {
            print("1. Add a new client ");
            print("2. Update a client ");
            print("3. Delete a client ");
            print("4. Show a client ");
            print("5. Show all clients ");
            print("0. Return to main menu! \n");
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> this.handleAddClient();
                case 2 -> this.handleUpdateClient();
                case 3 -> this.handleDeleteClient();
                case 4 -> this.handleShowOneClient();
                case 5 -> this.handleShowAllClients();
                case 0 -> {
                    return;
                }
                default -> print("Invalid option.\n");

            }
        }
    }

    public Client getClientData() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        print("Enter Client Id: ");
        Long id = scanner.nextLong();
        print("Enter Client First Name: ");
        String firstname = br.readLine();
        print("Enter Client Last Name: ");
        String lastname = br.readLine();
        print("Enter Client phone number: ");
        String phonenumber = br.readLine();


        return new Client(id, firstname, lastname, phonenumber);

    }

    private void handleAddClient() {
        try {
            Client client = getClientData();
            this.clientServiceClient.addEntity(client);
        } catch (InputMismatchException ime) {
            print("Wrong data type entered!\n");
            scanner.next();
        } catch (Exception exception) {
            print(exception.getMessage());
            print("\n");


        }
    }

    private void handleUpdateClient() {
        try {
            Client client = getClientData();
            this.clientServiceClient.updateEntity(client);
            print("Client Update Completed!\n");
        } catch (Exception exception) {
            print("Error during update!\n");

        }
    }

    private void handleDeleteClient() {
        try {
            print("Enter Client Id you want to delete: ");
            Long clientId = scanner.nextLong();
            System.out.println(this.clientServiceClient.readOneEntity(clientId) + " will be deleted!");
            this.clientServiceClient.deleteOneEntity(clientId);
            print("Deletion completed!\n");

        } catch (Exception exception) {
            print("Error occurred during delete...\n");
        }
    }

    private void handleShowOneClient() throws ExecutionException, InterruptedException {
        print("Enter client Id");
        long id = scanner.nextLong();
        Client client = this.clientServiceClient.readOneEntity(id);
        if (Objects.isNull(client)) {
            System.out.println("Couldn't find the client you searched for!\n");
        } else {
            System.out.println(client);
        }
    }

    private void handleShowAllClients() throws ExecutionException, InterruptedException {

        List<Client> clientsList = this.clientServiceClient.getAllEntities();

        if (!clientsList.isEmpty()) {
            for (Client client : clientsList) {
                print(client.toString());
            }
        } else {
            System.out.println("Couldn't find clients.\n");
        }
    }
}



