package com.firestudio.projectasia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firestudio.projectasia.adapter.AsiaCountryAdapter;
import com.firestudio.projectasia.models.Example;
import com.firestudio.projectasia.network.ApiInterface;
import com.firestudio.projectasia.network.RetrofitClient;
import com.firestudio.projectasia.repository.AsiaCountryRepository;
import com.firestudio.viewmodel.AsiaCountryViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AsiaCountryRepository asiaCountryRepository;
    List<Example> exampleList;
    AsiaCountryAdapter asiaCountryAdapter;
    AsiaCountryViewModel asiaCountryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        asiaCountryRepository = new AsiaCountryRepository(getApplication());
        exampleList = new ArrayList<>();
        asiaCountryAdapter = new AsiaCountryAdapter(exampleList, this);

        asiaCountryViewModel = new ViewModelProvider(this).get(AsiaCountryViewModel.class);
        asiaCountryViewModel.getData().observe(this, new Observer<List<Example>>() {
            @Override
            public void onChanged(List<Example> examples) {
                asiaCountryAdapter.getAllCountry(examples);
                recyclerView.setAdapter(asiaCountryAdapter);
            }
        });

        loadData();
    }

    private void loadData() {
        ApiInterface apiInterface = RetrofitClient.getInstance().create(ApiInterface.class);
        Call<List<Example>> countaryList = apiInterface.getAsiaCountary();
        countaryList.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
         exampleList = response.body();
asiaCountryViewModel.insertData(response.body());
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Log.d("error",""+t.getMessage());
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.delete:
                asiaCountryViewModel.deleteData();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
