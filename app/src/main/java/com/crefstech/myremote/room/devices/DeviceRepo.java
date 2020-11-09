package com.crefstech.myremote.room.devices;

import android.app.Application;

import com.crefstech.myremote.room.LocalRoomDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class DeviceRepo {


    private DeviceDao uDao;
    private LiveData<List<Device>> LiveDevices;


    DeviceRepo(Application application) {
        LocalRoomDatabase db = LocalRoomDatabase.getDatabase(application);
        uDao = db.deviceDao();
        LiveDevices = uDao.getDevices();
    }

    LiveData<List<Device>> getUserLiveDevices() {
        return LiveDevices;
    }

    void insert(Device device) {
        LocalRoomDatabase.databaseWriteExecutor.execute(() -> {
            uDao.insert(device);
        });
    }
}
