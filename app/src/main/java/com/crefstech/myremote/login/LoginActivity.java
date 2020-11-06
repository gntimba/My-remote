package com.crefstech.myremote.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
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
import com.crefstech.myremote.room.user.User;
import com.crefstech.myremote.room.user.UserViewModel;


public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;
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
                else {
                    showProgress(true);
                    doLogin(binding.email.getEditText().getText().toString(), binding.password.getEditText().getText().toString());

                }
            }
        });

    }

    public void showProgress(Boolean show){
        if(show) {
            binding.password.setVisibility(View.INVISIBLE);
            binding.email.setVisibility(View.INVISIBLE);
            binding.btnLinkToRegisterScreenCustomer.setVisibility(View.INVISIBLE);
            binding.btnLogin.setVisibility(View.INVISIBLE);
            binding.progressBar1.setVisibility(View.VISIBLE);
        }else {
            binding.password.setVisibility(View.VISIBLE);
            binding.email.setVisibility(View.VISIBLE);
            binding.btnLinkToRegisterScreenCustomer.setVisibility(View.VISIBLE);
            binding.btnLogin.setVisibility(View.VISIBLE);
            binding.progressBar1.setVisibility(View.INVISIBLE);
        }
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
                  User user = new User(respons.getId(),respons.getToken());
                  userViewModel.insert(user);
                  System.out.print(respons);
              Toast toast=   Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_LONG);
              toast.show();
              showProgress(false);
              }catch (Exception exception){
                  showProgress(false);
                  Toast toast2 =   Toast.makeText(getApplicationContext(),exception.getMessage(),Toast.LENGTH_LONG);
                  toast2.show();
                  exception.printStackTrace();
              }

          }

          @Override
          public void onFailure(Call<Auth> call, Throwable t) {
              Toast toast3 =   Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG);
              toast3.show();
              t.printStackTrace();
              showProgress(false);

          }
      });


  }
}