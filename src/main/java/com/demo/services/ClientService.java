package com.demo.services;

import com.demo.translators.ClientTranslator;
import com.demo.types.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ClientService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Client update(String fio, String birthday, String homeAdress, String sex, String phoneNumber) {
        jdbcTemplate.update("Update Client Set birthday = TO_DATE(?,'DD-MM-YYYY'), phone_number = ?, home_adress = ?, sex=?\n" +
                        "Where fio = ?",
                birthday, phoneNumber, homeAdress, sex, fio);
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Client Where FIO=?", fio);
        return new ClientTranslator().fromDto(result.get(0));
    }

    public Client add(String fio, String birthday, String homeAdress, String sex, String phoneNumber) {
        jdbcTemplate.update("Insert Into Client(id, FIO, birthday, home_adress, sex, phone_number) Values(NEXTVAL('seq_client'), ?,TO_DATE(?,'DD-MM-YYYY'),?,?,?)",
                fio, birthday, homeAdress, sex, phoneNumber);
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Client Where FIO=?", fio);
        return new ClientTranslator().fromDto(result.get(0));
    }

    public Client getClient(int id) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Client Where id=?", id);
        return new ClientTranslator().fromDto(result.get(0));
    }

    public List<Client> getAllClients() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * From Client");
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < result.size(); ++i) {
            clients.add(new ClientTranslator().fromDto(result.get(i)));
        }
        return clients;
    }
}
