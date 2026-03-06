package com.example.demoinvoicingservice.common.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AbstractMapper {
    @SneakyThrows
    default String fromListToString(List<String> stringList) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(stringList);
    }

    @SneakyThrows
    default List<String> fromStringToList(String str) {
        ObjectMapper mapper = new ObjectMapper();
        if (StringUtils.isEmpty(str))
            return new ArrayList<>();
        return mapper.readValue(str, new TypeReference<List<String>>() {
        });
    }

    @SneakyThrows
    default String fromMapToString(Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }

    @SneakyThrows
    default Map<String, Object> fromStringToMap(String str) {
        ObjectMapper mapper = new ObjectMapper();
        if (StringUtils.isEmpty(str))
            return new HashMap<>();
        return mapper.readValue(str, new TypeReference<>() {
        });
    }
}
