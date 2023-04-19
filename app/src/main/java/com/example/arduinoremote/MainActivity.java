package com.example.arduinoremote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.arduinoremote.FakeThings.FakeJoystickView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FakeJoystickView joystickView = new FakeJoystickView(this.getApplicationContext());
        this.addContentView(findViewById(joystickView.getId()), joystickView.getLayoutParams());
    }
}