package com.demo.services;

import com.demo.translators.RoomTranslator;
import com.demo.types.Room;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;


@Log4j
@Component
public class RoomService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RoomTypeService roomTypeService;

    public static final List<String> DESCRIPTIONS = Arrays.asList("Rich room", "Cool room", "Beautiful room");
    public static final List<Integer> NUMBERS = Arrays.asList(7097911, 5485102, 3214562);
    public Logger logger = LoggerFactory.getLogger(RoomService.class);

    @Scheduled(initialDelay = 1000, fixedDelay = 8000)
    public void makeRoom() {
        Random random = new Random();
        int roomNumber = getAllRooms().get(getAllRooms().size()-1).getRoomNumber() + random.nextInt(25);
        Room room = add(roomNumber,
                NUMBERS.get(random.nextInt(NUMBERS.size())),
                roomTypeService.getAllRoomTypes().get(random.nextInt(roomTypeService.getAllRoomTypes().size())).getId(),
                random.nextInt(25),
                DESCRIPTIONS.get(random.nextInt(DESCRIPTIONS.size())));
        logger.info("We create a room:" + room.toString());
    }

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
