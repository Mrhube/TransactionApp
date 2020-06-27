package com.example.transactionapp.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.transactionapp.R;
import com.example.transactionapp.structure.Transaction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private TableLayout table;
    private List<Transaction> data;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        textView.setText("Loading...");

        TableLayout table = root.findViewById(R.id.table_dashboard);
        table.addView(new HeaderRow(getContext()));
        this.table = table;

        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        String url ="http://192.168.1.68:8080/go";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
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

    public void setData(List<Transaction> data) {
        this.data = data;
        updateTable();
    }

    private void updateTable() {
        for (int i = 0; i < data.size(); i++) {
            Transaction row = data.get(i);
            table.addView(new DataRow(getContext(), i, row.getDate(), row.getAmount(), row.getCategory()));
        }
    }
}