package com.crefstech.myremote.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.crefstech.myremote.R;
import com.crefstech.myremote.adapters.ButtonAdapter;
import com.crefstech.myremote.databinding.FragmentGateBinding;
import com.crefstech.myremote.models.Commands;
import com.crefstech.myremote.room.LocalRoomDatabase;
import com.crefstech.myremote.room.devices.Device;
import com.crefstech.myremote.room.devices.DeviceViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class GateFragment extends Fragment {
    Gson gson = new Gson();
    private static final String TAG = "gateFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FragmentGateBinding fragmentHostBinding;
    private DeviceViewModel deviceViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        fragmentHostBinding = FragmentGateBinding.inflate(inflater, container, false);

        getDevice();
        return fragmentHostBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentHostBinding = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentHostBinding = null;
    }

    private void getDevice() {


        class getDevice extends AsyncTask<Void, Void, List<Device>> {

            @Override
            protected List<Device> doInBackground(Void... voids) {
                try {
                   // Log.e(TAG, LocalRoomDatabase.getDatabase(getActivity()).deviceDao().getDevicess().get(0).getCommands());
                    return LocalRoomDatabase.getDatabase(getActivity()).deviceDao().getDevicesByType("GATE");
                } catch (Exception e) {
                    e.printStackTrace();

                    return null;
                }

            }

            @Override
            protected void onPostExecute(List<Device> device) {
                super.onPostExecute(device);

                try {

                    ArrayAdapter<Device> adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, device);
                    fragmentHostBinding.selectGate.setAdapter(adapter);
                    fragmentHostBinding.selectGate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Device item = (Device) parent.getItemAtPosition(position);
                            Log.e(TAG, item.getCommands());

                            Type type = new TypeToken<ArrayList<Commands>>() {
                            }.getType();
                             List<Commands>  commands = gson.fromJson(item.getCommands(), type);
                            startRecycler(commands, item);
                        }
                    });

                    if(device.size()>0){
                        fragmentHostBinding.selectGate.setText(fragmentHostBinding.selectGate.getAdapter().getItem(0).toString(),false);
                        Type type = new TypeToken<ArrayList<Commands>>() {
                        }.getType();
                        List<Commands>  commands = gson.fromJson(device.get(0).getCommands(), type);
                        startRecycler(commands, device.get(0));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        getDevice ge = new getDevice();
        ge.execute();
    }

    public void startRecycler(List<Commands> button, Device item) {
        ButtonAdapter adapter = new ButtonAdapter(getActivity(), button,item);
        fragmentHostBinding.recyclerGate.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        manager.setReverseLayout(true);
        fragmentHostBinding.recyclerGate.setLayoutManager(manager);
    }

}