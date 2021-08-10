package com.crefstech.myremote.adapters;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.crefstech.myremote.R;
import com.crefstech.myremote.models.Button;
import com.crefstech.myremote.models.Commands;

import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;



public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ViewHolder> {
    List<Commands> mValues;
    Context mContext;
    protected Vibrator vibrator;


    //  protected ItemListener mListener;
    public ButtonAdapter(Context context,  List<Commands> values) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        mValues = values;
        mContext = context;
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
                Toast toast = Toast.makeText(mContext, item.getSmsCommands(), Toast.LENGTH_SHORT);
                toast.show();
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

}
