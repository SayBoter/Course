package com.demo.translators;

import com.demo.types.Room;

import java.util.Map;

public final class RoomTranslator {

    public Room fromDto(Map<String, Object> obj) {
        String description;
        try {
            description = obj.get("description").toString();
        } catch (Exception e) {
            description = "";
        }

        return new Room(
                Integer.valueOf(obj.get("room_number").toString()),
                obj.get("phone_number").toString(),
                Integer.valueOf(obj.get("room_type").toString()),
                Integer.valueOf(obj.get("floor").toString()),
                description
        );
    }
}
