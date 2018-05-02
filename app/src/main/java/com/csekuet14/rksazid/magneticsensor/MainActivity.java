package com.csekuet14.rksazid.magneticsensor;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    TextView Mtext,magnetDetecTextView;
    float magnitude = 0,x,y,z;
    int max = 200;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
        Mtext = findViewById(R.id.m_textView);
        magnetDetecTextView = findViewById(R.id.magnet_detect_textView);
        progressBar = findViewById(R.id.magnetic_progressbar);
        progressBar.setMax(max);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];

        magnitude = (float) Math.sqrt(x*x+y*y+z*z);
        max = (magnitude>max)? (int) magnitude : max;
        progressBar.setMax(max);
        progressBar.setProgress((int) magnitude);
        if(magnitude>60.0)
            magnetDetecTextView.setText("Magnet Detected!!!!");
        else magnetDetecTextView.setText("");
        Mtext.setText(String.valueOf(magnitude)+"µT");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
