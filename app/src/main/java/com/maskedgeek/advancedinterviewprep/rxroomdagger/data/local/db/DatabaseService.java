package com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.maskedgeek.advancedinterviewprep.rxroomdagger.Converter;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.dao.AddressDAO;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.dao.UserDAO;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.entity.Address;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.entity.Users;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Database(
        entities = {
                Users.class,
                Address.class
        },
        version = 2,
        exportSchema = false
)

@TypeConverters(Converter.class)
public abstract class DatabaseService extends RoomDatabase {

public abstract UserDAO userDao();

public abstract AddressDAO addressDAO();

}
