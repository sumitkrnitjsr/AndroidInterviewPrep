package com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;


import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "users",
        foreignKeys = {
                @ForeignKey(
                        entity = Address.class,
                        parentColumns ="id",
                        childColumns="address_id",
                        onDelete = ForeignKey.CASCADE)
        })
public class Users {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "name")
    public  String name;

    @ColumnInfo(name = "address_id")
    public Long address_id;

    @ColumnInfo(name = "dob")
    public Date dateOfBirth;

    @Ignore
    public boolean selected;


    public  Users() {

    }

    public Users(String name, Long address) {
        this.name = name;
        this.address_id = address;
    }

    public Users(String name, Long address, Date date) {
        this.name = name;
        this.address_id = address;
        this.dateOfBirth = date;
    }

    @Override
    public String toString() {
        return name + " " + id + " " + address_id + " " + dateOfBirth;
    }
}
