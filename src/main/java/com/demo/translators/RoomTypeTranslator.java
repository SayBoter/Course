package com.demo.translators;

import com.demo.types.RoomType;

import java.util.Map;

public final class RoomTypeTranslator {
    public RoomType fromDto(Map<String, Object> obj) {
        return new RoomType(Integer.valueOf(obj.get("id").toString()), obj.get("type").toString(), Double.valueOf(obj.get("price").toString()));
    }
}
