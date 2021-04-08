package com.maskedgeek.androidinterviewprep.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maskedgeek.androidinterviewprep.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView textView;
    ImageView imageView;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.text);
        imageView = itemView.findViewById(R.id.imageview);
    }


}
