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

    @Query("SELECT * FROM devices")
    List<Device> getDevicess();

    @Query("SELECT * FROM devices WHERE type = :type")
    List<Device> getDevicesByType(String type);

    @Query("DELETE FROM devices")
    void Delete();

}
