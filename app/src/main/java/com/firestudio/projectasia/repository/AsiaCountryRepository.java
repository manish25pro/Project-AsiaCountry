package com.firestudio.projectasia.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.firestudio.projectasia.dao.AsiaCountryDao;
import com.firestudio.projectasia.database.AsiaCountryDataBase;
import com.firestudio.projectasia.models.Example;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsiaCountryRepository {
    private AsiaCountryDataBase asiaCountryDataBase;
    private LiveData<List<Example>> countryLiveDataList;
    private Executor mExecutor = Executors.newSingleThreadExecutor();


    public AsiaCountryRepository(Application application) {
        asiaCountryDataBase = AsiaCountryDataBase.getINSTANCE(application);
        countryLiveDataList = asiaCountryDataBase.asiaCountryDao().getDataFromDataBase();


    }

    public void insert(List<Example> examples) {
        new InsertAsyncTask(asiaCountryDataBase).execute(examples);
    }

    public LiveData<List<Example>>getCountryLiveDataList(){
        return countryLiveDataList;
    }


    public void deleteAsiaCountry() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                asiaCountryDataBase.asiaCountryDao().deleteTableFromDataBase();
            }
        });
    }

    static class InsertAsyncTask extends AsyncTask<List<Example>, Void, Void> {
        private AsiaCountryDao asiaCountryDao;

        InsertAsyncTask(AsiaCountryDataBase asiaCountryDataBase) {
            asiaCountryDao = asiaCountryDataBase.asiaCountryDao();
        }

        @Override
        protected Void doInBackground(List<Example>... lists) {
            asiaCountryDao.insertCountryIntoDatabase(lists[0]);
return null;
        }
    }
}
