package ro.ubb.lab3.server.service;

import ro.ubb.lab3.common.Client;
import ro.ubb.lab3.common.ClientEntityService;
import ro.ubb.lab3.server.repository.Repository;

import java.util.concurrent.ExecutorService;

public class ClientService extends GenericService<Client> implements ClientEntityService {

    public ClientService(Repository<Long, Client> entityRepository, ExecutorService executorService) {
        super(entityRepository, executorService);
    }
}
