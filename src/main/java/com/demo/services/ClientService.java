package com.demo.services;

import com.demo.translators.ClientTranslator;
import com.demo.types.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ClientService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Logger logger = LoggerFactory.getLogger(RoomService.class);
    public static final List<String> FIOS = Arrays.asList("Ivanov I.I.", "Leonov L.L.", "Trofimov K.K.", "Krasilov K.K.", "Nikolaev H.L.", "Klimov K.K.");
    public static final List<String> NUMBERS = Arrays.asList("6997911", "7451254", "4582545");
    public static final List<String> BIRTHDAYS = Arrays.asList("20.01.1986", "17.11.1969", "02.03.1995", "30.05.1988");
    public static final List<String> ADRESSES = Arrays.asList("Nauki avenue", "Victory avenue", "Sumskaya street");
    public static final List<String> SEX = Arrays.asList("W", "M");

    @Scheduled(initialDelay = 1000, fixedDelay = 10000)
    public void makeClient() {
        Random random = new Random();
        Client client = add(FIOS.get(random.nextInt(FIOS.size())),
                            BIRTHDAYS.get(random.nextInt(BIRTHDAYS.size())),
                            ADRESSES.get(random.nextInt(ADRESSES.size())),
                            SEX.get(random.nextInt(SEX.size())),
                            NUMBERS.get(random.nextInt(NUMBERS.size())));
        logger.info("We create a client:" + client.toString());
    }

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
