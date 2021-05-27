package com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "addresses")
public class Address {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "code")
    public Integer code;

    public Address(String city, String country, Integer code) {
        this.city = city;
        this.country = country;
        this.code = code;
    }

    public Address(){

    }

    @Override
    public String toString() {
        return city + " " + country + " " + code + " " + id;
    }
}
