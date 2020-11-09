package com.crefstech.myremote.room.user;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {

    private UserRepo userRepo;
    private final LiveData<List<User>> userLiveData;


   public UserViewModel(Application application) {
        super(application);
        userRepo  = new UserRepo(application);
        userLiveData = userRepo.getUserLiveData();
    }

    public LiveData<List<User>> getUserLiveData() {
        return userLiveData;
    }

   public void  insert(User user){
            userRepo.insert(user);
    }
}
