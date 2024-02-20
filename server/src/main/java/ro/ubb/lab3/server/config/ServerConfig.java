package ro.ubb.lab3.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.lab3.common.*;
import ro.ubb.lab3.server.repository.BookDatabaseRepository;
import ro.ubb.lab3.server.repository.ClientDatabaseRepository;
import ro.ubb.lab3.server.repository.PurchaseDatabaseRepository;
import ro.ubb.lab3.server.repository.Repository;
import ro.ubb.lab3.server.service.BookService;
import ro.ubb.lab3.server.service.ClientService;
import ro.ubb.lab3.server.service.PurchaseService;

import java.util.concurrent.*;

@Configuration
public class ServerConfig {

    @Bean
    RmiServiceExporter rmiServiceExporterBook() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("bookService");
        rmiServiceExporter.setServiceInterface(BookEntityService.class);
        rmiServiceExporter.setService(bookService());
        return rmiServiceExporter;
    }

    @Bean
    RmiServiceExporter rmiServiceExporterPurchase() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("purchaseService");
        rmiServiceExporter.setServiceInterface(PurchaseEntityService.class);
        rmiServiceExporter.setService(purchaseService());
        return rmiServiceExporter;
    }
    @Bean
    RmiServiceExporter rmiServiceExporterClient() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("clientService");
        rmiServiceExporter.setServiceInterface(ClientEntityService.class);
        rmiServiceExporter.setService(clientService());
        return rmiServiceExporter;
    }





    @Bean
    public ExecutorService executorServiceBean() {
        return Executors.newFixedThreadPool(10);
    }
    @Bean
    public EntityService<Book> bookService() {
        return new BookService(bookDatabaseRepository(), executorServiceBean());
    }





    @Bean
    public EntityService<Purchase> purchaseService() {
        return new PurchaseService(purchaseDatabaseRepository(), executorServiceBean());
    }

    @Bean
    public Repository<Long, Book> bookDatabaseRepository() {
        return new BookDatabaseRepository();
    }
    @Bean
    public Repository<Long, Purchase> purchaseDatabaseRepository() {
        return new PurchaseDatabaseRepository();
    }
    @Bean
    public EntityService<Client> clientService() {
        return new ClientService(clientDatabaseRepository(), executorServiceBean());
    }
    @Bean
    public Repository<Long, Client> clientDatabaseRepository() {
        return new ClientDatabaseRepository();
    }
}
