package com.demo.translators;

import com.demo.types.Bill;

import java.util.Map;

public final class BillTranslator {

    public Bill fromDto(Map<String, Object> obj) {
        Double price;
        try {
            price = Double.valueOf(obj.get("price").toString());
        } catch (Exception e) {
            price = 0.0;
        }
        return new Bill(
                obj.get("arrival").toString(),
                Integer.valueOf(obj.get("id_client").toString()),
                price
        );
    }
}
