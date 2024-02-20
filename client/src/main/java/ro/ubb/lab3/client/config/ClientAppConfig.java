package ro.ubb.lab3.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.lab3.client.service.BookServiceClient;
import ro.ubb.lab3.client.service.ClientServiceClient;
import ro.ubb.lab3.client.service.PurchaseServiceClient;
import ro.ubb.lab3.client.ui.Console;

import ro.ubb.lab3.common.*;

@Configuration
public class ClientAppConfig {

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBeanBook() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(BookEntityService.class);   //interfata comuna/apelam din client->server
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/bookService");
        return rmiProxyFactoryBean;
    }
    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBeanPurchase() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(PurchaseEntityService.class);   //interfata comuna/apelam din client->server
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/purchaseService");
        return rmiProxyFactoryBean;

    }
    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBeanClient() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(ClientEntityService.class);   //interfata comuna/apelam din client->server
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/clientService");
        return rmiProxyFactoryBean;

    }
    @Bean
    Console console() {
        return new Console(bookServiceBean(), purchaseServiceBean(), clientServiceBean());
    }

    @Bean
    BookEntityService bookServiceBean() {
        return new BookServiceClient();
    }

    @Bean
    PurchaseEntityService purchaseServiceBean() {
        return new PurchaseServiceClient();
    }

    @Bean
    EntityValidator<Book> entityValidatorBookBean() {
        return new EntityValidator<>();
    }

    @Bean
    EntityValidator<Purchase> entityValidatorPurchaseBean() {
        return new EntityValidator<>();
    }

    @Bean
    ClientEntityService clientServiceBean() { return new ClientServiceClient(); }
    @Bean
    EntityValidator<Client> entityValidatorClientBean() {
        return new EntityValidator<>();
    }
}