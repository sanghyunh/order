package com.sanghyun.order.util;

import java.text.DecimalFormat;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConverterUtil {

    private final ObjectMapper objectMapper;

    public String toJsonString(Object object) {
        String value = "{}";
        if (ObjectUtils.isEmpty(object)) {
            return value;
        }
        try {
            value = this.objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.info("toJsonString error: {}", e.getMessage(), e);
            return value;
        }
        return value;
    }

    public <T> T toObject(String json, Class<T> type) {
        try {
            return this.objectMapper.readValue(json, type);
        } catch (Exception e) {
            log.info("toObject Error :{}", e.getMessage(), e);
            return null;
        }
    }

    public Map<String, Object> toMap(Object object) {
        try {
            return this.objectMapper.convertValue(object, Map.class);
        } catch (Exception e) {
            log.info("toObject Error :{}", e.getMessage(), e);
            return null;
        }
    }


}
