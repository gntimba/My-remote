package com.crefstech.myremote.login;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.crefstech.myremote.API.API;
import com.crefstech.myremote.R;
import com.crefstech.myremote.databinding.ActivityLoginBinding;
import com.crefstech.myremote.models.Auth;


public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();
        binding.email.getEditText().setText("gntimba@gmail.com");
        binding.password.getEditText().setText("magofifi");


       binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.email.setErrorEnabled(false);
                binding.password.setErrorEnabled(false);
                if(binding.email.getEditText().getText().toString().length()<1 ||!binding.email.getEditText().getText().toString().contains("@") ){
                    binding.email.setError("Invalid email");
                } else if(binding.password.getEditText().getText().toString().length()<1){
                    binding.email.setError("Invalid Password");
                }
                else
                    doLogin(binding.email.getEditText().getText().toString(),binding.password.getEditText().getText().toString());
            }
        });

    }


  public void  doLogin(String email,String password){
        Auth auth = new Auth();
        auth.setMail(email);
        auth.setPassword(password);

      Call<Auth> call = API.getAPIService(getApplicationContext()).login(auth);
      call.enqueue(new Callback<Auth>() {
          @Override
          public void onResponse(Call<Auth> call, Response<Auth> response) {

              try{
                  Log.d("TAG",response.code()+"");
                  Auth respons= response.body();

                  System.out.print(respons);
              Toast toast=   Toast.makeText(getApplicationContext(),respons.getId(),Toast.LENGTH_LONG);
              toast.show();
              }catch (Exception exception){
                  exception.printStackTrace();
              }

          }

          @Override
          public void onFailure(Call<Auth> call, Throwable t) {
              t.printStackTrace();

          }
      });


  }
}