package ro.ubb.lab3.common;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface EntityService<T> {


    List<T> getAllEntities()throws ExecutionException, InterruptedException ;
    T readOneEntity(Long id) throws ExecutionException, InterruptedException;

    T addEntity(T entity) throws ExecutionException, InterruptedException;

    T updateEntity(T entity) ;

    void  deleteOneEntity(Long id) ;


}


