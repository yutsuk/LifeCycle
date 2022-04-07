package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.activitylifecyclechallenge.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void addItem(View view) {
        String item = ((Button)view).getText().toString();
        Intent itemIntent = new Intent();
        itemIntent.putExtra(EXTRA_REPLY, item);
        setResult(RESULT_OK, itemIntent);
        finish();
    }
}