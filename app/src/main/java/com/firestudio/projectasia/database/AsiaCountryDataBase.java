package com.firestudio.projectasia.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.firestudio.projectasia.dao.AsiaCountryDao;
import com.firestudio.projectasia.models.Example;

@Database(entities = {Example.class}, version = 4)
public abstract class AsiaCountryDataBase extends RoomDatabase {

    public static String DATABASE_NAME = "asiaCountryDataBase";

    public abstract AsiaCountryDao asiaCountryDao();

    public static volatile AsiaCountryDataBase INSTANCE;

    public static AsiaCountryDataBase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (AsiaCountryDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AsiaCountryDataBase.class, DATABASE_NAME).fallbackToDestructiveMigration().addCallback(callback).build();
                }

            }
        }
        return INSTANCE;
    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    static class AsiaCountryAsyncTask extends AsyncTask<Void, Void, Void> {
        private AsiaCountryDao asiaCountryDao;

        AsiaCountryAsyncTask(AsiaCountryDataBase asiaCountryDataBase) {
            asiaCountryDao = asiaCountryDataBase.asiaCountryDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {
           asiaCountryDao.deleteTableFromDataBase();
            return null;
        }
    }
}


