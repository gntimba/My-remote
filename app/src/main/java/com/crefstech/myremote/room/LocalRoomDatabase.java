package com.crefstech.myremote.room;

import android.content.Context;

import com.crefstech.myremote.room.devices.Device;
import com.crefstech.myremote.room.devices.DeviceDao;
import com.crefstech.myremote.room.user.User;
import com.crefstech.myremote.room.user.userDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
//@Database(entities = {User.class, Device.class}, version = 1, exportSchema = true,autoMigrations = {@AutoMigration(from = 1 ,to=2)})
@Database(entities = {User.class, Device.class}, version = 1, exportSchema = true)
public abstract class LocalRoomDatabase extends RoomDatabase {
    public abstract userDao userDao();
    public abstract DeviceDao deviceDao();

    private static volatile LocalRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static public final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static LocalRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocalRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LocalRoomDatabase.class, "localDatabase")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
