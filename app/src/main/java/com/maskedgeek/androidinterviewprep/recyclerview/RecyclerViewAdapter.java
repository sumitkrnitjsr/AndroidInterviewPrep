package com.maskedgeek.androidinterviewprep.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.maskedgeek.androidinterviewprep.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private List<String> list = new ArrayList<>();
    private String TAG = RecyclerViewAdapter.class.getSimpleName();
    private AdapterActivityCommunicationInterface listener;

    public RecyclerViewAdapter(List<String> input,AdapterActivityCommunicationInterface activity){
        list = input;
        listener = activity;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, " onCreateViewHolder ");
        // create the customviewholder UI to optimize references to findviewbyid
        // using parent to create correct subclass of LayoutParams for root view xml
        //  java.lang.IllegalStateException: ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholderitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Log.d(TAG, " onBindViewHolder ");
        holder.textView.setText(list.get(position));
        Glide.with(holder.itemView.getContext()).load(R.mipmap.ic_launcher).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, " getItemCount ");
        return list.size();
    }
}
