package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG=MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE= "com.example.android.ActivityLifecycleChallenge.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;
    private TextView mReplyTextView;
    private int[] textviewId_List = new int[] {R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5,R.id.item6,
            R.id.item7, R.id.item8 , R.id.item9, R.id.item10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            for (int id : textviewId_List) {
                boolean isVisible = savedInstanceState.getBoolean("reply_visible" + id);
                if (isVisible) {
                    mReplyTextView = findViewById(id);
                    mReplyTextView.setText(savedInstanceState.getString("reply_text" + id));
                    mReplyTextView.setVisibility(View.VISIBLE);
                }
            }

        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        // If the heading is visible, message needs to be saved.
        // Otherwise we're still using default layout.
        for (int id: textviewId_List) {
            if (findViewById(id).getVisibility() == View.VISIBLE) {
                outState.putBoolean("reply_visible" + id, true);
                mReplyTextView = findViewById(id);
                outState.putString("reply_text" + id, mReplyTextView.getText().toString());
            }
        }
    }


    public void launchSecondActivity(View view) {
        Intent intent=new Intent(this, SecondActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(LOG_TAG, "0");
        if (requestCode == TEXT_REQUEST) {
            Log.d(LOG_TAG, "1");
            if (resultCode == RESULT_OK) {
                Log.d(LOG_TAG, "2");
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                Log.d(LOG_TAG, reply);
                mReplyTextView = getEmptyTextview();
                if(mReplyTextView != null){
                    mReplyTextView.setText(reply);
                    mReplyTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    private TextView getEmptyTextview() {
        for(int id: textviewId_List){
            if (findViewById(id).getVisibility() != View.VISIBLE){

                return findViewById(id);
            }
        }
        return null;
    }
}