package com.demo.Mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.services.RoomTypeService;
import com.demo.types.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RootMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private RoomTypeService roomTypeService;

    public RoomType addRoomType(String type, String price) {
        return roomTypeService.add(type, price);
    }

    public RoomType updateRoomType(String type, String price) {
        return roomTypeService.update(type, price);
    }
}
