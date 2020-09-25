package com.demo.Mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.services.VisitService;
import com.demo.types.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VisitMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private VisitService visitService;

    public Visit addVisit(String arrival, int client, int room, String departure) {
        return visitService.add(arrival, client, room, departure);
    }

    public List<Visit> deleteVisit(String arrival, int client) {
        return visitService.delete(arrival, client);
    }
}
