package com.example.randomnumber;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class RandomService extends Service {
    private final  IBinder binder=new MyBind();


    public RandomService() {
    }





    public class MyBind extends Binder
    {
        public RandomService getService()
        {
            return RandomService.this;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent) {

        return  binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }
    public int generateRandom()
    {
        Random random=new Random();
        return random.nextInt(100);
    }
}
