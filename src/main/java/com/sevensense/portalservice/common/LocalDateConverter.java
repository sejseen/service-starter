package com.sevensense.portalservice.common;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateConverter {

    public static LocalDateTime fromTimestamp(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),
            TimeZone.getDefault().toZoneId());
    }

    public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }

}
