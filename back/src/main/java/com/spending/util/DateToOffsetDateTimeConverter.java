package com.spending.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateToOffsetDateTimeConverter implements Converter<Date, OffsetDateTime> {

    @Nullable
    @Override
    public OffsetDateTime convert(Date source) {
        return source == null ? null : OffsetDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
    }

}