package com.crefstech.myremote.room.devices;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DeviceViewModel extends AndroidViewModel {

    private DeviceRepo deviceRepo;
    private final LiveData<List<Device>> deviceLiveData;


    public DeviceViewModel(Application application) {
        super(application);
        deviceRepo = new DeviceRepo(application);
        deviceLiveData = deviceRepo.getUserLiveDevices();
    }

    LiveData<List<Device>> getUserLiveData() {
        return deviceLiveData;
    }

    public void insert(Device device) {
        deviceRepo.insert(device);
    }
}
