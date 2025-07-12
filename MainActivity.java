package com.example.myapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.models.DataModel;
import com.example.myapp.repository.Repository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Repository repository;
    private MutableLiveData<List<DataModel>> dataListLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private RecyclerView recyclerView;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         recyclerView = findViewById(R.id.recyclerView);
         errorTextView = findViewById(R.id.errorTextView);

         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         final DataAdapter adapter = new DataAdapter();
         recyclerView.setAdapter(adapter);

         repository = new Repository();

         dataListLiveData.observe(this, new Observer<List<DataModel>>() {
             @Override
             public void onChanged(List<DataModel> dataModels) {
                 errorTextView.setVisibility(TextView.GONE);
                 recyclerView.setVisibility(RecyclerView.VISIBLE);
                 adapter.setData(dataModels);
             }
         });

         errorLiveData.observe(this, new Observer<String>() {
             @Override
             public void onChanged(String errorMsg) {
                 recyclerView.setVisibility(RecyclerView.GONE);
                 errorTextView.setVisibility(TextView.VISIBLE);
                 errorTextView.setText(errorMsg);
             }
         });

         repository.fetchData(dataListLiveData, errorLiveData);
    }
}
