package com.demo.Mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.services.ClientService;
import com.demo.types.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private ClientService clientService;

    public Client addClient(String fio, String birthday, String homeAdress, String sex, String phoneNumber) {
        return clientService.add(fio, birthday, homeAdress, sex, phoneNumber);
    }

    public Client updateClient(int id, String fio, String birthday, String homeAdress, String sex, String phoneNumber) {
        return clientService.update(id, fio, birthday, homeAdress, sex, phoneNumber);
    }

    public String deleteClient(int id) {
        return clientService.delete(id);
    }
}
