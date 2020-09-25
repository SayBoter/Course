package com.demo.services;

import com.demo.translators.ClientTranslator;
import com.demo.translators.VisitTranslator;
import com.demo.types.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class VisitService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Visit> delete(String arrival, int client) {
        jdbcTemplate.update("Delete From Visit Where arrival=TO_DATE(?,'DD-MM-YYYY') and id_client=?",
                arrival, client);
        return getAllVisits();
    }

    public Visit add(String arrival, int client, int room, String departure) {
        jdbcTemplate.update("Insert Into Visit Values(TO_DATE(?,'DD-MM-YYYY'),?,?,TO_DATE(?,'DD-MM-YYYY'))",
                arrival, client, room, departure);
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Visit Where arrival = TO_DATE(?,'DD-MM-YYYY') and id_client=? and room=?",
                arrival, client, room);
        return new VisitTranslator().fromDto(result.get(0));
    }

    public List<Visit> getBusyRooms(int room) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Visit Where room=? and departure>current_date", room);
        List<Visit> visits = new ArrayList<>();
        for (int i = 0; i < result.size(); ++i) {
            visits.add(new VisitTranslator().fromDto(result.get(i)));
        }
        return visits;
    }

    public List<Visit> getAllVisits() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Visit");
        List<Visit> visits = new ArrayList<>();
        for (int i = 0; i < result.size(); ++i) {
            visits.add(new VisitTranslator().fromDto(result.get(i)));
        }
        return visits;
    }

    public List<Visit> getClientVisits(int id) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Visit Where id_client = ?", id);
        List<Visit> visits = new ArrayList<>();
        for (int i = 0; i < result.size(); ++i) {
            visits.add(new VisitTranslator().fromDto(result.get(i)));
        }
        return visits;
    }

    public Visit getVisit(String arrival, int idClient, int room) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList(
                "Select * From Visit Where arrival = TO_DATE(?,'DD-MM-YYYY') and id_client=? and room=?",
                arrival, idClient, room
        );
        return new VisitTranslator().fromDto(result.get(0));
    }
}
