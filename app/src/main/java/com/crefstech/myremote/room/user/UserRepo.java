package com.crefstech.myremote.room.user;

import android.app.Application;

import com.crefstech.myremote.room.LocalRoomDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UserRepo {
    private userDao uDao;
    private LiveData<List<User>> userLiveData;


    UserRepo(Application application) {
        LocalRoomDatabase db = LocalRoomDatabase.getDatabase(application);
        uDao = db.userDao();
        userLiveData = uDao.getUser();
    }


    LiveData<List<User>> getUserLiveData() {
        return userLiveData;
    }

    void  insert(User user){
        LocalRoomDatabase.databaseWriteExecutor.execute(() -> {
            uDao.insert(user);
        });
    }
}
