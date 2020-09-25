package com.demo.Queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.services.BillService;
import com.demo.services.ClientService;
import com.demo.types.Bill;
import com.demo.types.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BillQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private BillService billService;

    @Autowired
    private ClientService clientService;

    public Bill getBill(String arrival, int idClient) {
        return billService.getBill(arrival, idClient);
    }

    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    public List<Bill> getClientBills(int id) {
        return billService.getClientBills(id);
    }

    @Bean
    public GraphQLResolver<Bill> billResolver() {
        return new GraphQLResolver<Bill>() {
            public Client client(Bill bill) {
                return clientService.getClient(bill.getIdClient());
            }
        };
    }
}
