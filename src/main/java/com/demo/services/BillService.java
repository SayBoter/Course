package com.demo.services;

import com.demo.translators.BillTranslator;
import com.demo.types.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BillService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Bill> add() {
        jdbcTemplate.update("Insert Into Bill(arrival, id_client, price)\n" +
                "Select arrival, id_client, RT.price*(departure-arrival)\n" +
                "From ((Visit V inner join Client C on V.id_client=C.id) inner join Room R on V.room=R.room_number) inner join Room_type RT on RT.id=R.room_type\n" +
                "Where departure < current_date and (Select arrival from Bill B2 Where B2.arrival=V.arrival and B2.id_client=V.id_client) is null");
        return getAllBills();
    }

    public Bill getBill(String arrival, int idClient) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList(
                "Select * From Bill Where arrival = TO_DATE(?,'MM-DD-YYYY') and id_client=?",
                arrival, idClient
        );
        return new BillTranslator().fromDto(result.get(0));
    }

    public List<Bill> getAllBills() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Bill");
        List<Bill> bills = new ArrayList<>();
        for (int i = 0; i < result.size(); ++i) {
            bills.add(new BillTranslator().fromDto(result.get(i)));
        }
        return bills;
    }

    public List<Bill> getClientBills(int id) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Bill Where id_client = ?", id);
        List<Bill> bills = new ArrayList<>();
        for (int i = 0; i < result.size(); ++i) {
            bills.add(new BillTranslator().fromDto(result.get(i)));
        }
        return bills;
    }
}
