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

    public Client updateClient(String fio, String birthday, String homeAdress, String sex, String phoneNumber) {
        return clientService.update(fio, birthday, homeAdress, sex, phoneNumber);
    }
}
