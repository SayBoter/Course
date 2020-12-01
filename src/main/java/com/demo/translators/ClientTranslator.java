package com.demo.translators;

import com.demo.types.Client;

import java.util.Date;
import java.util.Map;

public final class ClientTranslator {

    public Client fromDto(Map<String, Object> obj) {
        return new Client(
                Integer.valueOf(obj.get("id").toString()),
                obj.get("FIO").toString(),
                obj.get("birthday").toString(),
                obj.get("home_adress").toString(),
                obj.get("sex").toString(),
                obj.get("phone_number").toString()
        );
    }
}
