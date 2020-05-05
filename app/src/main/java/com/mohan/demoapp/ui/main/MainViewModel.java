package com.mohan.demoapp.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mohan.demoapp.model.Data;
import com.mohan.demoapp.repository.DataRepo;

import java.util.List;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<List<Data>> mutableLiveData;
    private DataRepo dataRepo;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        dataRepo = DataRepo.getInstance();
        mutableLiveData = dataRepo.getData();

    }

    public LiveData<List<Data>> getNewsRepository() {
        return mutableLiveData;
    }
}
