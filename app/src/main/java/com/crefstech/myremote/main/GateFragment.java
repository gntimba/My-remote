package com.crefstech.myremote.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.crefstech.myremote.adapters.ButtonAdapter;
import com.crefstech.myremote.databinding.FragmentHostBinding;
import com.crefstech.myremote.models.Button;
import com.crefstech.myremote.models.Commands;
import com.crefstech.myremote.room.LocalRoomDatabase;
import com.crefstech.myremote.room.devices.Device;
import com.crefstech.myremote.room.devices.DeviceViewModel;
import com.google.gson.Gson;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class GateFragment extends Fragment {

    private static final String TAG = "gateFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FragmentHostBinding fragmentHostBinding;
    private DeviceViewModel deviceViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle b = new Bundle();
        String type = b.getString("type");
        // Inflate the layout for this fragment
        fragmentHostBinding = FragmentHostBinding.inflate(inflater, container, false);
        getDevice();
        return fragmentHostBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentHostBinding = null;
    }

    private void getDevice() {
        class getDevice extends AsyncTask<Void, Void, List<Device>> implements ButtonAdapter.ItemListener {

            @Override
            protected List<Device> doInBackground(Void... voids) {
                try {
                    Log.e(TAG, LocalRoomDatabase.getDatabase(getActivity()).deviceDao().getDevicess().get(0).getCommands());
                    return LocalRoomDatabase.getDatabase(getActivity()).deviceDao().getDevicess();
                } catch (Exception e) {
                    e.printStackTrace();

                    return null;
                }

            }

            @Override
            protected void onPostExecute(List<Device> device) {
                super.onPostExecute(device);
                try {
                    Gson gson = new Gson();
                    Commands commands = gson.fromJson(device.get(0).getCommands(), Commands.class);
                    ButtonAdapter adapter = new ButtonAdapter(getActivity(), commands.getButton(), this);
                    fragmentHostBinding.recyclerGate.setAdapter(adapter);
                    GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
                    fragmentHostBinding.recyclerGate.setLayoutManager(manager);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onItemClick(Button item) {
                Toast toast = Toast.makeText(getActivity(), item.getName(), Toast.LENGTH_LONG);
                toast.show();
            }
        }

        getDevice ge = new getDevice();
        ge.execute();
    }


}