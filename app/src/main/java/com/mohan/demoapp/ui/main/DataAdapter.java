package com.mohan.demoapp.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mohan.demoapp.R;
import com.mohan.demoapp.model.Data;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataHolder> {

    private ArrayList<Data> arrayList;
    private FragmentActivity appCompatActivity;

    DataAdapter(FragmentActivity activity, ArrayList<Data> dataArrayList) {
        this.arrayList = dataArrayList;
        this.appCompatActivity = activity;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(appCompatActivity).inflate(R.layout.recyclerview_row, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        Data data = arrayList.get(position);
        holder.tv_title.setText(data.getTitle());
        holder.tv_description.setText(data.getDescription());

        Glide.with(appCompatActivity).load(data.getImage()).into(holder.appCompatImageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class DataHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tv_title, tv_description;
        AppCompatImageView appCompatImageView;


        DataHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            appCompatImageView = itemView.findViewById(R.id.appCompatImageView);
        }
    }
}
