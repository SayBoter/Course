package com.demo.Queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demo.services.ClientService;
import com.demo.types.Client;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private ClientService clientService;

    public Client getClient(int id) {
        return clientService.getClient(id);
    }

    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }
}
