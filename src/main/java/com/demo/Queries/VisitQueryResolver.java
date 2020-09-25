package com.demo.Queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.services.ClientService;
import com.demo.services.RoomService;
import com.demo.services.VisitService;
import com.demo.types.Client;
import com.demo.types.Room;
import com.demo.types.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VisitQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private VisitService visitService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private RoomService roomService;

    public List<Visit> getBusyRooms(int room) {
        return visitService.getBusyRooms(room);
    }

    public Visit getVisit(String arrival, int idClient, int room) {
        return visitService.getVisit(arrival, idClient, room);
    }

    public List<Visit> getAllVisits() {
        return visitService.getAllVisits();
    }

    public List<Visit> getClientVisits(int id) {
        return visitService.getClientVisits(id);
    }

    @Bean
    public GraphQLResolver<Visit> visitResolver() {
        return new GraphQLResolver<Visit>() {

            public Client client(Visit visit) {
                return clientService.getClient(visit.getIdClient());
            }

            public Room room(Visit visit) {
                return roomService.getRoom(visit.getRoom());
            }
        };
    }
}
