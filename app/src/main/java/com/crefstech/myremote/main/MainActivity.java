package com.crefstech.myremote.main;

import android.os.Bundle;
import android.view.View;

import com.crefstech.myremote.R;
import com.crefstech.myremote.databinding.ActivityMainBinding;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ActionBar toolbar = getSupportActionBar();
        toolbar.hide();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        //   BottomNavigationView bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation_view);
        NavigationUI.setupWithNavController(binding.bottomNav, navController);


//        binding.bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Bundle bundle = new Bundle();
//                Fragment fragment;
//                switch (item.getItemId()) {
//                    case R.id.navigation_gate:
//                        toolbar.setTitle("Gate");
//                        bundle.putString("type","gate");
//                        fragment = new hostFragment();
//                        loadFragment(fragment);
//                        return true;
//                    case R.id.navigation_garage:
//                        toolbar.setTitle("Garage");
//                        fragment = new hostFragment();
//                        loadFragment(fragment);
//                        return true;
//                    case  R.id.navigation_alarm:
//                        toolbar.setTitle("Garage");
//                        toolbar.setIcon(R.drawable.icons8_fire_alarm_box_96px);
//                        bundle.putString("type","garage");
//                        fragment = new hostFragment();
//                        loadFragment(fragment);
//                        return  true;
//                }
//                return false;
//            }
//        });
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.fragment_container_view_tag, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}