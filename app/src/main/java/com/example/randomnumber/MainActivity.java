package com.example.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private boolean binded=false;
    private RandomService randomService;
    private ServiceConnection connection;
    private TextView number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number=findViewById(R.id.tv_random);
        number.setText(""+randomService.generateRandom());
        connection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

                RandomService.MyBind binder = (RandomService.MyBind) iBinder;
                randomService = binder.getService();
                binded = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                binded = false;
            }
        };


    }
    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, RandomService.class);

        this.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (binded) {
            this.unbindService(connection);
            binded = false;
        }
    }
}
