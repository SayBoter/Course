package com.demo.Queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demo.services.RoomTypeService;
import com.demo.types.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RootQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private RoomTypeService roomTypeService;

    public RoomType getRoomType(int id) {
        return roomTypeService.getRoomType(id);
    }

    public List<RoomType> getAllRoomTypes() {
        return roomTypeService.getAllRoomTypes();
    }
}
