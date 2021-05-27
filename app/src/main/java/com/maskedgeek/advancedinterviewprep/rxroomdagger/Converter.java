package com.maskedgeek.advancedinterviewprep.rxroomdagger;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converter {

    @TypeConverter
    public Date fromTimeStamp(Long value) {
        return new Date(value);
    }

    @TypeConverter
    public long fromDate(Date date) {
        return date.getTime();
    }
}
