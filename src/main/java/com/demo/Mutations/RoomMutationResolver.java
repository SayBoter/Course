package com.demo.Mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.services.RoomService;
import com.demo.types.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private RoomService roomService;

    public Room addRoom(int roomNumber, int phoneNumber, int roomType, int floor, String description) {
        return roomService.add(roomNumber, phoneNumber, roomType, floor, description);
    }

    public Room updateRoom(int roomNumber, int phoneNumber, int roomType, int floor, String description) {
        return roomService.update(roomNumber, phoneNumber, roomType, floor, description);
    }
}
