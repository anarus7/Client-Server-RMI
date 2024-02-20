package ro.ubb.lab3.server.service;


import org.springframework.stereotype.Service;
import ro.ubb.lab3.common.Book;
import ro.ubb.lab3.common.BookEntityService;
import ro.ubb.lab3.server.repository.Repository;

import java.util.concurrent.ExecutorService;


@Service //needs 2 beans to be injected
public class BookService extends GenericService<Book> implements BookEntityService {

    public BookService(Repository<Long, Book> entityRepository, ExecutorService executorService) {
        super(entityRepository, executorService);
    }
}
