package com.example.hashmap_stringinteger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main2 extends AppCompatActivity {

    private TextView tvResult;
    private TextView tvMemoryInfo;
    private EditText etDataSize;
    private Map<String, Integer> performanceMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_2);

        etDataSize = findViewById(R.id.etDataSize);
        Button btnInsertion = findViewById(R.id.btnInsertion);
        Button btnIteration = findViewById(R.id.btnIteration);
        Button btnRandomQuery = findViewById(R.id.btnRandomQuery);
        Button btnDeletion = findViewById(R.id.btnDeletion);
        tvResult = findViewById(R.id.tvResult);
        tvMemoryInfo = findViewById(R.id.tvMemoryInfo);



        btnInsertion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dataSize = Integer.parseInt(etDataSize.getText().toString());
                performInsertion(dataSize);
            }
        });

        btnIteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dataSize = Integer.parseInt(etDataSize.getText().toString());
                performIteration(dataSize);
            }
        });

        btnRandomQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dataSize = Integer.parseInt(etDataSize.getText().toString());
                performRandomQuery(dataSize);
            }
        });

        btnDeletion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dataSize = Integer.parseInt(etDataSize.getText().toString());
                performDeletion(dataSize);
            }
        });
    }

    private void updateMemoryInfo() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);

        String memoryInfoText =
                "Total Memory: " + totalMemory / (1024 * 1024) + " MB\n" +
                "Free Memory: " + freeMemory / (1024 * 1024) + " MB\n" +
                "Memory Usage: " + (totalMemory - freeMemory) / (1024 * 1024) + " MB\n" ;
//                "Private Dirty: " + memoryInfo.getTotalPrivateDirty() + " KB\n" +
//                "Shared Dirty: " + memoryInfo.getTotalSharedDirty() + " KB";

        tvMemoryInfo.setText(memoryInfoText);
    }

    private void performInsertion(int dataSize) {
        long startTime = SystemClock.elapsedRealtime();
        performanceMap = new HashMap<>();
        for (int i = 0; i < dataSize; i++) {
            performanceMap.put("Key" + i, i);
        }
        long insertionTime = SystemClock.elapsedRealtime() - startTime;
        tvResult.setText("Insertion completed in " + insertionTime + " ms");
        updateMemoryInfo();
    }

    private void performIteration(int dataSize) {
        long startTime = SystemClock.elapsedRealtime();
        performanceMap = new HashMap<>();
        for (int i = 0; i < dataSize; i++) {
            performanceMap.put("Key" + i, i);
        }
        // Lặp qua các entry sử dụng for-each
        for (Map.Entry<String, Integer> entry : performanceMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            // In ra giá trị của mỗi entry
            System.out.println("Key: " + key + ", Value: " + value);

            // Đây là nơi bạn có thể thực hiện công việc cụ thể với giá trị và khóa
        }

        long iterationTime = SystemClock.elapsedRealtime() - startTime;
        tvResult.setText("Iteration completed in " + iterationTime + " ms");
        updateMemoryInfo();
    }

    private void performRandomQuery(int dataSize) {
        long startTime = SystemClock.elapsedRealtime();
        performanceMap = new HashMap<>();
        for (int i = 0; i < dataSize; i++) {
            performanceMap.put("Key" + i, i);
        }
        Random random = new Random(/* seed */);
        // Truy vấn ngẫu nhiên từ cấu trúc dữ liệu
        for (int i = 0; i < dataSize; i++) {
            int randomIndex = random.nextInt(dataSize);
            Integer value = performanceMap.get("Key" + randomIndex);

            // Đây là nơi bạn có thể thực hiện công việc cụ thể với giá trị được trả về
        }

        long randomQueryTime = SystemClock.elapsedRealtime() - startTime;
        tvResult.setText("Random Query completed in " + randomQueryTime + " ms");
        updateMemoryInfo();
    }

    private void performDeletion(int dataSize) {
        long startTime = SystemClock.elapsedRealtime();
        performanceMap = new HashMap<>();
        for (int i = 0; i < dataSize; i++) {
            performanceMap.put("Key" + i, i);
        }
        Random random = new Random();
        int randomIndex = random.nextInt(dataSize);
        String keyToRemove = "Key" + randomIndex;

        // Xóa phần tử từ HashMap bằng cách sử dụng phương thức remove
        Integer removedValue = performanceMap.remove(keyToRemove);

        if (removedValue != null) {
            long deletionTime = SystemClock.elapsedRealtime() - startTime;
            tvResult.setText("Deletion completed in " + deletionTime + " ms");
            updateMemoryInfo();
        } else {
            tvResult.setText("Deletion failed. Element not found.");
        }
    }
}
