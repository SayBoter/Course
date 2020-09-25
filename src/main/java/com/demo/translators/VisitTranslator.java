package com.demo.translators;

import com.demo.types.Visit;

import java.util.Map;

public final class VisitTranslator {

    public Visit fromDto(Map<String, Object> obj) {
        String departure;
        try {
            departure = obj.get("departure").toString();
        } catch (Exception e) {
            departure = "";
        }
        return new Visit(
                obj.get("arrival").toString(),
                Integer.valueOf(obj.get("id_client").toString()),
                Integer.valueOf(obj.get("room").toString()),
                departure
        );
    }
}
