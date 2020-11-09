package com.crefstech.myremote.room.user;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface userDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("SELECT * from user")
    LiveData<List<User>> getUser();


    @Query("SELECT * from user limit 1")
    List<User> getUserr();


    @Query("DELETE FROM USER")
    void Delete();
}
