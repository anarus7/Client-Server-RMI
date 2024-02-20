package ro.ubb.lab3.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.lab3.client.ui.Console;


public class ClientApp {
    public static void main(String[] args) throws Exception {

        System.out.println("Client....");

        ApplicationContext context=new AnnotationConfigApplicationContext("ro.ubb.lab3.client.config");
        Console console=context.getBean(Console.class);
        console.runConsole();

        System.out.println("bye");
    }
}