package com.crefstech.myremote.room.user;

import android.app.Application;

import com.crefstech.myremote.room.LocalRoomDatabase;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {

    private UserRepo userRepo;
     private final LiveData<User> userLiveData;


   public UserViewModel(Application application) {
        super(application);
        userRepo  = new UserRepo(application);
        userLiveData = userRepo.getUserLiveData();
    }

    LiveData<User> getUserLiveData(){
        return userLiveData;
    }

   public void  insert(User user){
            userRepo.insert(user);
    }
}
