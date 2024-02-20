package ro.ubb.lab3.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.lab3.common.EntityService;
import ro.ubb.lab3.common.EntityValidator;
import ro.ubb.lab3.common.Purchase;
import ro.ubb.lab3.common.PurchaseEntityService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PurchaseServiceClient implements PurchaseEntityService {
    @Autowired
    EntityValidator<Purchase> entityValidator;
    @Autowired
    private PurchaseEntityService purchaseServiceBean;


    @Override
    public List<Purchase> getAllEntities() throws ExecutionException, InterruptedException {
        return purchaseServiceBean.getAllEntities();
    }

    @Override
    public Purchase readOneEntity(Long id) throws ExecutionException, InterruptedException {
        return purchaseServiceBean.readOneEntity(id);
    }

    @Override
    public Purchase addEntity(Purchase entity) throws ExecutionException, InterruptedException {
        return purchaseServiceBean.addEntity(entity);
    }

    @Override
    public Purchase updateEntity(Purchase entity) {
        return purchaseServiceBean.updateEntity(entity);
    }

    @Override
    public void deleteOneEntity(Long id) {
       purchaseServiceBean.deleteOneEntity(id);
    }
}