package com.example.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
static final int ALARM_REQ_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editText = findViewById(R.id.edt);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                int time = Integer.parseInt(((EditText)(findViewById(R.id.edt))).getText().toString());
                long trigger = System.currentTimeMillis()+(time*1000);

                Intent intent = new Intent(MainActivity.this, MyReceiver.class);

                PendingIntent pi = PendingIntent.getBroadcast(
                        MainActivity.this,
                        ALARM_REQ_CODE,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
                );
                alarmManager.set(alarmManager.RTC_WAKEUP,trigger,pi);
            }
        });




    }


}