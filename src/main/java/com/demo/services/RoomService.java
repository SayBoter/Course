package com.demo.services;

import com.demo.translators.RoomTranslator;
import com.demo.types.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RoomService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Room add(int roomNumber, int phoneNumber, int roomType, int floor, String description) {
        jdbcTemplate.update("Insert Into Room(room_number, phone_number, room_type, floor, description) Values(?,?,?,?,?)",
                roomNumber, phoneNumber, roomType, floor, description);
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Room Where room_number=?", roomNumber);
        return new RoomTranslator().fromDto(result.get(0));
    }

    public Room update(int roomNumber, int phoneNumber, int roomType, int floor, String description) {
        jdbcTemplate.update("Update Room Set phone_number = ?, room_type = ?, floor = ?, description=?\n" +
                        "Where room_number = ?",
                phoneNumber, roomType, floor, description, roomNumber);
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Room Where room_number=?", roomNumber);
        return new RoomTranslator().fromDto(result.get(0));
    }

    public Room getRoom(int id) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Room Where room_number=?", id);
        return new RoomTranslator().fromDto(result.get(0));
    }

    public List<Room> getAllRooms() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Room");
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < result.size(); ++i) {
            rooms.add(new RoomTranslator().fromDto(result.get(i)));
        }
        return rooms;
    }
}
