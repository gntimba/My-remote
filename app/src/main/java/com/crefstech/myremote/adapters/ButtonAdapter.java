package com.crefstech.myremote.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crefstech.myremote.R;
import com.crefstech.myremote.models.Button;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ViewHolder> {
    List<Button> mValues;
    Context mContext;
    protected ItemListener mListener;

    public ButtonAdapter(Context context, List<Button> values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public ImageButton imageButton;
        public RelativeLayout relativeLayout;
        Button item;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            textView = v.findViewById(R.id.label);
            imageButton = v.findViewById(R.id.command);
            relativeLayout = v.findViewById(R.id.relativeLayout);

        }

        public void setData(Button item) {
            this.item = item;

            textView.setText(item.getName());
            Picasso.get().load(mContext.getString(R.string.url) + "api/icons/" + item.getIcon()).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    BitmapDrawable bdrawable = new BitmapDrawable(bitmap);
                    imageButton.setBackground(bdrawable);
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
            relativeLayout.setBackgroundColor(mContext.getColor(R.color.app_green));

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
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

    public interface ItemListener {
        void onItemClick(Button item);
    }
}
