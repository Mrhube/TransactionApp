package com.example.transactionapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.transactionapp.misc.Settings;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Context ctx = getApplicationContext();
        final EditText url = findViewById(R.id.settings_url);
        final EditText port = findViewById(R.id.settings_port);

        // Set input fields to existing values
        url.setText(Settings.getURL(ctx));
        port.setText(Integer.toString(Settings.getPort(ctx)));

        url.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Settings.setURL(ctx, url.getText().toString());
                }
            }
        });

        port.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Settings.setPort(ctx, Integer.parseInt(port.getText().toString()));
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context ctx = getApplicationContext();
        EditText url = findViewById(R.id.settings_url);
        EditText port = findViewById(R.id.settings_port);

        Settings.setURL(ctx, url.getText().toString());
        Settings.setPort(ctx, Integer.parseInt(port.getText().toString()));

        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
        return super.onOptionsItemSelected(item);
    }
}
