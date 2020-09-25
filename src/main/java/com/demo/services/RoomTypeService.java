package com.demo.services;

import com.demo.translators.RoomTypeTranslator;
import com.demo.types.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RoomTypeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public RoomType update(String type, String price) {
        jdbcTemplate.update("Update Room_Type Set price = ? " +
                        "Where type = ?",
                price, type);
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Room_Type Where type=?", type);
        return new RoomTypeTranslator().fromDto(result.get(0));
    }

    public RoomType add(String type, String price) {
        jdbcTemplate.update("Insert Into Room_Type Values(NEXTVAL('seq_room_type'),?,?)", type, Double.valueOf(price));
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Room_Type Where type=?", type);
        return new RoomTypeTranslator().fromDto(result.get(0));
    }

    public RoomType getRoomType(int id) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Room_Type Where id=?", id);
        return new RoomTypeTranslator().fromDto(result.get(0));
    }

    public List<RoomType> getAllRoomTypes() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Room_Type");
        List<RoomType> roomTypes = new ArrayList<>();
        for (int i = 0; i < result.size(); ++i) {
            roomTypes.add(new RoomTypeTranslator().fromDto(result.get(i)));
        }
        return roomTypes;
    }
}
