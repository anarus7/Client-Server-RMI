package ro.ubb.lab3.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.lab3.common.Client;
import ro.ubb.lab3.common.ClientEntityService;
import ro.ubb.lab3.common.EntityValidator;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ClientServiceClient implements ClientEntityService {
    @Autowired
    private EntityValidator<Client> entityValidator;

    @Autowired
    private ClientEntityService clientServiceBean; //apelam interfata comuna ,aceeasi metoda de pe client-server (message)



    @Override
    public List<Client> getAllEntities() throws ExecutionException, InterruptedException {
        return clientServiceBean.getAllEntities();
    }

    @Override
    public Client readOneEntity(Long id) throws ExecutionException, InterruptedException {
        return clientServiceBean.readOneEntity(id);
    }

    @Override
    public Client addEntity(Client entity) throws ExecutionException, InterruptedException {
        //entityValidator.validate(entity);
        return clientServiceBean.addEntity(entity);
    }

    @Override
    public Client updateEntity(Client entity) {
        //entityValidator.validate(entity);
        return clientServiceBean.updateEntity(entity);
    }

    @Override
    public void deleteOneEntity(Long id) {
        clientServiceBean.deleteOneEntity(id);
    }
}
