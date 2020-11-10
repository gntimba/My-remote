package com.crefstech.myremote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crefstech.myremote.R;
import com.crefstech.myremote.models.Commands;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {

    List<Commands> mValues;
    Context mContext;
    // protected ButtonAdapter.ItemListener mListener;

    public DeviceAdapter(Context context, List<Commands> values) {

        mValues = values;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public ImageView imageView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            textView = v.findViewById(R.id.label);
            imageView = v.findViewById(R.id.command);
            relativeLayout = v.findViewById(R.id.relativeLayout);

        }

//        public void setData(DataModel item) {
//            this.item = item;
//
//            textView.setText(item.text);
//            imageView.setImageResource(item.drawable);
////            relativeLayout.setBackgroundColor(Color.parseColor(item.color));
//
//        }


        @Override
        public void onClick(View view) {
//            if (mListener != null) {
//             //   mListener.onItemClick(item);
//            }
        }
    }

    @Override
    public DeviceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false);

        return new DeviceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceAdapter.ViewHolder Vholder, int position) {
        // Vholder.setData(mValues.get(position));

    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        //void onItemClick(DataModel item);
    }
}
