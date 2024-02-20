package ro.ubb.lab3.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.lab3.common.Book;
import ro.ubb.lab3.common.BookEntityService;
import ro.ubb.lab3.common.EntityService;
import ro.ubb.lab3.common.EntityValidator;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BookServiceClient implements BookEntityService {
    @Autowired
    private  EntityValidator<Book> entityValidator;

    @Autowired
    private BookEntityService bookServiceBean; //apelam interfata comuna ,aceeasi metoda de pe client-server (message)



    @Override
    public List<Book> getAllEntities() throws ExecutionException, InterruptedException {
        return bookServiceBean.getAllEntities();
    }

    @Override
    public Book readOneEntity(Long id) throws ExecutionException, InterruptedException {
        return bookServiceBean.readOneEntity(id);
    }

    @Override
    public Book addEntity(Book entity) throws ExecutionException, InterruptedException {
        //entityValidator.validate(entity);
        return bookServiceBean.addEntity(entity);
    }

    @Override
    public Book updateEntity(Book entity) {
        //entityValidator.validate(entity);
        return bookServiceBean.updateEntity(entity);
    }

    @Override
    public void deleteOneEntity(Long id) {
        bookServiceBean.deleteOneEntity(id);
    }
}
