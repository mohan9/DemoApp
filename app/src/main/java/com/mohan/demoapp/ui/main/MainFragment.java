package com.mohan.demoapp.ui.main;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohan.demoapp.R;
import com.mohan.demoapp.model.Data;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private ArrayList<Data> dataArrayList = new ArrayList<>();
    private DataAdapter dataAdapter;
    private RecyclerView recyclerView;
    private AlertDialog alert;
    private AlertDialog.Builder builder;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainViewModel mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // TODO: Use the ViewModel

        setupRecyclerview();

        mViewModel.init();

        mViewModel.getNewsRepository().observe(getViewLifecycleOwner(), data -> {
            if (data == null) {
                showMessageDialog("Something went wrong...");
            } else {
                dataArrayList.addAll(data);
                dataAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setupRecyclerview() {
        dataAdapter = new DataAdapter(getActivity(), dataArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(dataAdapter);
    }

    private void showMessageDialog(String message) {
        try {
            if (alert != null) {
                if (alert.isShowing()) {
                    alert.cancel();
                }
            }
            builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialog, which) -> dialog.cancel());
            alert = builder.create();
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
