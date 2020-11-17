package com.crefstech.myremote.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.crefstech.myremote.API.API;
import com.crefstech.myremote.R;
import com.crefstech.myremote.databinding.ActivityLoginBinding;
import com.crefstech.myremote.main.MainActivity;
import com.crefstech.myremote.models.Auth;
import com.crefstech.myremote.models.Device;
import com.crefstech.myremote.room.devices.DeviceViewModel;
import com.crefstech.myremote.room.user.User;
import com.crefstech.myremote.room.user.UserViewModel;
import com.google.gson.Gson;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;
    private DeviceViewModel deviceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();
        binding.email.getEditText().setText("gntimba@gmail.com");
        binding.password.getEditText().setText("magofifi");
        binding.progressBar1.setVisibility(View.INVISIBLE);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        deviceViewModel = new ViewModelProvider(this).get(DeviceViewModel.class);


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.email.setErrorEnabled(false);
                binding.password.setErrorEnabled(false);
                if (binding.email.getEditText().getText().toString().length() < 1 || !binding.email.getEditText().getText().toString().contains("@")) {
                    binding.email.setError("Invalid email");
                } else if (binding.password.getEditText().getText().toString().length() < 1) {
                    binding.email.setError("Invalid Password");
                } else {
                    showProgress(true);
                    doLogin(binding.email.getEditText().getText().toString(), binding.password.getEditText().getText().toString());

                }
            }
        });

    }

    public void showProgress(Boolean show) {
        if (show) {
            binding.password.setVisibility(View.GONE);
            binding.email.setVisibility(View.GONE);
            binding.btnLinkToRegisterScreenCustomer.setVisibility(View.GONE);
            binding.btnLogin.setVisibility(View.GONE);
            binding.progressBar1.setVisibility(View.VISIBLE);
        } else {
            binding.password.setVisibility(View.VISIBLE);
            binding.email.setVisibility(View.VISIBLE);
            binding.btnLinkToRegisterScreenCustomer.setVisibility(View.VISIBLE);
            binding.btnLogin.setVisibility(View.VISIBLE);
            binding.progressBar1.setVisibility(View.GONE);
        }
    }

    private void getExisting(User token) {
        Call<List<Device>> call = API.getAPIService(getApplicationContext()).getUserDevices("Bearer " + token.getToken());
        call.enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                Log.d("TAG", response.code() + "");
                try {
                    for (Device resp : response.body()) {
                        Gson gson = new Gson();
                        Log.e("Login", resp.getCustomName());
                        Log.e("Login", gson.toJson(resp.getCommands()));
                        com.crefstech.myremote.room.devices.Device device = new com.crefstech.myremote.room.devices.Device();

                        device.setCommands(gson.toJson(resp.getCommands()));
                        device.setCustomName(resp.getCustomName());
                        device.setDescription(resp.getDescription());
                        device.setId(resp.getId());
                        device.setModel(resp.getModel());
                        device.setType(resp.getType());
                        device.setPhoneNo(resp.getPhoneNo());
                        device.setPicture(resp.getPicture());
                        deviceViewModel.insert(device);

                    }

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } catch (Exception e) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void doLogin(String email, String password) {

        Auth auth = new Auth();
        auth.setMail(email);
        auth.setPassword(password);

        Call<Auth> call = API.getAPIService(getApplicationContext()).login(auth);
        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {

                try {
                    Log.d("TAG", response.code() + "");
                    Auth respons = response.body();
                    User user = new User(respons.getId(), respons.getToken());

                    SharedPreferences mPreferences = getSharedPreferences(getApplicationContext().getString(R.string.token), MODE_PRIVATE);
                    SharedPreferences.Editor editor = mPreferences.edit();
                    editor.putString(getApplicationContext().getString(R.string.token), "Bearer " + user.getToken());
                    editor.apply();
                    userViewModel.insert(user);
                    System.out.print(respons);
//                    Toast toast = Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG);
//                    toast.show();
                    getExisting(user);
                } catch (Exception exception) {
                    showProgress(false);
                    Toast toast2 = Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG);
                    toast2.show();
                    exception.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                Toast toast3 = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast3.show();
                t.printStackTrace();
                showProgress(false);

            }
        });


    }
}