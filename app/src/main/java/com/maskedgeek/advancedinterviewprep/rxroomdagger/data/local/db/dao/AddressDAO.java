package com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.entity.Address;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface AddressDAO {

    @Delete
    public Single<Integer> delete(Address address);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Single<List<Long>> insertMany(List<Address> addressList);

    @Query("SELECT * FROM addresses")
    public Single<List<Address>> getAllAddresses();

}
