package com.example.transactionapp.ui.home;

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

import com.example.transactionapp.misc.FileReadWrite;
import com.example.transactionapp.misc.JSONConverter;
import com.example.transactionapp.R;
import com.example.transactionapp.ui.TransactionTable;
import com.example.transactionapp.ui.HeaderRow;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Get root view
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Populate views
        final TextView textView = root.findViewById(R.id.text_home);
        ScrollView scrollView = root.findViewById(R.id.home_scrollview);
        TableLayout header = root.findViewById(R.id.table_header);
        header.addView(new HeaderRow(getContext()));
        TransactionTable table = new TransactionTable(getContext(), textView);
        table.setId(R.id.new_entry_table);
        scrollView.addView(table);

        // Populate TransactionTable data
        table.setData(JSONConverter.JSONToTransactionList(FileReadWrite.read(getContext())));

        return root;
    }
}