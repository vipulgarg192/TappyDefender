package com.app.tappydefender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonPlay =
                (Button)findViewById(R.id.button);
        buttonPlay.setOnClickListener((View.OnClickListener) MainActivity.this);

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, GameActivity.class);
// Start our GameActivity class via the Intent
        startActivity(i);
// Now shut this activity down
        finish();
    }
}
