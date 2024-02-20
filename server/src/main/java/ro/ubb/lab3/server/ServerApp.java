package ro.ubb.lab3.server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerApp {

    public static void main(String[] args) {
        System.out.println("welcome server");

        new AnnotationConfigApplicationContext("ro.ubb.lab3.server");
    }

}

