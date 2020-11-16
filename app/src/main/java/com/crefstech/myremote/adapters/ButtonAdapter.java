package com.crefstech.myremote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.crefstech.myremote.R;
import com.crefstech.myremote.models.Button;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ViewHolder> {
    List<Button> mValues;
    Context mContext;

    //  protected ItemListener mListener;
    public ButtonAdapter(Context context, List<Button> values) {

        mValues = values;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public android.widget.Button button;
        Button item;

        public ViewHolder(View v) {

            super(v);


            button = v.findViewById(R.id.command);

        }

        public void setData(Button item) {
            this.item = item;
            button.setText(item.getName());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(mContext, item.getName(), Toast.LENGTH_SHORT);
                    toast.show();
                }
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
