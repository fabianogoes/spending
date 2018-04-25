package com.spending.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.Date;

public class OffsetDateTimeToDateConverter implements Converter<OffsetDateTime, Date>{

    @Nullable
    @Override
    public Date convert(OffsetDateTime source){
        return source == null ? null : Date.from(source.toInstant());
    }
}