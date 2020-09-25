package com.demo.Mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.services.BillService;
import com.demo.types.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BillMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private BillService billService;

    public List<Bill> addBill() {
        return billService.add();
    }
}
