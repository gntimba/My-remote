package com.crefstech.myremote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.crefstech.myremote.login.LoginActivity;
import com.crefstech.myremote.main.MainActivity;
import com.crefstech.myremote.room.LocalRoomDatabase;
import com.crefstech.myremote.room.user.User;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "SplashSreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getUers();


    }


    private void getUers() {
        class GetUsers extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                try {
                    User user = LocalRoomDatabase.getDatabase(getApplication()).userDao().getUserr().get(0);
                    Log.e(TAG, user.getToken());
                    return user;
                } catch (Exception e) {
                    e.printStackTrace();
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    finish();
                    return null;
                }

            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                try {
                    if (user.getToken() != null) {
                        Log.e(TAG, user.getToken());
                        SharedPreferences mPreferences = getSharedPreferences(getApplicationContext().getString(R.string.token), MODE_PRIVATE);
                        SharedPreferences.Editor editor = mPreferences.edit();
                        editor.putString(getApplicationContext().getString(R.string.token), "Bearer " + user.getToken());
                        editor.putString("id", user.getId());
                        editor.apply();


                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        finish();
                    } else {
                        Log.e(TAG, "no user available");
                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    finish();
                }
            }
        }

        GetUsers getUsers = new GetUsers();
        getUsers.execute();
    }
}