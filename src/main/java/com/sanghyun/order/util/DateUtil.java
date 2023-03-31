package com.sanghyun.order.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class DateUtil {

    public Date localDateTimeToDate(LocalDateTime localDateTime) {
        if (ObjectUtils.isEmpty(localDateTime)) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date makeExpirationDate(Long additionalSecond) {
        return this.localDateTimeToDate(LocalDateTime.now().plusSeconds(additionalSecond));
    }

    public LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public boolean expirationCheck(Long exp) {
        return LocalDateTime.now().isAfter(this.dateToLocalDateTime(new Date(exp)));
    }

    public String convertToFormat(LocalDateTime time, String format) {
        if (ObjectUtils.isEmpty(time) ){
            return "";
        }
        return time.format(DateTimeFormatter.ofPattern(format));
    }
}
