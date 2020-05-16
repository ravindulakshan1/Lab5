package com.example.broadcastproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final static String BROADCAST_ACTION = "android.intent.action.MAIN";

    Receiver localListener;
    TextView ViewMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewMsg = findViewById(R.id.textView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        localListener = new Receiver();
        IntentFilter intentfilter =new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener, intentfilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListener);
    }

    public  void onClick(View view){
        if(view.getId() == R.id.button){
            BackgroundServices.startAction(this.getApplicationContext());
        }
    }

    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String currentText = ViewMsg.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nReceived "+ message;
            ViewMsg.setText(currentText);
        }
    }
}
