package com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.entity.Users;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface UserDAO {

    @Insert
    Single<Long> insert(Users users);

    @Update
    Single<Integer> update(Users users);

    @Delete
    Single<Integer> delete(Users users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertMany(List<Users> users);

    @Query("SELECT * FROM users")
    Single<List<Users>> getAllUsers();

    @Query("SELECT count(*) FROM users")
    Single<Integer> count();
}
