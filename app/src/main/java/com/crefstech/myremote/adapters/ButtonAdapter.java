package com.crefstech.myremote.adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.crefstech.myremote.API.API;
import com.crefstech.myremote.R;
import com.crefstech.myremote.models.Commands;
import com.crefstech.myremote.models.smsDTO;
import com.crefstech.myremote.room.devices.Device;

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
        public android.widget.Button button;
        Commands item;

        public ViewHolder(View v) {

            super(v);


            button = v.findViewById(R.id.command);

        }

        public void setData(Commands item) {
            this.item = item;
            button.setText(item.getName());
            button.setOnClickListener(v -> {
                vibrator.cancel();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(100);
                }
               sendCommand(item.getSmsCommands());
            });

            //   textView.setText(item.getName());
//            Picasso.get().load(mContext.getString(R.string.url) + "api/icons/" + item.getIcon()).into(new Target() {
//                @Override
//                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                    BitmapDrawable bdrawable = new BitmapDrawable(bitmap);
//                //    imageButton.setBackground(bdrawable);
//                }
//
//                @Override
//                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//                }
//
//                @Override
//                public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                }
//            });
            //   relativeLayout.setBackgroundColor(mContext.getColor(R.color.app_green));

        }

    }

    @Override
    public ButtonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position) {
        Vholder.setData(mValues.get(position));

    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }

    private void sendCommand(String command) {

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
                }catch (Exception e){
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
    }
}







