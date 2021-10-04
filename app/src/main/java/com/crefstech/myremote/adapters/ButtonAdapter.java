package com.crefstech.myremote.adapters;

import static android.content.Context.MODE_PRIVATE;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.crefstech.myremote.API.API;
import com.crefstech.myremote.R;
import com.crefstech.myremote.databinding.RecyclerViewItemBinding;
import com.crefstech.myremote.models.Commands;
import com.crefstech.myremote.models.smsDTO;
import com.crefstech.myremote.room.devices.Device;
import com.crefstech.myremote.util.util;

import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ViewHolder> {
    List<Commands> mValues;
    Context mContext;
    protected Vibrator vibrator;
    Device device;


    //  protected ItemListener mListener;
    public ButtonAdapter(Context context, List<Commands> values, Device device) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        mValues = values;
        mContext = context;
        this.device = device;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewItemBinding binding;

        public ViewHolder(RecyclerViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public ButtonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Commands item = mValues.get(position);
        viewHolder.binding.description.setText(item.getName());
        //   viewHolder.binding.imageDescription.setColorFilter(ContextCompat.getColor(mContext,R.color.red), PorterDuff.Mode.SRC_IN);
//        Picasso.get().load(mContext.getString(R.string.url) + "images/" + item.getIcon())
//                .placeholder(R.drawable.default_icon)
//                .into(viewHolder.binding.imageDescription);

        Glide.with(mContext).load(mContext.getString(R.string.url) + "images/" + item.getIcon())
                .placeholder(R.drawable.default_icon)
                .into(viewHolder.binding.imageDescription);

        viewHolder.binding.mainCard.setOnClickListener(v -> {
            vibrator.cancel();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(100);
            }
            sendSMS(item.getSmsCommands());
        });

    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }

    private void sendCommand(String command) {
        if(      util.isNetworkAvailable(mContext)) {

            SharedPreferences mPreferences = mContext.getSharedPreferences(mContext.getString(R.string.token), MODE_PRIVATE);
            String token = mPreferences.getString("token", "");
            String id = mPreferences.getString("id", "");
            smsDTO sms = new smsDTO(command, id, device.getPhoneNo());
            Call<smsDTO> call = API.getAPIService(mContext).sendCommand(token, sms);
            call.enqueue(new Callback<smsDTO>() {
                @SneakyThrows
                @Override
                public void onResponse(Call<smsDTO> call, Response<smsDTO> response) {

                    try {
                        if (response.isSuccessful()) {
                            Toast toast = Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(mContext, response.errorBody().string(), Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } catch (Exception e) {
                        Toast toast = Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }

                @Override
                public void onFailure(Call<smsDTO> call, Throwable t) {
                    Toast toast = Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                    t.printStackTrace();
                }
            });
        }else
            Toast.makeText(mContext, "Check if network is available", Toast.LENGTH_LONG).show();

    }

    private void sendSMS (String comand){
        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);


       // smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.putExtra("address"  , device.getPhoneNo());
        smsIntent.putExtra("sms_body"  , comand);

        try {
            mContext.startActivity(smsIntent);

            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(mContext,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}







