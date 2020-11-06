package com.crefstech.myremote.room.user;

import android.app.Application;

import com.crefstech.myremote.room.LocalRoomDatabase;

import androidx.lifecycle.LiveData;

public class UserRepo {
    private userDao uDao;
    private LiveData<User> userLiveData;


    UserRepo(Application application) {
        LocalRoomDatabase db = LocalRoomDatabase.getDatabase(application);
        uDao = db.userDao();
        userLiveData = uDao.getUser();
    }

    LiveData<User> getUserLiveData(){
        return userLiveData;
    }

    void  insert(User user){
        LocalRoomDatabase.databaseWriteExecutor.execute(() -> {
            uDao.insert(user);
        });
    }
}
