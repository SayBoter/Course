package com.demo.Queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.services.RoomService;
import com.demo.services.RoomTypeService;
import com.demo.types.Room;
import com.demo.types.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private RoomService roomService;

    public Room getRoom(int id) {
        return roomService.getRoom(id);
    }

    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @Bean
    public GraphQLResolver<Room> roomTypeResolver() {
        return new GraphQLResolver<Room>() {
            public RoomType roomType(Room item) {
                return roomTypeService.getRoomType(item.getRoomType());
            }
        };
    }
}
