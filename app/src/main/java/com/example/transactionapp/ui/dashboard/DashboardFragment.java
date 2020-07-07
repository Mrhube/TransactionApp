package com.example.transactionapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.transactionapp.R;
import com.example.transactionapp.misc.Settings;
import com.example.transactionapp.structure.Transaction;
import com.example.transactionapp.ui.HeaderRow;
import com.example.transactionapp.ui.TransactionTable;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


public class DashboardFragment extends Fragment {

    private TransactionTable table;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Get root view
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Rebuild the action bar menu and hide buttons
        setHasOptionsMenu(true);
        getActivity().invalidateOptionsMenu();

        // Populate views
        final TextView textView = root.findViewById(R.id.text_dashboard);
        ScrollView scrollView = root.findViewById(R.id.scroll_view);
        textView.setText("Connecting...");
        TableLayout header = root.findViewById(R.id.table_header);
        TransactionTable table = new TransactionTable(getContext(), textView);
        scrollView.addView(table);
        header.addView(new HeaderRow(getContext()));
        this.table = table;

        // Retrieve URL for the API
        String url = Settings.getURL(getContext());
        int port = Settings.getPort(getContext());
        String fullURL = url + ":" + port + "/go"; //"http://192.168.1.82:8080/go";

        // Request a string response from the provided URL
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, fullURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ObjectMapper objectMapper = new ObjectMapper();
                            List<Transaction> data = objectMapper.readValue(response, new TypeReference<List<Transaction>>(){});
                            setData(data);
                            textView.setText("");
                        } catch(Exception e) {
                            textView.setText(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(error.getMessage());
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return root;
    }

    // Populate the TransactionTable
    public void setData(List<Transaction> data) {
        table.setData(data);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_delete).setVisible(false);
        menu.findItem(R.id.action_add).setVisible(false);
    }

}