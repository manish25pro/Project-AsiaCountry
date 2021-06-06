package com.firestudio.projectasia.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.firestudio.projectasia.models.Example;

import java.util.List;

@Dao
public interface AsiaCountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountryIntoDatabase(List<Example> countryList);


    @Query("DELETE FROM asiacountrytable")
    void deleteTableFromDataBase();

    @Query("SELECT * FROM asiacountrytable")
    LiveData<List<Example>> getDataFromDataBase();
}
