package com.firestudio.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.firestudio.projectasia.models.Example;
import com.firestudio.projectasia.repository.AsiaCountryRepository;

import java.util.List;

public class AsiaCountryViewModel extends AndroidViewModel {
    private AsiaCountryRepository asiaCountryRepository;
    private LiveData<List<Example>> listLiveData;

    public AsiaCountryViewModel(@NonNull Application application) {
        super(application);

        asiaCountryRepository = new AsiaCountryRepository(application);
        listLiveData = asiaCountryRepository.getCountryLiveDataList();
    }

    public void insertData(List<Example> examples) {
        asiaCountryRepository.insert(examples);
    }

    public LiveData<List<Example>> getData() {
        return listLiveData;

    }

    public void deleteData() {
        asiaCountryRepository.deleteAsiaCountry();
    }

}
