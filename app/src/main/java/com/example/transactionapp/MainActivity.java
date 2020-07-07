package com.example.transactionapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.transactionapp.misc.FileReadWrite;
import com.example.transactionapp.misc.Settings;
import com.example.transactionapp.structure.Transaction;
import com.example.transactionapp.ui.TransactionTable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Settings.initializeSettings(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("Settings")) {
            // Open Settings activity
            Intent myIntent = new Intent(this, SettingsActivity.class);
            startActivity(myIntent);
        } else if (item.getTitle().equals("Add")) {
            // Open New Entry activity
            Intent myIntent = new Intent(getApplicationContext(), NewEntryActivity.class);
            startActivity(myIntent);
        } else if (item.getTitle().equals("Delete")) {
            // Delete selected row
            TransactionTable table = findViewById(R.id.new_entry_table);
            table.deleteRow();
        } else if (item.getTitle().equals("Sync")) {
            // Send new entries to database
            syncWithDatabase();
        }
        return super.onOptionsItemSelected(item);
    }

    private void syncWithDatabase() {
        final Context ctx = getApplicationContext();
        final String json = FileReadWrite.read(ctx);
        if (json.equals("")) {
            return;
        } else {
            RequestQueue queue = Volley.newRequestQueue(ctx);
            String url = Settings.getURL(ctx);
            int port = Settings.getPort(ctx);
            String fullURL = url + ":" + port + "/submit";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, fullURL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // TODO: add logging here
                            FileReadWrite.write(ctx, "");
                            TransactionTable table = findViewById(R.id.new_entry_table);
                            table.setData(new ArrayList<Transaction>());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: add logging here
                        }
                    }) {
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }
                        @Override
                        public byte[] getBody() {
                            try {
                                return json.getBytes("utf-8");
                            } catch (UnsupportedEncodingException uee) {
                                return null;
                            }
                        }
                    };

            // Add the request to the RequestQueue.
            queue.add(stringRequest);
        }
    }

}