package ro.ubb.lab3.server.service;

import ro.ubb.lab3.common.Book;
import ro.ubb.lab3.common.Purchase;
import ro.ubb.lab3.common.PurchaseEntityService;
import ro.ubb.lab3.server.repository.Repository;

import java.util.concurrent.ExecutorService;

public class PurchaseService  extends GenericService<Purchase> implements PurchaseEntityService {

    public PurchaseService(Repository<Long, Purchase> entityRepository, ExecutorService executorService) {
        super(entityRepository, executorService);
    }
}