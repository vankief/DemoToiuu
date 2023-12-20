package com.example.hashmap_stringinteger;

import android.os.Bundle;
import android.os.Debug;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hashmap_stringinteger.model.DataRequest;
import com.example.hashmap_stringinteger.retrofit.APIHelper;
import com.example.hashmap_stringinteger.retrofit.StringCallBack;

import java.util.Random;

public class HashvsSparse extends AppCompatActivity {
    SparseArray<Long> performanceMap = new SparseArray<>();
    //HashMap<Integer, Long> performanceMap = new HashMap<>();
    public Integer data_size = 80000;
    private TextView tvResult;
    private TextView tvMemoryInfo;
    private EditText editTextDataSize;
    private DataRequest request;
    public APIHelper.APIService apiService;
    private long iterationTime, insertionTime, randomQueryTime, deletionTime, memoryUse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnPerformOperations = findViewById(R.id.btnPerformOperations);
        tvResult = findViewById(R.id.tvResult);
        tvMemoryInfo = findViewById(R.id.tvMemoryInfo);
        editTextDataSize = findViewById(R.id.editTextDataSize);

        btnPerformOperations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperations();
            }
        });
    }

    private void updateMemoryInfo(int dataSize, long insertionTime) {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        memoryUse = (totalMemory - freeMemory) / (1024 * 1024);
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);

        String memoryInfoText = "Total Memory: " + totalMemory / (1024 * 1024) + " MB\n" +
                "Free Memory: " + freeMemory / (1024 * 1024) + " MB\n" +
                "Memory Usage: " + memoryUse + " MB\n";

        tvMemoryInfo.setText(memoryInfoText);
    }

    private void performOperations() {
        // Lấy kích thước dữ liệu từ EditText
//        String dataSizeString = editTextDataSize.getText().toString();
//        String dataSizeString = "10000";
//        if (dataSizeString.isEmpty()) {
//            tvResult.setText("Please enter data size");
//            return;
//        }

        int dataSize = data_size;
        tvResult.setText("");

        // Thực hiện thao tác Insertion
        performInsertion(dataSize);

        // Thực hiện thao tác Iteration
        performIteration();

        // Thực hiện thao tác Random Query
        performRandomQuery();

        // Thực hiện thao tác Deletion
        performDeletion(dataSize);
        request = new DataRequest();
        request.setMap_structure("SparseArray");
        //request.setMap_structure( "HashMap" );
        request.setData_type("s-i");
        request.setSize_array(dataSize);
        request.setInsertion((int) insertionTime);
        request.setIteration((int) iterationTime);
        request.setRandom_query((int) randomQueryTime);
        request.setDeletion((int) deletionTime);
        request.set_memory_usage((int) memoryUse);
        callApiData(request);
    }

    private void callApiData(DataRequest request) {
        if (request != null) {
            new APIHelper().postData(request, new StringCallBack() {
                @Override
                public void execute(String str) {
                    Toast.makeText(HashvsSparse.this, "aaaaa", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void performInsertion(int dataSize) {
        long startTime = Debug.threadCpuTimeNanos();
        for (int i = 0; i < dataSize; i++) {
//            SparseArray
            performanceMap.put(i, (long) i);
//            HashMap
//            performanceMap.put(i, (long) i);
        }
        long endTime = Debug.threadCpuTimeNanos();
        insertionTime = (endTime - startTime) / 1000000;

        tvResult.append("\nInsertion completed in " + insertionTime + " ms");

        updateMemoryInfo(dataSize, insertionTime);
    }

    private void performIteration() {
        long startTime = Debug.threadCpuTimeNanos();

        // Lặp qua các entry sử dụng for-each
        for (int i = 0; i < performanceMap.size(); i++) {
//            SparseArray
            int key = performanceMap.keyAt(i);
            Long value = performanceMap.get(key);
//            HashMap
//            int key = i;
//            Long value = performanceMap.get(key);
        }
        long endTime = Debug.threadCpuTimeNanos();

        iterationTime = (endTime - startTime) / 1000000;
        tvResult.append("\nIteration completed in " + iterationTime + " ms");



    }

    private void performRandomQuery() {
        int seed = 213523;
        Long[] arrValue = new Long[data_size];
        long startTime = Debug.threadCpuTimeNanos();

        Random random = new Random(seed);
        int dataSize = performanceMap.size();

        for (int i = 0; i < dataSize; i++) {
            int randomIndex = random.nextInt(dataSize);
            Long value = performanceMap.get(randomIndex);
            arrValue[i] = value;
        }
        long endTime = Debug.threadCpuTimeNanos();

        randomQueryTime = (endTime - startTime) / 1000000;
        tvResult.append("\nRandom Query completed in " + randomQueryTime + " ms");
    }

    private void performDeletion(int dataSize) {
        long startTime = Debug.threadCpuTimeNanos();
        for (int i = 0; i < dataSize; i++) {
            performanceMap.remove(i);
        }
        deletionTime = (Debug.threadCpuTimeNanos() - startTime) / 1000000;
        tvResult.append("\nDeletion completed in " + deletionTime + " ms");
    }
}
