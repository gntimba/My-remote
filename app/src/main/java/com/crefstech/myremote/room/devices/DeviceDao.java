package com.crefstech.myremote.room.devices;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Device device);

    @Query("SELECT * FROM devices")
    LiveData<List<Device>> getDevices();

    @Query("DELETE FROM devices")
    void Delete();

}
