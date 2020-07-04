package com.example.transactionapp.ui.home;

import android.content.Intent;
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
import androidx.lifecycle.ViewModelProviders;

import com.example.transactionapp.FileReadWrite;
import com.example.transactionapp.JSONConverter;
import com.example.transactionapp.NewEntryActivity;
import com.example.transactionapp.R;
import com.example.transactionapp.ui.TransactionTable;
import com.example.transactionapp.ui.dashboard.HeaderRow;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        ScrollView scrollView = root.findViewById(R.id.home_scrollview);
        TableLayout header = root.findViewById(R.id.table_header);

        header.addView(new HeaderRow(getContext()));


        TransactionTable table = new TransactionTable(getContext(), textView);
        scrollView.addView(table);
        table.setData(JSONConverter.JSONToTransactionList(FileReadWrite.read(getContext())));
        table.setId(R.id.new_entry_table);

        return root;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

    }
}